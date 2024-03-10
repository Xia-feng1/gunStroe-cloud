package com.edu.store.domain;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * (Store)表实体类
 * @since 2023-12-27 13:42:39
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("xq_store")
public class Store  {
    //仓库ID
    @TableId(type = IdType.AUTO)
    private Integer id;
    //仓库名称
    private String storeName;
    //仓库描述
    private String remark;
    //仓库容量
    private String storeNums;
    //仓库位置
    private String storeLocal;
    //创建人
    @TableField(fill = FieldFill.INSERT)
    private Integer creatBy;
    //更新人
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateBy;
    //删除标志
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
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
