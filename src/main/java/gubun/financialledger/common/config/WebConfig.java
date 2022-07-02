package gubun.financialledger.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry registry)   {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/menu/1").setViewName("index");
        registry.addViewController("/menu/2").setViewName("index");
        registry.addViewController("/menu/3").setViewName("index");
        registry.addViewController("/menu/4").setViewName("index");

        //registry.addViewController("/login").setViewName("login-bootstrap");
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }
}