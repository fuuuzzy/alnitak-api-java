package com.ztingfg.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionInfo {

    private String token;

    private String refreshToken;
}
