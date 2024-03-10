package com.edu.gun.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * (Guns)表实体类
 * @since 2023-12-27 13:42:02
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("xq_guns")
public class Gun {
    //枪支ID
    @TableId(type = IdType.AUTO)
    private Integer id;
    //分类ID
    private Integer cid;
    //仓库ID
    private Integer sid;
    //枪支编号ID
    private String gunId;
    //枪支名称
    private String gunName;
    //枪支出借状态，0表示未借出，1表示已借出
    private Integer lendType;
    //枪支描述
    private String gunDesc;
    //枪支图片
    private String gunImages;
    //枪支热度
    private Long gunHot;
    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//接受从前端传入的日期格式，映射到java类日期属性的规则
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//接受从前端传入的日期格式，映射到java类日期属性的规则
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

    public Gun(Integer id, long gunHot) {
        this.id=id;
        this.gunHot=gunHot;
    }
    @TableField(exist = false)
    //出借枪支描述
    private String lenderDesc;
    //出借开始时间
    @TableField(exist = false)
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//接受从前端传入的日期格式，映射到java类日期属性的规则
    private Date startTime;

    //出借结束时间
    @TableField(exist = false)
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//接受从前端传入的日期格式，映射到java类日期属性的规则
    private Date endTime;
}
