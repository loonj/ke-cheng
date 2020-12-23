package cn.zhanx.ke.cheng.config;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.WebMvcRequestHandler;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfiguration {

    @Bean(value ="priDocket")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).forCodeGeneration(true)
                .select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("cn.zhanx.ke.cheng.controller.pri"))
                .paths(PathSelectors.any())
                .build().groupName("需要token验证").globalOperationParameters(globalOperation())
                .ignoredParameterTypes(HttpServletResponse.class, HttpServletRequest.class);
    }

    @Bean(value ="pubDocket")
    public Docket createRestApi2() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).forCodeGeneration(true)
                .select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("cn.zhanx.ke.cheng.controller.pub"))
                .paths(PathSelectors.any())
                .build().groupName("不需要token验证")
                .ignoredParameterTypes(HttpServletResponse.class, HttpServletRequest.class);
    }


    private List<Parameter> globalOperation() {
        //添加head参数配置start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        //第一个token为传参的key，第二个token为swagger页面显示的值
        tokenPar.name("token").description("token").modelRef(new ModelRef("string"))
                .parameterType("header").required(true).build();
        pars.add(tokenPar.build());

        return pars;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ke-cheng工程")
                .description("ke-cheng工程 V1.0")
                .termsOfServiceUrl("http://localhost:8080/")
                .contact("loonj@163.com")
                .version("1.0")
                .build();
    }
}