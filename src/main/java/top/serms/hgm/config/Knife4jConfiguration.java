package top.serms.hgm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@Slf4j
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        //指定使用Swagger2规范
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //描述字段支持Markdown语法
                        .description("# 黄光明Demo")
                        .termsOfServiceUrl("https://serms.top")
                        .contact(new Contact("SerMs", "https://serms.top", "1839928782@qq.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("WebApi")
                .select()
                //这里指定Controller扫描包路径
                .apis(
                        RequestHandlerSelectors.basePackage(getProjectBasePackage())
                                .or(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                )
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 获取项目包前缀
     */
    private String getProjectBasePackage() {
        String projectBasePackage;
        String currPackageName = this.getClass().getPackage().getName();
        String[] packageItemArr = currPackageName.split("\\.");
        if (packageItemArr.length > 3) {
            projectBasePackage = String.join(".", packageItemArr[0], packageItemArr[1], packageItemArr[2]);
        } else {
            projectBasePackage = currPackageName;
        }
        log.info("Base package to scan api is -> {}", projectBasePackage);
        return projectBasePackage;
    }
}