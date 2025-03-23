package com.javaweb.config;

import com.javaweb.config.filter.JwtTokenFilter;
import com.javaweb.security.CustomSuccessHandler;
import com.javaweb.security.utils.JwtTokenUtil;
import com.javaweb.service.impl.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public JwtTokenFilter jwtTokenFilter(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        return new JwtTokenFilter(userDetailsService, jwtTokenUtil);
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http.csrf().disable()
                        .addFilterBefore(jwtTokenFilter(userDetailsService(),new JwtTokenUtil()), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                        .antMatchers(HttpMethod.POST,"/api/transactions").hasAnyRole("MANAGER","STAFF")
                        .antMatchers(HttpMethod.DELETE,"/api/transactions/{id}").hasRole("MANAGER")
                        .antMatchers(HttpMethod.DELETE,"/api/customer/{ids}").hasRole("MANAGER")
                        .antMatchers("/admin/customer-edit-{id}","/admin/customer-edit").hasAnyRole("MANAGER", "STAFF")
                        .antMatchers(HttpMethod.POST,"/api/assignments").hasRole("MANAGER")
                        .antMatchers(HttpMethod.DELETE,"/api/buildings/{ids}").hasRole("MANAGER")
                        .antMatchers("/admin/user-list","/admin/user-edit","/admin/user-edit-{id}","api/user").hasRole("MANAGER")
                        .antMatchers("/admin/building-edit-{id}","/admin/building-edit").hasAnyRole("MANAGER", "STAFF")
                        .antMatchers("/admin/**").hasAnyRole("MANAGER","STAFF","ADMIN")
                        .antMatchers(HttpMethod.POST,"/api/customer").permitAll()
                        .antMatchers("/login", "/resource/**", "/trang-chu", "/api/**","/register").permitAll()
                .and()
                .formLogin().loginPage("/login").usernameParameter("j_username").passwordParameter("j_password").permitAll()
                .loginProcessingUrl("/j_spring_security_check") //khong dc dieu huong
                .successHandler(myAuthenticationSuccessHandler()) // thanh cong va dieu huong
                .failureUrl("/login?incorrectAccount").and()// sai tk hoac mk
                .logout().logoutUrl("/logout").deleteCookies("JSESSIONID")// log out phai xoa cookies
                .and().exceptionHandling().accessDeniedPage("/access-denied").and()// khong co quyen truy cap
                .sessionManagement().maximumSessions(1).expiredUrl("/login?sessionTimeout");
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){

        return new CustomSuccessHandler();
    }
}
