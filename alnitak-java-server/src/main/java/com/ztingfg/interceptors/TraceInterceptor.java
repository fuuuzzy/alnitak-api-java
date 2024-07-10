package com.ztingfg.interceptors;

import com.ztinfg.Consts;
import com.ztingfg.comment.RequestContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class TraceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = RequestContext.createTraceId();
        if (!response.isCommitted()) {
            response.setHeader(Consts.TRACE_ID_HEADER, traceId);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestContext.clean();
    }
}
