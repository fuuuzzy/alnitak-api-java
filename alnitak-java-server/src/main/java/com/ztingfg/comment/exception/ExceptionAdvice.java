package com.ztingfg.comment.exception;

import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com.ztingfg.comment.BizStatus;
import com.ztingfg.comment.GenericResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<GenericResult<?>> handleBusiness(BusinessException e, HttpServletRequest httpServletRequest) {
        logger.error("handle BusinessException {}", e.getMessage(), e);
        GenericResult<?> res = GenericResult.from(e.getBusinessStatus());
        return new ResponseEntity<>(res, e.getBusinessStatus().getHttpStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GenericResult<?>> resolveValidate(ConstraintViolationException e) {
        GenericResult<?> res = GenericResult.from(BizStatus.IllegalParams, e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessageTemplate)
                .collect(Collectors.joining(",")));
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    /**
     * validation参数校验异常内部处理
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<GenericResult<?>> resolveValidate(ValidationException e) {
        logger.error("handle ValidationException {}", e.getMessage());
        String message = e.getMessage();
        if (e.getCause() != null) {
            message = e.getCause().getMessage();
        }
        GenericResult<?> res = GenericResult.from(BizStatus.IllegalParams, message);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    private final Function<List<String[]>, String> messageJoiner = d -> d.stream().map(s -> s[1])
            .collect(Collectors.joining(";"));

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResult<?>> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest httpServletRequest) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String, List<String[]>> groupFieldError = allErrors.stream().map(x -> {
            if (x instanceof FieldError fe) {
                return new String[]{fe.getField(), fe.getDefaultMessage()};
            } else {
                return new String[]{x.getObjectName(), x.getDefaultMessage()};
            }
        }).collect(Collectors.groupingBy(x -> x[0]));

        String errors = groupFieldError.entrySet().stream()
                .map(entry -> new String[]{entry.getKey(), messageJoiner.apply(entry.getValue())})
                .map(x -> Joiner.on("=").join(x))
                .collect(Collectors.joining(";"));
        logger.warn("valid error [{}]", errors);
        GenericResult<?> res = GenericResult.from(BizStatus.IllegalParams, errors);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public GenericResult<?> resolveUnknownException(Exception e, HttpServletRequest httpServletRequest) {
        if (e instanceof SQLException) {
            logger.error("sql execution exception {}", e.getMessage());
            return GenericResult.from(BizStatus.ServerError, "Sql异常");
        } else {
            Throwable rootCause = Throwables.getRootCause(e);
            logger.error("handle exception {}", rootCause.getMessage(), rootCause);
            return GenericResult.from(BizStatus.ServerError, "未知的异常");
        }
    }
}
