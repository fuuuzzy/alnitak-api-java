package com.ztingfg.cqrs.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuUpdate {

    private Long id;

    private List<Long> menuIds;
}
