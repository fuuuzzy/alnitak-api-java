package com.ztingfg.services;

import com.ztinfg.utils.JweUtil;
import com.ztingfg.bo.TokenInfo;
import com.ztingfg.comment.BizStatus;
import com.ztingfg.comment.exception.BusinessException;
import com.ztingfg.comment.utils.JsonUtil;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${jwe.secret}")
    private String jweSecret;

    public String createToken(TokenInfo tokenInfo) {
        String json = JsonUtil.toJson(tokenInfo);
        try {
            return JweUtil.createToken(json, jweSecret);
        } catch (JoseException e) {
            throw new BusinessException(BizStatus.TokenCreateError);
        }
    }

    public String createToken(Long userId, Long expiry) {
        TokenInfo tokenInfo = new TokenInfo(userId, expiry);
        return createToken(tokenInfo);
    }

    public TokenInfo verifyToken(String token) {
        if (null == token || token.trim().isEmpty()) {
            throw new BusinessException(BizStatus.IllegalToken);
        }
        try {
            String payload = JweUtil.verifyToken(token, jweSecret);
            return JsonUtil.toObject(payload, TokenInfo.class);
        } catch (JoseException e) {
            throw new BusinessException(BizStatus.IllegalToken);
        }
    }
}
