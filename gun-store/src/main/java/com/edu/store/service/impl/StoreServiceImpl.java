package com.edu.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.api.client.GunClient;
import com.edu.api.client.StoreClient;
import com.edu.api.dto.GunDTO;
import com.edu.api.dto.StoreDTO;
import com.edu.common.constants.SystemConstants;
import com.edu.common.domain.PageVo;
import com.edu.common.domain.R;
import com.edu.common.utils.BeanUtils;
import com.edu.store.domain.Store;
import com.edu.store.mapper.StoreMapper;
import com.edu.store.service.IStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements IStoreService {

    @Override
    public R pageStoreList(String keyword, Integer num, Integer size) {
        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Store::getDelFlag, SystemConstants.NORMAL)
                .like(StringUtils.hasText(keyword), Store::getStoreName, keyword)
                .or()
                .like(StringUtils.hasText(keyword), Store::getStoreLocal, keyword);
        //设置分页
        Page<Store> storesPage = new Page<>(num, size);
        page(storesPage,queryWrapper);
//        List<StoreVo> StoreVos = BeanUtils.copyList(StoresPage.getRecords(), StoreVo.class);
        return R.ok(new PageVo(storesPage.getRecords(), storesPage.getTotal()));

    }

    @Override
    public StoreDTO getOneById(Integer id) {
        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Store::getDelFlag,SystemConstants.NORMAL)
                .eq(Store::getId,id);
        Store one = getOne(queryWrapper);
      return   BeanUtils.copyBean(one,StoreDTO.class);
    }
}
