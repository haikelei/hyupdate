package com.empathy.utils;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.servlet.ServletInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by dly on 2016/11/3.
 */
public class XmlUtil {
    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     * @param strxml
     * @return
     * @throws
     * @throws IOException
     */
    public static Map doXMLParse(String strxml) throws Exception {
        if(null == strxml || "".equals(strxml)) {
            return null;
        }

        Map m = new HashMap();
        InputStream in = String2Inputstream(strxml);
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while(it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if(children.isEmpty()) {
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

    /**
     * 获取子结点的xml
     * @param children
     * @return String
     */
    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if(!children.isEmpty()) {
            Iterator it = children.iterator();
            while(it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if(!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }
    public static InputStream String2Inputstream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }



    public static String readXML(ServletInputStream servletInputStream) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(servletInputStream));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }




    public static String getXMLValueByNode(String nodeName, String Xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            StringReader stringReader = new StringReader(Xml);
            org.w3c.dom.Document document = builder.parse(new InputSource(stringReader));
            org.w3c.dom.Element rootElement = document.getDocumentElement();
            NodeList bookNodes = rootElement.getChildNodes();
            for (int i = 0; i < bookNodes.getLength(); i++) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) bookNodes.item(i);
                if (element.getNodeName().equals(nodeName)) {
                    return element.getTextContent();
                }
            }
        } catch (Exception e) {
           return null;
        }
        return null;
    }

}
