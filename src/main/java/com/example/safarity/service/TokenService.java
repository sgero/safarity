package com.example.safarity.service;

import com.example.safarity.model.Token;
import com.example.safarity.model.Usuario;
import com.example.safarity.repository.ITokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final ITokenRepository tokenRepository;


    public Token getByUsuario(Usuario usuario){
        return tokenRepository.findTopByUsuario(usuario);
    }

    public Token save(Token token){
        return tokenRepository.save(token);
    }

}