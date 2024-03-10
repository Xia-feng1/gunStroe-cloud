package com.edu.lend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.common.domain.R;
import com.edu.lend.domain.Lend;

public interface ILendService extends IService<Lend> {
    R pageAllLend(String keyword, Integer pageNum, Integer pageSize);
    R insertLendData(Lend lend);
    R getLendByID(String id );
    R updateLend(Lend lend);
    R deleteLend(String id);
    R deleteLends(String[] ids);

}
