package com.ma.pedidos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ma.pedidos.entity.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer>{

}
