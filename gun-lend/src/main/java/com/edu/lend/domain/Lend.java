package com.edu.lend.domain;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * (Lender)表实体类
 * @since 2023-12-27 13:42:28
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("xq_lender")
public class Lend  {
    //出借记录id
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    //出借人名称
    private String lenderName;
    //出借人联系方式
    private String lenderPhone;
    //出借枪支描述
    private String lenderDesc;
    //出借枪支id
    private Integer lendGunId;
    //出借类型 0标识归还记录，1标识借出记录
    private Integer lendType;
    //出借开始时间
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//接受从前端传入的日期格式，映射到java类日期属性的规则
    private Date startTime;
    //出借结束时间
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//接受从前端传入的日期格式，映射到java类日期属性的规则
    private Date endTime;
    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//接受从前端传入的日期格式，映射到java类日期属性的规则
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//接受从前端传入的日期格式，映射到java类日期属性的规则
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    //删除标志
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
    //创建人
    @TableField(fill = FieldFill.INSERT)
    private Integer createBy;
    //更新人
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateBy;

}
