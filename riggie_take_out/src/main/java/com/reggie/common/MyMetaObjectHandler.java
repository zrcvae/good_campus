package com.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
// 自动填充公用字段
public class MyMetaObjectHandler implements MetaObjectHandler {
    // 插入时自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("插入时公共字段自动填充。。。");
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
//        Long empId = BaseContext.getCurrentId();
        metaObject.setValue("createUser", BaseContext.getCurrentId());  // 后面需要修改
        metaObject.setValue("updateUser", BaseContext.getCurrentId());  // 后面需要修改

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新时公共字段自动填充。。。");
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());  // 后面需要修改
    }
}
