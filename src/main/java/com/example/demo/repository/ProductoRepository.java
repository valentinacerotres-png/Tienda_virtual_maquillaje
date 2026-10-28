package com.example.demo.repository;

import com.example.demo.model.Producto;
import org.sprongframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.list;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoria(String categoria);

    List<Producto> findByContaining(String nombre);
}
