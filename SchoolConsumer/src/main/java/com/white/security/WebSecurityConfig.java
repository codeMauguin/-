package com.white.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/27 0:05
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private MyAuthenticationProvider myAuthenticationProvider;
    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.myAuthenticationProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/stu-role/**").hasAnyAuthority("STU_ROLE")
                .antMatchers("/tea-role/**").hasAnyAuthority("TEA_ROLE", "ADMIN_ROLE")
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN_ROLE")
                .antMatchers("/countInfo/**")
                .hasAnyAuthority("STU_ROLE", "TEA_ROLE", "ADMIN_ROLE")
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/count/**").permitAll()
                .and()
                .formLogin().usernameParameter("countId").passwordParameter("countPwd")
                .loginPage("http://localhost:8888/")
                .loginProcessingUrl("/login").successHandler(loginSuccessHandler).failureForwardUrl("/login/error")
                .permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout()
                .logoutUrl("/logOut")
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
