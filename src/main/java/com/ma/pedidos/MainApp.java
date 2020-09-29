package com.ma.pedidos;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.ma.pedidos.entity.Pedido;
import com.ma.pedidos.entity.PedidoDet;
import com.ma.pedidos.entity.Producto;
import com.ma.pedidos.repository.PedidoRepository;
import com.ma.pedidos.repository.ProductRepository;

@SpringBootApplication
public class MainApp {

	public static void main(String[] args) {
		
		SpringApplication.run(MainApp.class, args);
		
		/*ConfigurableApplicationContext context = 
				SpringApplication.run(MainApp.class, args);
		
		PedidoRepository pedidoRepository = context.getBean(PedidoRepository.class);
		ProductRepository productRepository = context.getBean(ProductRepository.class);
		
		Producto p1 = new Producto();
		p1.setDescripcionCorta("JMS Product");
		p1.setPrecioUnitario(500D);
		productRepository.save(p1);
		Producto p2 = new Producto();
		p2.setDescripcionCorta("LC Product");
		p2.setPrecioUnitario(100D);
		productRepository.save(p2);
		
		Pedido pedido = new Pedido();
		pedido.setDescuento(true);
		
		PedidoDet det1 = new PedidoDet();
		det1.setCantidad(1);
		det1.setProducto(p1);
		
		PedidoDet det2 = new PedidoDet();
		det2.setCantidad(5);
		det2.setProducto(p2);
		
		List<PedidoDet> detList = Arrays.asList(det1, det2);
		pedido.setDetalle(detList);
		
		pedidoRepository.save(pedido); */
		
	}

}
