package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private  UsuarioRepository usuarioRepository;

    public String iniciarSesion(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            String token = UUID.randomUUID(). toString();
            usuario.get().setToken(token);
            usuarioRepository.save(usuario.get());
            return token;
        }
        throw new RuntimeException("Usuario no encontrado");
    }

    public boolean esAdmin(String token) {
        Optional<Usuario> usuario = usuarioRepository.findByToken(token);
        return usuario.isPresent() && "ADMIN".equals(usuario.get().getRol());
    }
}
