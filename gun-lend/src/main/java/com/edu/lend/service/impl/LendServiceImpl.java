package com.edu.lend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.common.constants.SystemConstants;
import com.edu.common.domain.PageVo;
import com.edu.common.domain.R;
import com.edu.lend.domain.Lend;
import com.edu.lend.mapper.LendMapper;
import com.edu.lend.service.ILendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Service("lend-list-service")
@RequiredArgsConstructor
public class LendServiceImpl extends ServiceImpl<LendMapper, Lend> implements ILendService {
    //获取全部用户
    @Override
    public R pageAllLend(String keyword, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Lend> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(keyword), Lend::getLenderName, keyword)
                .or()
                .like(StringUtils.hasText(keyword), Lend::getLenderPhone, keyword)
                .orderByDesc(Lend::getUpdateTime).eq(Lend::getDelFlag, SystemConstants.NORMAL);
        Page page = new Page(pageNum, pageSize);
        page(page, queryWrapper);
        return R.ok(new PageVo(page.getRecords(), page.getTotal()));
    }
    @Override
    public R insertLendData(Lend lend) {
        int insert = getBaseMapper().insert(lend);
        if(insert>0){
            return  R.ok();
        }else{
            return R.error("系统故障联系后台管理员");
        }
    }

    @Override
    public R getLendByID(String id) {
        LambdaQueryWrapper<Lend> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Lend::getDelFlag,SystemConstants.NORMAL)
                .eq(Lend::getId ,id);
        Lend lend = getBaseMapper().selectOne(queryWrapper);
        if(ObjectUtils.isEmpty(lend)){
            return R.error("查无此人");
        }
        return R.ok(lend);
    }

    @Override
    public R updateLend(Lend lend) {
        LambdaQueryWrapper<Lend> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Lend::getId, lend.getId());
        int update = getBaseMapper().update(lend, queryWrapper);
        if(update>0){
            return  R.ok();
        }else{
            return R.error("系统故障联系后台管理员");
        }
    }

    @Override
    public R deleteLend(String id) {
        LambdaUpdateWrapper<Lend> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Lend::getId,id).set(Lend::getDelFlag, SystemConstants.DELETE_NORMAL);
        boolean update = update(updateWrapper);
        if(update){
            return  R.ok();
        }else{
            return R.error("系统故障联系后台管理员");
        }
    }

    @Override
    public R deleteLends(String[] ids) {
        LambdaUpdateWrapper<Lend> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(Lend::getId,ids).set(Lend::getDelFlag, SystemConstants.DELETE_NORMAL);
        boolean update = update(updateWrapper);
        if(update){
            return  R.ok();
        }else{
            return R.error("系统故障联系后台管理员");
        }
    }
}
