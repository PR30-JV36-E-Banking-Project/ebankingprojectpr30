package com.example.Ebanking.SpringSecurityConfig;

import com.example.Ebanking.service.UserServiceSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceSecurity userServiceSecurity;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceSecurity);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
////                .passwordEncoder(passwordEncoder)
////                .withUser("user").password(passwordEncoder.encode("123456")).roles("USER")
//                .withUser("user").password("123456").roles("USER")
//                .and()
////                .withUser("admin").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN");
//                .withUser("admin").password("123456").roles("USER", "ADMIN");
//    }
    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**", "/captcha");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/", "/account/**").permitAll()
                .antMatchers("/interTranfer").hasAuthority("ROLE_USER")
                .antMatchers("/teller").hasAuthority("ROLE_TELLER")
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .anyRequest().fullyAuthenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                
                
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true).permitAll()
                .and()
                .csrf()
                .disable()
                .addFilterBefore(new CaptchaAuthenticationFilter("/login", "/login?error2"), UsernamePasswordAuthenticationFilter.class);
    }
}
