package com.ma.pedidos.repository;

import org.springframework.data.repository.CrudRepository;
import com.ma.pedidos.entity.Producto;

public interface ProductRepository extends CrudRepository<Producto, Integer>{

}
