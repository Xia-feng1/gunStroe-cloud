package com.edu.gun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.api.dto.GunDTO;
import com.edu.api.dto.LendDTO;
import com.edu.common.domain.R;
import com.edu.gun.domain.Gun;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;

import java.util.List;

public interface IGunService extends IService<Gun> {
    R pageGunList(String key, Integer num, Integer size);

    List<GunDTO> getListBySid(Integer sid);
    R lendGun(LendDTO lendDTO);
    R getTopList();


}
