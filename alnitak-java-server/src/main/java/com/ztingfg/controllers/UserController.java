package com.ztingfg.controllers;

import com.ztingfg.bo.UserInfo;
import com.ztingfg.comment.GenericResult;
import com.ztingfg.comment.RequestContext;
import com.ztingfg.entities.User;
import com.ztingfg.pagination.Pagination;
import com.ztingfg.pagination.PaginationResult;
import com.ztingfg.services.UserService;
import com.ztingfg.vo.RegisterRequest;
import com.ztingfg.vo.UserRole;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/v1/auth/register")
    public GenericResult<Object> register(@Valid @RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return GenericResult.success();
    }

    @GetMapping("/v1/user/getUserBaseInfo")
    public GenericResult<UserInfo> getUserBaseInfo(@RequestParam("userId") Long userId) {
        User user = userService.getById(userId);
        return GenericResult.success(UserInfo.builder().userInfo(user).build());
    }

    @GetMapping("/v1/user/getUserInfo")
    public GenericResult<UserInfo> getUserInfo() {
        Long uid = RequestContext.getUser().getId();
        User user = userService.getById(uid);
        return GenericResult.success(UserInfo.builder().userInfo(user).build());
    }

    @GetMapping("/v1/user/getUserListManage")
    public GenericResult<PaginationResult<User>> getUserListManage(Pagination pagination) {
        PaginationResult<User> paginationResult = userService.getUserListManage(pagination);
        return GenericResult.success(paginationResult);
    }

    @PutMapping("/v1/user/editUserInfo")
    public GenericResult<Object> editUserInfo(@RequestBody User user) {
        Long uid = RequestContext.getUser().getId();
        userService.editUserInfo(uid, user);
        return GenericResult.success();
    }

    @PutMapping("/v1/user/editUserInfoManage")
    public GenericResult<Object> editUserInfoManage(@RequestBody User user) {
        userService.editUserInfo(user.getId(), user);
        return GenericResult.success();
    }

    @PutMapping("/v1/user/editUserRole")
    public GenericResult<Object> editUserRole(@RequestBody UserRole userRole) {
        userService.editUserRole(userRole);
        return GenericResult.success();
    }

    @DeleteMapping("/v1/user/deleteUser/{uid}")
    public GenericResult<Object> deleteUser(@PathVariable Long uid) {
        userService.removeById(uid);
        return GenericResult.success();
    }
}
