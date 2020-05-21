package com.jiehuihui.shiro;


import org.apache.shiro.session.SessionListener;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 安全管理器
     * @param myShiroRealm
     * @param ehCacheManager
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("myShiroRealm") ShiroRealm myShiroRealm, @Qualifier("ehCacheManager") RedisCacheManager ehCacheManager, @Qualifier("sessionManager") DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm);//配置自定义验证类
        securityManager.setCacheManager(ehCacheManager);//配置缓存
        securityManager.setSessionManager(sessionManager);//配置自定义session管理，使用redis
        return securityManager;
    }


    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;

    /**
     * 1.redis的控制器，操作redis
     */
    @Bean(value = "redisManager")
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * 2.sessionDao
     */
    @Bean(value = "redisSessionDAO")
    public RedisSessionDAO redisSessionDAO(@Qualifier("redisManager") RedisManager redisManager) {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(redisManager);
        //session在redis中的保存时间,最好大于session会话超时时间
        sessionDAO.setExpire(12000);
        return sessionDAO;
    }

    /**
     * 3.会话管理器
     */
    @Bean(value = "sessionManager")
    public DefaultWebSessionManager sessionManager(@Qualifier("redisSessionDAO") RedisSessionDAO redisSessionDAO) {
        CustomSessionManager sessionManager = new CustomSessionManager();

        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        //配置监听
        listeners.add(sessionListener());
        sessionManager.setSessionListeners(listeners);

        //配置redisSessionDAO
        sessionManager.setSessionDAO(redisSessionDAO);
        //禁用cookie
        sessionManager.setSessionIdCookieEnabled(false);
        //禁用url重写   url;jsessionid=id
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    /**
     * 4.设置缓存
     * @return
     */
    @Bean(value = "ehCacheManager")
    public RedisCacheManager ehCacheManager(@Qualifier("redisManager") RedisManager redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        //redis中针对不同用户缓存
        redisCacheManager.setPrincipalIdFieldName("username");
        //用户权限信息缓存时间
        redisCacheManager.setExpire(200000);
        return redisCacheManager;
    }


    /**
     * 创建自定义验证类
     * @return
     */
    @Bean(value = "myShiroRealm")
    public ShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }




    /**
     * shiro工厂过滤
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager, @Qualifier("kickoutSessionControlFilter") KickoutSessionControlFilter kickoutSessionControlFilter) {
//        System.out.println("shiro工厂过滤");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //自定义拦截器限制并发人数,参考博客：
        LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
        //限制同一帐号同时在线的个数
        filtersMap.put("kickout", kickoutSessionControlFilter);
        //统计登录人数
        shiroFilterFactoryBean.setFilters(filtersMap);



        /**
         * anon  无序认证（登录）可以访问
         * authc  必须认证才可以访问
         * user  如果使用rememberMe的功能可以直接访问
         * perms 该资源必须得到资源权限才可以访问
         * role 该资源必须得到角色权限才可以访问
         */

        //添加shiro内置过滤器
        Map<String, String> filterMap = new LinkedHashMap<String, String>();

        //设置无需登录就可以访问
        filterMap.put("/static/**", "anon");
        filterMap.put("/img/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/templates/**", "anon");
        filterMap.put("/loginCaChe", "anon");
        filterMap.put("/logout", "anon");
        filterMap.put("/ai", "anon");
        filterMap.put("/getai", "anon");
        filterMap.put("/webjars/**", "anon");

        //设置必须要登录才可以访问的页面
//        filterMap.put("/**", "authc");
        filterMap.put("/admin", "authc");
        filterMap.put("/base/tobasegninfo", "authc");
        filterMap.put("/base/tobasetype", "authc");
//        filterMap.put("/ac/**", "authc");

        //授权过滤器
//        filterMap.put("/getUser", "perms[user:getUser]");

        //设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //成功调到的页面
        shiroFilterFactoryBean.setSuccessUrl("/admin");
        //未授权界面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setUnauthorizedUrl("/home/getHomegg");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }




    /**
     * 配置session监听
     * @return
     */
    @Bean("sessionListener")
    public ShiroSessionListener sessionListener(){
        ShiroSessionListener sessionListener = new ShiroSessionListener();
        return sessionListener;
    }


    /**
     * 并发登录控制
     * @return
     */
    @Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter(@Qualifier("ehCacheManager") RedisCacheManager ehCacheManager, @Qualifier("sessionManager") DefaultWebSessionManager sessionManager){
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        //用于根据会话ID，获取会话进行踢出操作的；
        kickoutSessionControlFilter.setSessionManager(sessionManager);
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        kickoutSessionControlFilter.setCacheManager(ehCacheManager);
        //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；
        kickoutSessionControlFilter.setKickoutAfter(false);
        //同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
        kickoutSessionControlFilter.setMaxSession(1);
        //被踢出后重定向到的地址；
        kickoutSessionControlFilter.setKickoutUrl("/login?kickout=1");
        return kickoutSessionControlFilter;
    }


}
