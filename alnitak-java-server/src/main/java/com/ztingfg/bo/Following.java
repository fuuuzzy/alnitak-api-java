package com.ztingfg.bo;

import com.ztingfg.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class Following {

    private Integer relation;

    private List<User> user;
}
