package com.springboot.Configration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
    @Autowired
    private UrlAccessDecisionManager urlAccessDecisionManager;
    @Autowired
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;
    @Autowired
    private CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .anonymous().disable()
//                .cors().and().httpBasic()
//                .and().authorizeRequests()
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                        o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
//                        o.setAccessDecisionManager(urlAccessDecisionManager);
//                        return o;
//                    }
//                }).and().formLogin().loginProcessingUrl("/login")
//                .usernameParameter("name").passwordParameter("password").permitAll()
//                .failureHandler(new AuthenticationFailureHandler() {
//            @Override
//            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//                httpServletResponse.setContentType("application/json;charset=utf-8");
//                PrintWriter out = httpServletResponse.getWriter();
//                StringBuffer sb = new StringBuffer();
//                sb.append("{\"status\":\"error\",\"msg\":\"");
//                if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
//                    sb.append("用户名或密码输入错误，登录失败!");
//                } else if (e instanceof DisabledException) {
//                    sb.append("账户被禁用，登录失败，请联系管理员!");
//                } else {
//                    sb.append("登录失败!");
//                }
//                sb.append("\"}");
//                out.write(sb.toString());
//                out.flush();
//                out.close();
//            }
//        }).successHandler(new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                httpServletResponse.setContentType("application/json;charset=utf-8");
//                PrintWriter out = httpServletResponse.getWriter();
//                ObjectMapper objectMapper = new ObjectMapper();
//                String s = "success";
//                out.write(s);
//                out.flush();
//                out.close();
//            }
//        }).and().exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint)
//          .and().logout().permitAll()
//          .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
//          // 无需权限 即可访问
//          .antMatchers("/logout").permitAll()
//          // 需要USER角色才可访问
//          .antMatchers("/**").permitAll();
//    }
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
            //.anonymous().disable()
            .cors().and().httpBasic()
            .and().authorizeRequests()
            .and().formLogin().loginProcessingUrl("/login")
            .usernameParameter("name").passwordParameter("password").permitAll()
            .failureHandler(new AuthenticationFailureHandler() {
                @Override
                public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    StringBuffer sb = new StringBuffer();
                    sb.append("{\"status\":\"error\",\"msg\":\"");
                    if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                        sb.append("用户名或密码输入错误，登录失败!");
                    } else if (e instanceof DisabledException) {
                        sb.append("账户被禁用，登录失败，请联系管理员!");
                    } else {
                        sb.append("登录失败!");
                    }
                    sb.append("\"}");
                    out.write(sb.toString());
                    out.flush();
                    out.close();
                }
            }).successHandler(new AuthenticationSuccessHandler() {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            String s = "success";
            out.write(s);
            out.flush();
            out.close();
        }
    }).and().exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler)
            .and().logout().permitAll()
            .and().authorizeRequests().antMatchers("/**").permitAll();
}
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("lisi").password("123456").roles("INSPECT");
        auth.userDetailsService(userInfoService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/test","/css/**", "/js/**", "/*.html", "/img/**", "/fonts/**", "/favicon.ico", "/verifyCode");
    }
}
