package org.fms.auth.provider.config;

/**
 * @version V1.0
 * @ClassName: SiteSecurityConfigurer
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: luozhonghua
 * @date: 2018年7月19日 下午3:25:03
 *
 *EnableZuulProxy 配置Zuul传递令牌
 */
//@EnableZuulProxy
//@Configuration
//@EnableOAuth2Sso
public class SiteSecurityConfigurer{
//        extends
//        WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http)
//            throws Exception {
//        http.antMatcher("/**")
//                .authorizeRequests()
//                .antMatchers("/", "/webjars/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .permitAll()
//                .and()
//                .csrf()
//                .csrfTokenRepository(
//                        CookieCsrfTokenRepository
//                                .withHttpOnlyFalse());
//    }

    /**
     * 传递令牌
     * 当传递令牌时，OAuth2客户端将其接收到的OAuth2令牌转发给资源服务。
     * 由于我们已经声明了@EnableOauth2Sso注解，Spring Boot 会在请求上下文中添加一个OAuth2ClientContext对象，
     * 因此我们可以在客户端应用程序中创建自己的OAuth2RestTemplate
     * 一旦我们配置了这个Bean，上下文就会将访问令牌转发给所请求的服务，并且如果令牌到期了也将会刷新令牌
     * @param resource
     * @param context
     * @return
     */
//    @Bean
//    public OAuth2RestOperations restOperations(
//            OAuth2ProtectedResourceDetails resource,
//            OAuth2ClientContext context) {
//        return new OAuth2RestTemplate(resource, context);
//    }

}
