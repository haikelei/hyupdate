package com.empathy.security;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author tybest
 * @date 2017年3月8日 下午1:00:30
 * @email tybest@126.com
 * @desc
 */
public class SwaggerBean extends Docket {

    public SwaggerBean() {
        this(DocumentationType.SWAGGER_2);
    }

    public SwaggerBean(DocumentationType documentationType) {
        super(documentationType);
    }

}
