//package org.fms.auth.main.java.com.wisdom.auth.provider.config;
//
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
///**
// * Created by fp295 on 2018/5/6.
// * 跨域开发环境使用 vue-cli 代理，正式用nginx
// */
////@Order(Ordered.HIGHEST_PRECEDENCE)
////@Component
//public class MyCorsFilter extends CorsFilter {
//    public MyCorsFilter() {
//        super(configurationSource());
//    }
//
//    private static UrlBasedCorsConfigurationSource configurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//
//        source.registerCorsConfiguration("/**", config);
////        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
////        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return source;
//    }
//}
