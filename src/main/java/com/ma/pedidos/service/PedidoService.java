package com.ma.pedidos.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ma.pedidos.entity.Pedido;
import com.ma.pedidos.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired 
	private PedidoRepository pedidoRepository;
	
	
	public Pedido saveOrUpdate(Pedido pedido) {
		pedido.generarOrden();
		return this.pedidoRepository.save(pedido);
	}
	
	public List<Pedido> findByFecha(Date date){
		return this.pedidoRepository.findByFecha(date);
	}
}
