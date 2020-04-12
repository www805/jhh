package com.jiehuihui;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.jiehuihui.common.utils.IdWorkerJie;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 本服务会写两套接口
 * 1、提供给后台的接口（全套完整）admin
 * 2、提供给业务前端的接口（部分特殊）web
 */
@SpringBootApplication
@EnableSwagger2 //添加swagger2启动注解
@MapperScan({"com.jiehuihui.admin.mapper","com.jiehuihui.web.mapper"})
public class BodyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BodyApplication.class, args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
