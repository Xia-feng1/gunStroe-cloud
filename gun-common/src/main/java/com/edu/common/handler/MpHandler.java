package com.edu.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.edu.common.utils.UserContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MpHandler implements MetaObjectHandler {
    String userId = UserContext.getUserId();
    @Override
    public void insertFill(MetaObject metaObject) {
        // 获取当前时间作为创建时间，填充到相应字段
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictUpdateFill(metaObject,"createBy", String.class,userId);
        this.strictUpdateFill(metaObject,"delFlag", Integer.class,0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新操作时的填充逻辑，这里可以根据需要自行实现
        this.strictUpdateFill(metaObject,"updateTime",Date.class,new Date());
        this.strictUpdateFill(metaObject,"updateBy", String.class,userId);

    }
}
