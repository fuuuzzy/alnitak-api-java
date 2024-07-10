package com.ztingfg.bo;

import com.ztingfg.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {

    private User userInfo;
}
