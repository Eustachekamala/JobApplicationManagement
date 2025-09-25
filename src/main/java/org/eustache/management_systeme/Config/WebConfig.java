package org.eustache.management_systeme.Config;

import org.eustache.management_systeme.Interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoggingInterceptor loggingInterceptor;
    @Autowired
    public WebConfig(LoggingInterceptor loggingInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(loggingInterceptor)
               .addPathPatterns("/api/v1/**")
               .excludePathPatterns("/api/v1/auth/**");
    }
}
