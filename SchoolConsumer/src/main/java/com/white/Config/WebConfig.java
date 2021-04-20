package com.white.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/2 14:49
 */
@Configuration
@SuppressWarnings("all")
@ConfigurationProperties(prefix = "login")
public class WebConfig extends WebMvcConfigurationSupport {
    private String redirectUrl;

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Resource
    private MyHandlerInterceptor interceptor;

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", redirectUrl);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**").order(1);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("/**")
                .addResourceLocations("classpath:/resources")
                .addResourceLocations("classpath:/static")
                .addResourceLocations("classpath:/templates")
                .addResourceLocations("classpath:/public");
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
