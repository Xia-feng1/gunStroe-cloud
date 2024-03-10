package com.edu.user.domain;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户表(User)表实体类
 * @since 2023-12-27 13:40:52
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User  implements Serializable {
    //主键
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    //用户名
    private String userName;
    //昵称
    private String nickName;
    //密码
    private String password;
    //用户类型：0代表普通用户，1代表管理员
    private Integer type;
    //邮箱
    private String email;
    //手机号
    private String phonenumber;
    //用户性别（0男，1女，2未知）
    private String sex;
    //头像
    private String avatar;
    //启用状态 0标识启用，1标识未启用
    @TableField(fill = FieldFill.INSERT)
    private Integer status;
    //删除标志 0标识未删除，1标识已删除
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
    //创建人的用户id
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    //更新人
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    //角色id
    @TableField(exist = false)
    private List<Integer> roleId;
    //权限id
    @TableField(exist = false)
    private List<Long> permissionId;
    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//接受从前端传入的日期格式，映射到java类日期属性的规则
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//接受从前端传入的日期格式，映射到java类日期属性的规则
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
