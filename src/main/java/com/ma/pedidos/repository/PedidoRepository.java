package com.ma.pedidos.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.ma.pedidos.entity.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer>{

	List<Pedido> findByFecha(Date date);
}
