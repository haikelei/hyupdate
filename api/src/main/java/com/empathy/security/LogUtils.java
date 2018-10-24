package com.empathy.security;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tybest
 * @date 2017/10/21 15:10
 * @email zhoujian699@126.com
 * @desc
 **/
public final class LogUtils {

    private static final Map<String, String> urls = new ConcurrentHashMap<>();

    public static void init() {
        File shopxxXmlFile = null;
        try {
            shopxxXmlFile = new ClassPathResource("/log.xml").getFile();
            Document document = new SAXReader().read(shopxxXmlFile);
            List<Element> elements = document.selectNodes("/apilog/node");
            for (Element element : elements) {
                String desc = element.attributeValue("name");
                String url = element.attributeValue("url");
                urls.put(url, desc);
            }
        } catch (Exception e) {

        }
    }

    /**
     * 获得地址对应的说明
     *
     * @param url
     * @return
     */
    public static String getDesc(String url) {
        if (StringUtils.isNotBlank(url)) {
            return urls.get(url);
        }
        return null;
    }
}
