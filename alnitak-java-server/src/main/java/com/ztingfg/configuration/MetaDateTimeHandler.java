package com.ztingfg.configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MetaDateTimeHandler implements MetaObjectHandler {

    private static final Logger logger = LoggerFactory.getLogger(MetaDateTimeHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        logger.info("开始插入填充...");
        this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info("开始更新填充...");
        this.strictUpdateFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
    }
}