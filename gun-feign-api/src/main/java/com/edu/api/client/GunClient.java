package com.edu.api.client;

import com.edu.api.dto.GunDTO;
import com.edu.common.domain.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("gun-list-service")
public interface GunClient {
    //分页查询枪支信息
    @GetMapping("/gun/page")
    R pageGunList(String key, Integer num, Integer size);

    //更具仓库id查询枪支信息
    @GetMapping("/gun/ck")
    List<GunDTO> gunList(Integer sid);

    @ApiOperation("出借枪支")
    @GetMapping("/gun/tops")
    R getTopGun();
}
