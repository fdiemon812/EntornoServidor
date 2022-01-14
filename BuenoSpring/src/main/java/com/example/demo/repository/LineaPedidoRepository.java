package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Usuario;

@Repository
public interface LineaPedidoRepository extends JpaRepository<LineaPedido, Integer >{

}