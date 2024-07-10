package com.ztingfg.comment;

import com.ztinfg.utils.StringUtil;
import com.ztingfg.comment.exception.BusinessException;
import com.ztingfg.entities.User;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

public final class RequestContext {

    private static final String AccountKey = "accountKey";

    private static final String TracerId = "traceId";

    private static final ThreadLocal<Map<String, Object>> localStore = ThreadLocal.withInitial(HashMap::new);

    public static String getTracerId() {
        return (String) localStore.get().get(TracerId);
    }

    public static User getUser() {
        User user = (User) localStore.get().get(AccountKey);
        if (user != null) {
            return user;
        }
        throw new BusinessException(BizStatus.AccountNotSignIn);
    }

    public static void setUser(User user) {
        if (user != null) {
            localStore.get().put(AccountKey, user);
        }
    }

    public static String createTraceId() {
        Map<String, Object> store = localStore.get();
        String id = StringUtil.randStr("Req_", 12);
        MDC.put(TracerId, id);
        store.put(TracerId, id);
        return id;
    }

    public static void clean() {
        MDC.clear();
        localStore.remove();
    }

    private RequestContext() {
    }
}
