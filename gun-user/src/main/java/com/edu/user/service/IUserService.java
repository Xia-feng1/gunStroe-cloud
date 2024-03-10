package com.edu.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.common.domain.R;
import com.edu.user.domain.User;

public interface IUserService extends IService<User> {
    R pageAllUser(String keyword, Integer pageNum, Integer pageSize);
    R loginUser(User user);
    R insertUser(User user);
    R getUserByID(String id );
    R updateUser(User user);
    R deleteUser(String id);
    R deleteUsers(String[] ids);
}
