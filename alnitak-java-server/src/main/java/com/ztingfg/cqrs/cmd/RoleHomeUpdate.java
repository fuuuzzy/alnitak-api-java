package com.ztingfg.cqrs.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleHomeUpdate {

    private Long id;

    private String home;
}
