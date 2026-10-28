package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public class UsuarioRepository {
    @Repository
    public interface UsuarioRepository extends
            JpaRepository<Usuario, Long> {
        Optional<Usuario> findByToken(String token);
        Optional<Usuario> findByemail(String email);
    }
}
