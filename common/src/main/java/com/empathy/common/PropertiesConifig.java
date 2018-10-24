package com.empathy.common;

import org.apache.log4j.PropertyConfigurator;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by dly on 2016/8/10.
 */
public class PropertiesConifig {
    private static Properties properties;

    static {
        System.out.println("配置文件读取");
        try {
            properties = new Properties();
//            InputStream inputStream = Object.class.getResourceAsStream("/common.properties");//Object.class在web项目无法读取
            InputStream inputStream = PropertiesConifig.class.getResourceAsStream("/common.properties");
            properties.load(inputStream);
            PropertyConfigurator.configure(properties);
        } catch (Exception e) {
            System.out.println("配置文件读取错误");
        }
    }

    public static String getFileRoot() {
        return properties.getProperty("fileRoot");
    }

    public static String getFileLogicRoot() {
        return properties.getProperty("fileLogicRoot");
    }
}
