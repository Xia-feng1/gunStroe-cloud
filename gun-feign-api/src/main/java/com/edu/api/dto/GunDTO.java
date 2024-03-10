package com.edu.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "枪支DTO实体")
public class GunDTO {
    @ApiModelProperty("枪支id")
    private Integer id;
    private Integer cid;
    private Integer sid;
    private String storeName;
    private String gunId;
    private String gunName;
    private Integer lendType;
    private String gunDesc;
    private String gunImages;
    private Long gunHot;
}
