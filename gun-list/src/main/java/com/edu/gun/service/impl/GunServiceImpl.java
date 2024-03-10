package com.edu.gun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.api.client.LendClient;
import com.edu.api.client.StoreClient;
import com.edu.api.dto.GunDTO;
import com.edu.api.dto.LendDTO;
import com.edu.api.dto.StoreDTO;
import com.edu.common.constants.SystemConstants;
import com.edu.common.domain.LendMSG;
import com.edu.common.domain.PageVo;
import com.edu.common.domain.R;
import com.edu.common.utils.BeanUtils;
import com.edu.common.utils.RabbitMqHelper;
import com.edu.gun.config.TopProperties;
import com.edu.gun.domain.Gun;
import com.edu.gun.domain.vo.GunVo;
import com.edu.gun.mapper.GunMapper;
import com.edu.gun.service.IGunService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GunServiceImpl extends ServiceImpl<GunMapper, Gun> implements IGunService {
    private final StoreClient storeClient;
    private final LendClient lendClient;
    private final RabbitMqHelper rabbitMqHelper;
    private final TopProperties topProperties;

    @Override
    public R pageGunList(String keyword, Integer num, Integer size) {
        LambdaQueryWrapper<Gun> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Gun::getDelFlag, SystemConstants.NORMAL)
                .eq(Gun::getLendType, SystemConstants.NORMAL)
                .like(StringUtils.hasText(keyword), Gun::getGunName, keyword)
                .or()
                .like(StringUtils.hasText(keyword), Gun::getGunDesc, keyword)
                .orderByDesc(Gun::getGunHot);
        //设置分页
        Page<Gun> gunsPage = new Page<>(num, size);
        page(gunsPage, queryWrapper);
        List<GunVo> gunVos = BeanUtils.copyList(gunsPage.getRecords(), GunVo.class);
        for (GunVo g : gunVos) {
            StoreDTO oneStore = storeClient.getOneStore(g.getSid());
            g.setStoreName(oneStore.getStoreName());
        }
        return R.ok(new PageVo(gunVos, gunsPage.getTotal()));

    }

    @Override
    public List<GunDTO> getListBySid(Integer sid) {
        LambdaQueryWrapper<Gun> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Gun::getDelFlag, SystemConstants.NORMAL).eq(Gun::getSid, sid);
        List<Gun> guns = getBaseMapper().selectList(queryWrapper);
        List<GunVo> gunVos = BeanUtils.copyList(guns, GunVo.class);
        for (GunVo g : gunVos) {
            StoreDTO oneStore = storeClient.getOneStore(g.getSid());
            g.setStoreName(oneStore.getStoreName());
        }
        return BeanUtils.copyList(gunVos, GunDTO.class);
    }

    @Override
    public R lendGun(LendDTO lendDTO) {
        LambdaUpdateWrapper<Gun> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Gun::getId, lendDTO.getLendGunId()).set(Gun::getLendType, SystemConstants.LEND);
        boolean update = update(updateWrapper);
        if (update) {
            //生成出借信息
            lendClient.insertLend(lendDTO);
            // 发送延迟消息
            LendMSG<Object> reminder = new LendMSG<>(lendDTO);
            reminder.setData(lendDTO);
            rabbitMqHelper.sendDelayMessage("delay-exchange", "delayed", reminder,6000);
            System.out.println("延迟消息发送成功！"+reminder);
            return R.ok();
        }
        return R.error("出借系统问题，请联系管理员");
    }

    @Override
    public R getTopList() {
        Integer maxTop = topProperties.getMaxTop();
        LambdaQueryWrapper<Gun> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Gun::getDelFlag, SystemConstants.NORMAL)
                .eq(Gun::getLendType, SystemConstants.NORMAL)
                .orderByDesc(Gun::getGunHot)
                .last("LIMIT "+maxTop);
        List<Gun> guns = getBaseMapper().selectList(queryWrapper);
        List<GunVo> gunVos = BeanUtils.copyList(guns, GunVo.class);
        for (GunVo g : gunVos) {
            StoreDTO oneStore = storeClient.getOneStore(g.getSid());
            g.setStoreName(oneStore.getStoreName());
        }
        return R.ok(gunVos);
    }

    public static LocalDate convertDateToLocalDate(Date date) {
        // 将Date转换为Instant
        Instant instant = date.toInstant();
        // 使用系统默认时区将Instant转换为ZonedDateTime
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        // 从ZonedDateTime获取LocalDate
        return zonedDateTime.toLocalDate();
    }
}
