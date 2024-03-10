package com.edu.gun.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel(description = "枪支VO实体")
public class GunVo {
    @ApiModelProperty("枪支id")
    private Integer id;
    @ApiModelProperty("分类ID")
    private Integer cid;
    @ApiModelProperty("仓库ID")
    private Integer sid;
    @ApiModelProperty("仓库名称")
    private String storeName;
    @ApiModelProperty("枪支编号ID")
    private String gunId;
    @ApiModelProperty("枪支名称")
    private String gunName;
    @ApiModelProperty("枪支出借状态，0表示未借出，1表示已借出")
    private Integer lendType;
    @ApiModelProperty("枪支描述")
    private String gunDesc;
    @ApiModelProperty("枪支图片")
    private String gunImages;
    @ApiModelProperty("枪支热度")
    private Long gunHot;
}
