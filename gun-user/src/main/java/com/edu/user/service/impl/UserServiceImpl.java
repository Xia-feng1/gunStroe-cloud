package com.edu.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.common.constants.SystemConstants;
import com.edu.common.domain.PageVo;
import com.edu.common.domain.R;
import com.edu.common.utils.JwtUtils;
import com.edu.common.utils.PasswordEncryptionUtil;
import com.edu.common.utils.RedisCache;
import com.edu.user.domain.User;
import com.edu.user.mapper.UserMapper;
import com.edu.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Objects;

@Service("user-list-service")
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final RedisCache redisCache;
    //获取全部用户
    @Override
    public R pageAllUser(String keyword, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(keyword), User::getNickName, keyword)
                .or()
                .like(StringUtils.hasText(keyword), User::getPhonenumber, keyword)
                .orderByDesc(User::getUpdateTime).eq(User::getDelFlag, SystemConstants.NORMAL);
        Page page = new Page(pageNum, pageSize);
        page(page, queryWrapper);
        return R.ok(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public R loginUser(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getDelFlag,SystemConstants.NORMAL)
                .eq(User::getUserName,user.getUserName())
                .or().eq(User::getNickName,user.getUserName())
                .or().eq(User::getPhonenumber,user.getUserName());
        User user1 = getBaseMapper().selectOne(queryWrapper);
        String s = PasswordEncryptionUtil.decryptPassword(user1.getPassword());
        if(Objects.isNull(user1)){
            return R.ok("用户名或密码错误");
        }
        if(!s.equals(user.getPassword())){
            return R.ok("用户名或密码错误");
        }
        String jwt = JwtUtils.createJWT(user1.getId());
        //认证通过将信息存入Redis
        redisCache.setCacheObject(SystemConstants.TOKEN_KEY+user1.getId(), user1);
        HashMap<String, String> map = new HashMap<>();
        map.put(SystemConstants.TOKEN_KEY, jwt);
        return R.ok(map);
    }

    @Override
    public R insertUser(User user) {
       if(existUserName(user.getUserName())){
           return  R.error("用户名已存在");
       }
        if(existNickName(user.getNickName())){
            return  R.error("昵称已存在");
        }
        if(exitPhone(user.getPhonenumber())){
            return  R.error("手机号已存在");
        }
        if(exitEmail(user.getEmail())){
            return  R.error("手机号已存在");
        }

        String s = PasswordEncryptionUtil.encryptPassword(user.getPassword());
        user.setPassword(s);
        int insert = getBaseMapper().insert(user);
        if(insert>0){
            return  R.ok();
        }else{
            return R.error("系统故障联系后台管理员");
        }
    }

    private boolean exitEmail(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,email);
        User user = getBaseMapper().selectOne(queryWrapper);
        if(ObjectUtils.isEmpty(user)){
            return true;
        }
        return false;
    }

    private boolean exitPhone(String phonenumber) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhonenumber,phonenumber);
        User user = getBaseMapper().selectOne(queryWrapper);
        if(ObjectUtils.isEmpty(user)){
            return true;
        }
        return false;
    }

    private boolean existNickName(String nickName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNickName,nickName);
        User user = getBaseMapper().selectOne(queryWrapper);
        if(ObjectUtils.isEmpty(user)){
            return true;
        }
        return false;
    }

    private boolean existUserName(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,userName);
        User user = getBaseMapper().selectOne(queryWrapper);
        if(ObjectUtils.isEmpty(user)){
            return true;
        }
        return false;
    }

    @Override
    public R getUserByID(String id) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getDelFlag,SystemConstants.NORMAL)
                .eq(User::getId ,id);
        User user = getBaseMapper().selectOne(queryWrapper);
        if(ObjectUtils.isEmpty(user)){
            return R.error("查无此人");
        }
        String password = user.getPassword();
        String s = PasswordEncryptionUtil.decryptPassword(password);
        user.setPassword(s);
        return R.ok(user);
    }

    @Override
    public R updateUser(User user) {
        String password = user.getPassword();
        String s = PasswordEncryptionUtil.encryptPassword(password);
        user.setPassword(s);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,user.getId());
        int update = getBaseMapper().update(user, queryWrapper);
        if(update>0){
            return  R.ok();
        }else{
            return R.error("系统故障联系后台管理员");
        }
    }

    @Override
    public R deleteUser(String id) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId,id).set(User::getDelFlag, SystemConstants.DELETE_NORMAL);
        boolean update = update(updateWrapper);
        if(update){
            return  R.ok();
        }else{
            return R.error("系统故障联系后台管理员");
        }
    }

    @Override
    public R deleteUsers(String[] ids) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(User::getId,ids).set(User::getDelFlag, SystemConstants.DELETE_NORMAL);
        boolean update = update(updateWrapper);
        if(update){
            return  R.ok();
        }else{
            return R.error("系统故障联系后台管理员");
        }
    }
}
