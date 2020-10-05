/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.SpringSecurityConfig;

import java.io.BufferedReader;
import java.io.IOException;
import static java.net.Proxy.Type.HTTP;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 *
 * @author solid
 */
public class CaptchaAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String processUrl;

    public CaptchaAuthenticationFilter(String defaultFilterProcessesUrl, String failureUrl) {
        super(defaultFilterProcessesUrl);
        this.processUrl = defaultFilterProcessesUrl;
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        if (processUrl.equals(req.getServletPath()) && "POST".equalsIgnoreCase(req.getMethod())) {
            String captcha = req.getSession().getAttribute("captcha_security").toString();
            String verifyCaptcha = req.getParameter("captcha");
            System.out.println(captcha);
            System.out.println(verifyCaptcha);
            //remove from session
            req.getSession().removeAttribute("captcha_security");

            if (captcha != null && !captcha.equals(verifyCaptcha)) {
                unsuccessfulAuthentication(req, res, new InsufficientAuthenticationException("Wrong verification code."));
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        return null;
    }
}
