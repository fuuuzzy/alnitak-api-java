package com.ztingfg.services;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.ztinfg.utils.StringUtil;
import com.ztingfg.comment.BizStatus;
import com.ztingfg.comment.exception.BusinessException;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class SendService {

    private static final Logger log = getLogger(SendService.class);

    @Value("${spring.mail.username}")
    private String fromMail;

    @Resource
    private JavaMailSender javaMailSender;

    private final Cache<String, String> cache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(Duration.ofSeconds(60))
            .build();

    @Resource(name = "virtualThreadExecutor")
    private Executor executor;

    public void validateCode(String email, String code) {
        String cacheCode = cache.getIfPresent(email);
        if (cacheCode != null && cacheCode.equals(code)) {
            cache.invalidate(email);
            return;
        }
        throw new BusinessException(BizStatus.MailCodeError);
    }

    public void send(String email) {
        String cacheCode = cache.getIfPresent(email);
        if (cacheCode != null) {
            throw new BusinessException(BizStatus.SendMailCodeError);
        }
        String code = StringUtil.randNumber(6);
        log.info("Sent email {}  code {}", email, code);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(email);
        message.setSubject("验证码");
        message.setText("您的验证码是: " + code);
        CompletableFuture.runAsync(() -> {
            cache.put(email, code);
            javaMailSender.send(message);
            log.info("Sent email success");
        }, executor);
    }
}
