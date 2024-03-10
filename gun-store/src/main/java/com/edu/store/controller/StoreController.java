package com.edu.store.controller;

import com.edu.api.dto.GunDTO;
import com.edu.api.dto.StoreDTO;
import com.edu.common.domain.R;
import com.edu.store.domain.Store;
import com.edu.store.service.IStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
@Api(tags = "仓库展示管理接口")
public class StoreController {
    private final IStoreService iStoreService;

    @ApiOperation("分页查询仓库信息")
    @GetMapping("/page")
    public R pageStoreList(String key,Integer num,Integer size) {
      return iStoreService.pageStoreList(key, num, size);
    }
    @ApiOperation("通过id查询仓库信息")
    @GetMapping("{id}")
    public StoreDTO getOneStore(@PathVariable Integer id) {
        return iStoreService.getOneById(id);
    }

}
