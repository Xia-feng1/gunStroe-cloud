package com.edu.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "仓库信息")
@Data
@Accessors(chain = true)
public class StoreDTO {
    @ApiModelProperty("仓库id")
    private Integer id;
    @ApiModelProperty("仓库名")
    private String storeName;
}
