package com.chings.core.config;

import com.chings.core.common.xss.ChingArgumentResolverWrapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2018/6/25
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new ChingArgumentResolverWrapper(new RequestParamMethodArgumentResolver(true)));
    }
}
