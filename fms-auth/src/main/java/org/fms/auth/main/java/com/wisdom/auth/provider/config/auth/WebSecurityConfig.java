package org.fms.auth.main.java.com.wisdom.auth.provider.config.auth;


import org.fms.auth.main.java.com.wisdom.auth.provider.config.auth.filter.MyLoginAuthenticationFilter;
import org.fms.auth.main.java.com.wisdom.auth.provider.config.auth.handler.MyLoginAuthSuccessHandler;

import org.fms.auth.main.java.com.wisdom.auth.provider.config.auth.provider.MyAuthenticationProvider;
import org.fms.auth.main.java.com.wisdom.auth.provider.service.BaseUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;

/**
 */
@ConfigurationProperties(prefix = "security")
@Configuration
//@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 自动注入UserDetailsService
    @Autowired
    private BaseUserDetailService baseUserDetailService;




//    private String[] ignoreds;
//
//    public void setIgnored(String ignored) {
//        if(ignored != null && !"".equals(ignored))
//            this.ignoreds = ignored.split(",");
//        else
//            this.ignoreds = new String[]{};
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        System.out.println("===========================org.fms.auth.main.java.com.wisdom.auth.provider configure============================");

        http
//                .addFilterAt(getMyLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 配置登陆页/login并允许访问,
//                .formLogin().loginPage("/login").permitAll()
//
//                // 登出页
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/backReferer")
                // 其余所有请求全部需要鉴权认证,
//                .and()
                .authorizeRequests()
                .antMatchers("/css/**","/js/**","/favicon.ico","/webjars/**","/images/**","/static/**",
                        "/hystrix.stream/**","/info","/error","/health","/env","/metrics","/trace","/dump",
                        "/jolokia","/configprops","/activiti","/logfile","/refresh","/flyway","/liquibase","/loggers","/druid/**",
                        "/oauth/deleteToken","/backReferer","/extractToken","/dept/**","/role/**","/menu/**","/system/**","/client/**","/user/**","/right/**").permitAll()
                .anyRequest().authenticated()
                // 由于使用的是JWT，我们这里不需要csrf
                .and().csrf().disable();
        //只允许单人登陆
        http.sessionManagement().maximumSessions(1).expiredUrl("/login");
    }

    /**
     * 用户验证
     * @param auth
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(myAuthenticationProvider());
    }

    /*@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider org.fms.auth.main.java.com.wisdom.auth.provider = new DaoAuthenticationProvider();
        // 设置userDetailsService
        org.fms.auth.main.java.com.wisdom.auth.provider.setUserDetailsService(baseUserDetailService);
        // 禁止隐藏用户未找到异常
        org.fms.auth.main.java.com.wisdom.auth.provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        org.fms.auth.main.java.com.wisdom.auth.provider.setPasswordEncoder(new BCryptPasswordEncoder(6));
        return org.fms.auth.main.java.com.wisdom.auth.provider;
    }*/

    /**
     * 自定义密码验证
     * @return
     */
    @Bean
    public MyAuthenticationProvider myAuthenticationProvider(){
        MyAuthenticationProvider provider = new MyAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(baseUserDetailService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider.setPasswordEncoder(new BCryptPasswordEncoder(6));
        return provider;
    }

    /**
     * 自定义登陆过滤器
     * @return
     */
//    @Bean
//    public MyLoginAuthenticationFilter getMyLoginAuthenticationFilter() {
//        MyLoginAuthenticationFilter filter = new MyLoginAuthenticationFilter();
//        try {
//            filter.setAuthenticationManager(this.authenticationManagerBean());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        filter.setAuthenticationSuccessHandler(new MyLoginAuthSuccessHandler());
//        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
//        return filter;
//    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}
