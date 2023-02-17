package com.example.ratelimitiing.Configure;

import com.example.ratelimitiing.interceptors.RateLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*Add Spring MVC lifecycle interceptors for pre- and post-processing of controller method invocations and resource handler requests. Interceptors can be registered to apply to all requests or be limited to a subset of URL pattern
 * */

public class RateLimitingInterceptorAppConfig implements WebMvcConfigurer {

    @Autowired
    RateLimitInterceptor rateLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitInterceptor)
    }
}
