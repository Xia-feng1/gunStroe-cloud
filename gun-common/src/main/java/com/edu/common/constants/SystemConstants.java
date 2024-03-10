package com.edu.common.constants;

public class SystemConstants {
    // 其他的正常状态
    public static final String STATUS_NORMAL = "0";
    // token字段名
    public static final String TOKEN_KEY = "token";
    public static final String USER_INFO = "user_info";
    // 根id状态
    public static final int ROOT_ID = -1;
    // 登录用户前缀
    public static final String PRE_KEY = "LOGIN:";
    // 枪支热度的key
    public static final String GUN_HOT = "GUN:hotCount";
    // 后台要登陆用户的redis key
    public static final String ADMIN_PRE_KEY = "ADMIN:";
    // 菜单的标识
    public static final String MENU = "C";
    // 按扭的标识
    public static final String BUTTON = "F";
    // 删除的状态
    public static final Integer DELETE_NORMAL = 1;
    // 未删除的状态
    public static final Integer NORMAL = 0;
    // 清空仓库装态
    public static final Integer REST_NULL = 0;
    // 超级管理员标识
    public static final Integer SU_ADMIN_USER = 1;
    // 管理员标识
    public static final Integer ADMIN_USER = 2;
    // 其他角色标识
    public static final Integer USER = 0;
    public static final String SYS_MENU = "M";
    //前后台用户的标志
    public static final String IS_FRONT_USER = "isFrontUser";
    public static final String TOP_LIST = "TOP5:";
    //枪支借出
    public static final Integer LEND = 1;
    //枪支未借出
    public static final Integer NOT_LEND = 0;
    //维护状态为未曾操作
    public static final Integer TEND_STATUS_NO_OPERATION =4 ;
    //枪支未归还
    public static final Integer NOT_RETURN = 1;
    //枪支已归还
    public static final Integer RETURN = 0;
    //维护前三
    public static String TEND_TOP_THREE="tendTopThree";
    //检查前三
    public static String CHECK_TOP_THREE="checkTopThree";
    //出借前三
    public static String LEND_TOP_THREE="lendTopThree";

}