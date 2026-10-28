package com.example.demo.service;

import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public List<Producto> obtenerPorCategoria(String categoria) {
        if (categoria != null && !categoria.isEmpty()) {
            return productoRepository.findByCategoria(categoria);
        }
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto crear(Producto producto, String token) {
        Optional<Usuario> usuario = usuarioRepository.findByToken(token);
        if (usuario.isPresent() && "ADMIN".equals(usuario.get().getRol())) {
            return productoRepository.save(producto);
        }
        throw new RuntimeException("No tienes permiso para agregar productos");
    }

    public Producto actualizar(Long id, Producto productoActualizado, String token) {
        Optional<Usuario> usuario = usuarioRepository.findByToken(token);
        if (usuario.isEmpty() || !"ADMIN".equals(usuario.get().getRol())) {
            throw new RuntimeException("No tienes permiso");
        }

        return productoRepository.findById(id).map(prod -> {
            prod.setNombre(productoActualizado.getNombre());
            prod.setCategoria(productoActualizado.getCategoria());
            prod.setPrecio(productoActualizado.getPrecio());
            prod.setStock(productoActualizado.getStock());
            return productoRepository.save(prod);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void eliminar(Long id, String token) {
        Optional<Usuario> usuario = usuarioRepository.findByToken(token);
        if (usuario.isEmpty() || !"ADMIN".equals(usuario.get().getRol())) {
            throw new RuntimeException("No tienes permiso");
        }
        productoRepository.deleteById(id);
    }
}