package com.edu.gun.controller;

import com.edu.api.dto.GunDTO;
import com.edu.api.dto.LendDTO;
import com.edu.common.domain.R;
import com.edu.gun.domain.Gun;
import com.edu.gun.service.IGunService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gun")
@RequiredArgsConstructor
@Api(tags = "枪支展示管理接口")
public class GunController {
    private final IGunService iGunService;
    @ApiOperation("分页查询枪支")
    @GetMapping("/page")
    public R pageGunList(String key,Integer num,Integer size) {
      return iGunService.pageGunList(key, num, size);
    }

    @ApiOperation("仓库id查询枪支")
    @GetMapping("/ck")
    public List<GunDTO> gunList(Integer sid) {
        return iGunService.getListBySid(sid);
    }
    @ApiOperation("出借枪支")
    @PutMapping("/lend")
    public R lendGun(@RequestBody LendDTO lendDTO) {
        return iGunService.lendGun(lendDTO);
    }

    @ApiOperation("出借枪支")
    @GetMapping("/tops")
    public R getTopGun() {
        return iGunService.getTopList();
    }
}
