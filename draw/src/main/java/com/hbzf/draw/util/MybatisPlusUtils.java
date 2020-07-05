package com.hbzf.draw.util;


import com.baomidou.mybatisplus.annotation.DbType;
/*import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;*/
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 代码自动生成
 *
 * @author Administrator
 */
public class MybatisPlusUtils {

    /*public static void main(String[] args) {
        String[] models = {"play-common"};
        for (String model : models) {
            shell(model);
        }
    }

    private static void shell(String model) {
        //获取模块绝对路径
        String path = Objects.requireNonNull(MybatisPlusUtils.class.getClassLoader().getResource("")).getPath();
        if (StringUtils.isEmpty(path)) {
            return;
        }
        path = path.split("/target")[0];
        AutoGenerator mpg = new AutoGenerator();
        //全局配置
        setGlobalConfig(mpg, path);
        //数据源配置
        setDataSourceConfig(mpg);
        //策略配置
        String[] tables = needCreateTables();
        setStrategyConfig(mpg, tables);
        // 包配置
        setPackageConfig(mpg);
        //自定义模板配置
        setTemplateConfig(mpg);

        //配置mapper.xml生成路径
        setXmlPath(mpg, path);
        // 执行生成
        mpg.execute();
    }

    *//**
     * 需要生成的表
     *
     * @return
     *//*
    private static String[] needCreateTables() {
        return new String[]{"chose_expert","chose_major","expert","major","program_manager","pur_way","supervisory_place"
                ,"unit","user"
        };
    }

    private static void setXmlPath(AutoGenerator mpg, String path) {
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        String fileName = path + "/src/main/resources/mapper/";
        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return fileName + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }

    private static void setTemplateConfig(AutoGenerator mpg) {
        TemplateConfig tc = new TemplateConfig();
        tc.setController(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        tc.setXml(null);
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);
    }

    private static void setPackageConfig(AutoGenerator mpg) {
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.hbzf.draw");
        pc.setEntity("model");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);
    }

    private static void setStrategyConfig(AutoGenerator mpg, String[] tables) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntityColumnConstant(true);
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // 此处可以修改为您的表前缀
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setTablePrefix("");
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        // 需要生成的表
        strategy.setInclude(tables);
        mpg.setStrategy(strategy);

    }

    private static void setDataSourceConfig(AutoGenerator mpg) {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(globalConfig,fieldType);
            }
        });
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("liu1995a");
        dsc.setUrl("jdbc:mysql://67.216.221.244:3306/szfcj?characterEncoding=utf8");
        mpg.setDataSource(dsc);
    }

    private static void setGlobalConfig(AutoGenerator mpg, String path) {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(path + "/src/main/java");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(false);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setAuthor("");
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        mpg.setGlobalConfig(gc);
    }*/
}