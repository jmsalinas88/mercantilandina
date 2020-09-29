package com.ma.pedidos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ma.pedidos.entity.Pedido;
import com.ma.pedidos.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired 
	private PedidoService pedidoService;
	
	@PostMapping
	public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido){
		return ResponseEntity.ok(this.pedidoService.saveOrUpdate(pedido));
	}
	
	@GetMapping
	public ResponseEntity<List<Pedido>> getPedidosByDate(@RequestParam("fecha") String date){
		return null;
	}

}
