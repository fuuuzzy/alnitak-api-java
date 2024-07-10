package com.ztinfg.utils;

import com.google.common.base.Splitter;
import com.google.common.net.UrlEscapers;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sean
 */
public abstract class StringUtil {

    public static String randStr(int len) {
        return randStr("", len);
    }

    public static String randStr(String prefix, int len) {
        int leftLimit = 48;
        int rightLimit = 122;
        SecureRandom random = new SecureRandom();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(len)
                .collect(() -> new StringBuilder(prefix), StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String randNumber(int size) {
        SecureRandom random = new SecureRandom();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(random.nextInt(0, 10));
        }
        return builder.toString();
    }

    public static Map<String, String> parseQueryParams(String queryString) {
        if (null == queryString || queryString.trim().isEmpty()) {
            return Collections.emptyMap();
        }
        List<String> queries = Splitter.on("&").splitToList(queryString);
        Map<String, String> params = new HashMap<>();
        for (String query : queries) {
            String escape = UrlEscapers.urlPathSegmentEscaper().escape(query);
            String[] split = escape.split("=");
            if (split.length == 2) {
                String key = split[0];
                String value = split[1];
                params.put(key, value);
            }
        }
        return params;
    }

    private StringUtil() {
    }
}
