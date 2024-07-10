package com.ztingfg.bo;

import lombok.Data;

@Data
public class TokenInfo {

    private Long accountId;

    private long expirationTime;

    public static TokenInfo from(Long accountId, Long expirationTime) {
        return new TokenInfo(accountId, expirationTime);
    }

    public TokenInfo() {
    }

    public TokenInfo(Long accountId, long expirationTime) {
        this.accountId = accountId;
        this.expirationTime = expirationTime;
    }
}
