package web.crawler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * swagger2配置,能否发现接口,就是在这里通过正则表达式匹配的
 */


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket springfoxDocket() {
        return
                new Docket(DocumentationType.SWAGGER_2)
                        .globalOperationParameters(Collections.singletonList(new ParameterBuilder()
                                .name("Version").description("接口版本号").modelRef(new ModelRef("string"))
                                .parameterType("header").required(false).build()))
                        .apiInfo(apiInfo())
                        .pathMapping("/").select()
                        .apis(RequestHandlerSelectors.basePackage("web.crawler"))
                        .paths(regex("^.*(?<!error)$"))
                        .build();
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("爬虫实验")
                //创建人信息
                .contact(new Contact("轻浆","http://baidu.com","保密"))
                .description("爬虫项目接口介绍文档")
                .version("1.0")
                .build();
    }



}
