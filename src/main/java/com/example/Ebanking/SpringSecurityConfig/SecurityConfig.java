package com.example.Ebanking.SpringSecurityConfig;

import com.example.Ebanking.service.UserServiceSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceSecurity userServiceSecurity;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceSecurity).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**", "/captcha");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/", "/account/**", "/accountRest", "/rest").permitAll()
                .antMatchers("/interTranfer").hasAuthority("ROLE_USER")
                .antMatchers("/teller", "/newAccount").hasAuthority("ROLE_TELLER")
                .antMatchers("/admin", "/list-teller").hasAuthority("ROLE_ADMIN")
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
