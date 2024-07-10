package com.ztinfg;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;

public class Main {

    /**
     * 代码生成
     *
     * @param args args
     */
    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/alnitak?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false"
                        , "root", "420021")
                .globalConfig(builder -> {
                    builder.author("fuuuzzy") // 设置作者
                            .outputDir("/Users/g/Documents/oneself/alnitak-java/alnitak-java-generator/src/main/java/com/ztinfg/generator"); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder -> {
                    builder.parent("com.ztingfg") // 设置父包名
                            .entity("entities") // 设置实体类包名
                            .mapper("mappers") // 设置 Mapper 接口包名
                            .service("services") // 设置 Service 接口包名
                            .serviceImpl("service.impl") // 设置 Service 实现类包名
                            .xml("mappers"); // 设置 Mapper XML 文件包名
                })
                .strategyConfig(builder -> {
                    builder// 设置需要生成的表名
                            .entityBuilder()
                            .disableSerialVersionUID() // 禁用生成serialVersionUID
                            .enableRemoveIsPrefix()
                            .enableLombok() // 启用 Lombok
                            .enableTableFieldAnnotation() // 启用字段注解
                            .controllerBuilder()
                            .enableRestStyle().serviceBuilder().formatServiceFileName("%sService123")
                            .formatServiceImplFileName("%sService");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}