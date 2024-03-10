package com.edu.user.controller;

import com.edu.common.annotations.LogExecution;
import com.edu.common.domain.R;
import com.edu.common.exception.BadRequestException;
import com.edu.user.domain.User;
import com.edu.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(tags = "用户信息展示管理接口")
public class UserController {
    private final IUserService iUserService;

    @ApiOperation("分页查询用户信息")
    @GetMapping("/page")
    @LogExecution
    public R pageUserList(String key, Integer num, Integer size) {
        return iUserService.pageAllUser(key, num, size);
    }

    @ApiOperation("用户登录方法")
    @PostMapping("/login")
    public R login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            //提示 必须要传用户名
            throw new BadRequestException("请输入正确信息");
        }
        return iUserService.loginUser(user);
    }
    @ApiOperation("用户插入方法")
    @PostMapping("/insert")
    public R insertUser(@RequestBody User user) {
        return iUserService.insertUser(user);
    }

    @ApiOperation("根据id查询用户信息")
    @GetMapping("/one/{id}")
    public R pageUserList(@PathVariable String id) {
        return iUserService.getUserByID(id);
    }

    @ApiOperation("用户更新方法")
    @PostMapping("/update")
    @LogExecution
    public R updateUser(@RequestBody User user) {
        return iUserService.updateUser(user);
    }
    @ApiOperation("根据id删除用户信息")
    @DeleteMapping("/delete/{id}")
    @LogExecution
    public R deleteUserById(@PathVariable String id) {
        return iUserService.deleteUser(id);
    }
    @ApiOperation("根据ids删除用户信息")
    @LogExecution
    @DeleteMapping("/delete")
    public R deleteUserByIds(String[] ids) {
        return iUserService.deleteUsers(ids);
    }
}
