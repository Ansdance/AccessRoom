package com.ansar.trainingcourse.config;

import com.ansar.trainingcourse.model.service.UserService;
import io.swagger.models.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * Configure paths allowed for different users authorities and configure user authentication method
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final AccessDeniedHandler accessDeniedHandler;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserService userService,
                             AccessDeniedHandler accessDeniedHandler,
                             PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.accessDeniedHandler = accessDeniedHandler;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/v2/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .antMatchers(String.valueOf(HttpMethod.GET), "/user/info", "/api/**", "/webjars/**")
                .hasAuthority("SCOPE_read")
                .antMatchers(String.valueOf(HttpMethod.POST), "/api")
                .hasAuthority("SCOPE_write")
                .antMatchers("/js/**", "/images/**", "/css/**", "/index", "/", "/access-denied", "/favicon.ico", "/error/**")
                .permitAll()
                .antMatchers("/users/**","/room/add","/room","/room/**","/room/delete/**","swagger-ui.html", "/spring-security-rest/api/v2/api-docs")
                .hasAuthority("ADMIN")
                .antMatchers("/profile")
                .hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/registration", "/login")
                .anonymous()
                .anyRequest()
                .denyAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .usernameParameter("username")
                .defaultSuccessUrl("/profile")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }
    //For unlock swagger, cause without this swaggger isn t working
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }


}
