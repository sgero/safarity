package com.example.safarity.security.filter;

import com.example.safarity.model.Usuario;
import com.example.safarity.security.auth.TokenDataDTO;
import com.example.safarity.security.jwt.JWTService;
import com.example.safarity.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTFilterChain extends OncePerRequestFilter {


    @Autowired
    private JWTService jwtService;

    @Autowired
    private UsuarioService usuarioService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {


        final String authHeader = request.getHeader("Authorization");


        //Si viene por una url "/auth" lo dejamos pasar
        if (request.getServletPath().contains("/auth")){
            filterChain.doFilter(request, response);
            return;
        }

        if(authHeader== null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = authHeader.substring(7);
        TokenDataDTO tokenDataDTO = jwtService.extractTokenData(token);

        if(tokenDataDTO!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            Usuario usuario = usuarioService.buscarPorUsername(tokenDataDTO.getUsername());

            if(usuario!= null && !jwtService.isExpired(token)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        usuario.getAlias(),
                        usuario.getPassword(),
                        usuario.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

        filterChain.doFilter(request, response);
    }
}