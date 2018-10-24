package com.empathy.security;


import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 1、不支持泛型，×
 * http://localhost:8080/youxi-web-api/swagger-ui.html
 *
 * @author tybest
 * @date 2017/2/18 14:34
 * @email
 */
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket API() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiDescriptionOrdering(null)
                .groupName("api")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.empathy.web"))
                //.paths(Predicates.or(PathSelectors.regex("/api/.*")))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        Contact contact = new Contact("tybest", "www.tybest.com", "331344988@qq.com");
        List<VendorExtension> extensions = new ArrayList<VendorExtension>();
        ApiInfo info = new ApiInfo("API接口",
                "统一请求头: {<br/>" +
                        "platform  :  1(ios)/2(android), <br/>" +
                        "token  :  '登录后提供, 000000默认不校验登录',<br/>" +
                        "version  :  '1.0.0',<br/>" +
                        "uid  :  123456(登录后提供,用户ID),<br/>" +
                        "accesstime  :  201709091234(请求时间戳),5分钟内有效<br/>" +
                        "} <br/>"
                        + "统一响应最外层：{<br/>" +
                        "code:  200(成功)/501(请登录)/502(业务错误)/500(系统错误),<br/>" +
                        "msg:  '错误信息',<br/>" +
                        "rows:  '响应数据，接口对应的Response，不体现数组形式',<br/>" +
                        "total:  0（总行数）<br/>" +
                        "}",
                "1.0",
                "http://127.0.0.1:8080/",
                contact,
                "TYB",
                "www.tybest.com", extensions);
        return info;
    }

}
