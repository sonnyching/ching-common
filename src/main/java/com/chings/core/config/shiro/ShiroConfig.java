package com.chings.core.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Administrator
 * @Date 2018/6/25
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "spring.shiro.filters")
public class ShiroConfig {

    private static final String LOGIN_PATH_VIEW = "/login";
    private static final String LOGIN_PATH_AJAX = "/doLogin";

    private String[] anon;
    private String[] authc;

    private String [] ajaxLoginUrls = new String[]{LOGIN_PATH_AJAX};

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //添加新的拦截器

        Map<String,Filter> filters = new LinkedHashMap<String,Filter>();
        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter(ajaxLoginUrls,LOGIN_PATH_VIEW);
        filters.put("authc",loginAuthenticationFilter);
        shiroFilterFactoryBean.setFilters(filters);

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        //authc 需放在后面，确保/** 在最后
        setArrayToFilterChain(filterChainDefinitionMap,anon,"anon");
        setArrayToFilterChain(filterChainDefinitionMap,authc,"authc");

        shiroFilterFactoryBean.setLoginUrl(LOGIN_PATH_VIEW);
        shiroFilterFactoryBean.setSuccessUrl("/home");

//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public LoginRealm myShiroRealm(){
        LoginRealm myShiroRealm = new LoginRealm();
        return myShiroRealm;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    public void setArrayToFilterChain(Map<String,String> filterChainMap,String[] items,String filterName){
        if(authc != null && items.length > 0){
            for (int i = 0 ; i < items.length ;i++ ){
                if(!StringUtils.isEmpty(items[i])) filterChainMap.put(items[i],filterName);
            }
        }
    }

    public String[] getAnon() {
        return anon;
    }

    public void setAnon(String[] anon) {
        this.anon = anon;
    }

    public String[] getAuthc() {
        return authc;
    }

    public void setAuthc(String[] authc) {
        this.authc = authc;
    }
}
