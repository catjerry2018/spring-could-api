package com.xfy.apiall.core.config;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtConfiguration extends GenericFilterBean {


    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        } else {
            String token = "";
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                if(request.getParameter("token")==null){
                    throw new ServletException("Missing or invalid Authorization header");
                }else{
                    token = request.getParameter("token");
                }
            }else{
                    token = authHeader.substring(7);
            }

            try {
                final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
                request.setAttribute("claims", claims);//建议保存写入上下文content
            } catch (final SignatureException e) {
                throw new ServletException("Invalid token");
            }

            chain.doFilter(req, res);
        }
    }
}
