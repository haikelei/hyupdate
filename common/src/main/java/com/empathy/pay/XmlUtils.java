package com.empathy.pay;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author tybest
 * @date 2017/8/20 13:56
 * @email zhoujian699@126.com
 * @desc XML操作
 **/
public final class XmlUtils {

    public static String toXml(Object obj) {
        StringWriter sw = new StringWriter();
        JAXBContext ctx;
        try {
            ctx = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }


    public static final Object toObject(Class<?> clazz, String xmlStr) {
        Object obj = null;
        JAXBContext ctx;
        try {
            ctx = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            StringReader reader = new StringReader(xmlStr);
            obj = unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static Map<String, String> doXMLParse(String strxml) throws JDOMException, IOException {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if (null == strxml || "".equals(strxml)) {
            return null;
        }
        Map<String, String> m = new HashMap<>();
        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List<?> list = root.getChildren();
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List<?> children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }
            m.put(k, v);
        }
        //关闭流
        in.close();
        return m;
    }

    public static String getChildrenText(List<?> children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator<?> it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List<?> list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }
        return sb.toString();
    }
}
