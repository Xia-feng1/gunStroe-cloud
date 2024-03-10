package com.edu.lend.controller;

import com.edu.common.annotations.LogExecution;
import com.edu.common.domain.R;
import com.edu.lend.domain.Lend;
import com.edu.lend.service.ILendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lend")
@RequiredArgsConstructor
@Api(tags = "出借信息展示管理接口")
public class LendController {
    private final ILendService iLendService;
    @ApiOperation("分页查询出借信息")
    @GetMapping("/page")
    public R pageLendList(String key, Integer num, Integer size) {
        return iLendService.pageAllLend(key, num, size);
    }

    @ApiOperation("出借插入方法")
    @PostMapping("/insert")
    public R insertLend(@RequestBody Lend lend) {
        return iLendService.insertLendData(lend);
    }

    @ApiOperation("根据id查询出借信息")
    @GetMapping("/one/{id}")
    public R pageLendList(@PathVariable String id) {
        return iLendService.getLendByID(id);
    }

    @ApiOperation("出借更新方法")
    @PostMapping("/update")
    @LogExecution
    public R updateLend(@RequestBody Lend lend) {
        return iLendService.updateLend(lend);
    }
    @ApiOperation("根据id删除出借信息")
    @DeleteMapping("/delete/{id}")
    @LogExecution
    public R deleteLendById(@PathVariable String id) {
        return iLendService.deleteLend(id);
    }
    @ApiOperation("根据ids删除出借信息")
    @LogExecution
    @DeleteMapping("/delete")
    public R deleteLendByIds(String[] ids) {
        return iLendService.deleteLends(ids);
    }
}
