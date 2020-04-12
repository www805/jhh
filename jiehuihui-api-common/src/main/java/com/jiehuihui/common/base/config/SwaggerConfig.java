package com.jiehuihui.common.base.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration  //必须存在，spring类
@EnableSwagger2 //必须存在，swagger2 核心类
@EnableWebMvc   //必须存在，MVC包
//必须扫描要生成API文档的 Controller包
@ComponentScan(basePackages = {"com.jiehuihui.admin.controller"})
public class SwaggerConfig {

    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select() // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.any())// 对所有api进行监控
                //不显示错误的接口地址
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
                .paths(PathSelectors.regex("/.*"))// 对根下所有路径进行监控
                .build();
    }

    /***
     * 编写开发者信息
     * 网站AIP接口信息
     * @return
     */
    private ApiInfo apiInfo(){
        Contact contact = new Contact("ZJL",
                "http://www.jiehuihui.com",
                "jiehuihui@163.com");
        return new ApiInfoBuilder()
                .title("街惠惠")
                .description("街惠惠API接口描述")
                .contact(contact)
                .version("0.0.1")
                .build();
    }
}
