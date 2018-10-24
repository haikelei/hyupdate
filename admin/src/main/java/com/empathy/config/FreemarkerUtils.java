package com.empathy.config;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Locale;
import java.util.Map;

/**
 * @author tybest
 * @date 2017/11/19 14:09
 * @email zhoujian699@126.com
 * @desc
 **/
public final class FreemarkerUtils {

    private static final String ENCODING = "UTF-8";

    /**
     * ftl文件静态化
     *
     * @param context
     * @param data
     * @param templatePath
     * @param targetHTMLPath
     */
    public static void buildHTML(ServletContext context, Map<String, Object> data, String templatePath,
                                 String targetHTMLPath) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setServletContextForTemplateLoading(context, "/");
        cfg.setEncoding(Locale.getDefault(), ENCODING);
        Writer out = null;
        try {
            Template template = cfg.getTemplate(templatePath, ENCODING);
            String htmlPath = context.getRealPath("/html") + "/" + targetHTMLPath;
            File file = new File(htmlPath);
            if (file.exists()) {
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                template.process(data, out);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
        }

    }
}
