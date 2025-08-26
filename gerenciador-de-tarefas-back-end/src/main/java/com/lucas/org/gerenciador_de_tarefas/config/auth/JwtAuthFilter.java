package com.lucas.org.gerenciador_de_tarefas.config.auth;

import com.lucas.org.gerenciador_de_tarefas.JWT.JwtUtils;
import com.lucas.org.gerenciador_de_tarefas.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {



        String header = request.getHeader(JwtUtils.JWT_AUTHORIZATION);
        if (header == null || !header.startsWith(JwtUtils.JWT_BEARER)) {
            logger.info("JWT Token nulo, vazio ou não iniciando com 'Bearer '.");
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(JwtUtils.JWT_BEARER.length());

        if (!JwtUtils.validarToken(token)){
            logger.warn("JWT Token está inválido ou expirado.");
            filterChain.doFilter(request, response);
            return;
        }

        String email = JwtUtils.extractEmail(token);

        toAuthentication(request, email);

        filterChain.doFilter(request, response);

    }

    private void toAuthentication(HttpServletRequest request, String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken
                .authenticated(userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

}
