package com.edu.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.api.dto.GunDTO;
import com.edu.api.dto.StoreDTO;
import com.edu.common.domain.R;
import com.edu.store.domain.Store;
import com.edu.store.domain.vo.GunVo;

import java.util.List;

public interface IStoreService extends IService<Store> {
R pageStoreList(String key,Integer num,Integer size);
StoreDTO getOneById(Integer id);

}
