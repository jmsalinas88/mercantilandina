package com.ma.pedidos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.ma.pedidos.entity.PedidoDet;

public interface PedidoDetRepository extends CrudRepository<PedidoDet, Integer>{

	 @Query("SELECT COUNT(*) FROM PedidoDet WHERE PRODUCTO_ID = :idProducto")
	 public Integer hasPedidosAssociated(@Param("idProducto") Integer idProducto);
}
