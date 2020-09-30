package com.ma.pedidos;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ma.pedidos.entity.Estado;
import com.ma.pedidos.entity.Pedido;
import com.ma.pedidos.entity.PedidoDet;
import com.ma.pedidos.entity.Producto;
import com.ma.pedidos.repository.PedidoRepository;
import com.ma.pedidos.service.PedidoService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

	@Mock 
	private PedidoRepository pedidoRepository;
	@InjectMocks 
	private PedidoService pedidoService;
	
	@Test
	public void when_create_then_return_pedido_created() {
		PedidoDet det1 = new PedidoDet(1, 1, new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D));
		PedidoDet det2 = new PedidoDet(1, 1, new Producto(1,"Jamon y Morrones", "Pizza Jamon y Morrones Chica", "Fabulosa Pizza Jamon y Morrones Chica", 700D));
		PedidoDet det3 = new PedidoDet(1, 1, new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D));
		Pedido pedido = new Pedido(1, "Av. Rivadavia 700", "emerica@gmail.com", "1598746687", "21:00", new Date(), false, Estado.PENDIENTE, 1000D, Arrays.asList(det1, det2, det3));
		Mockito.when(this.pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
		assertNotNull(this.pedidoService.saveOrUpdate(pedido));
	}
	
	@Test
	public void when_findByFecha_then_return_listOfPedidos() {
		PedidoDet det1 = new PedidoDet(1, 1, new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D));
		PedidoDet det2 = new PedidoDet(1, 1, new Producto(1,"Jamon y Morrones", "Pizza Jamon y Morrones Chica", "Fabulosa Pizza Jamon y Morrones Chica", 700D));
		PedidoDet det3 = new PedidoDet(1, 1, new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D));
		Pedido pedido1 = new Pedido(1, "Av. Rivadavia 700", "emerica@gmail.com", "1598746687", "21:00", new Date(), false, Estado.PENDIENTE, 1000D, Arrays.asList(det1, det2, det3));
		Pedido pedido2 = new Pedido(1, "Av. Rivadavia 700", "emerica@gmail.com", "1598746687", "21:00", new Date(), false, Estado.PENDIENTE, 1000D, Arrays.asList(det1, det2, det3));
		Pedido pedido3 = new Pedido(1, "Av. Rivadavia 700", "emerica@gmail.com", "1598746687", "21:00", new Date(), false, Estado.PENDIENTE, 1000D, Arrays.asList(det1, det2, det3));
		List<Pedido> pedidosListToReturn = Arrays.asList(pedido1, pedido2, pedido3);
		Mockito.when(this.pedidoRepository.findByFecha(any(Date.class))).thenReturn(pedidosListToReturn);
		List<Pedido> pedidosList = this.pedidoService.findByFecha(new Date());
		assertEquals(pedidosListToReturn, pedidosList);
	}
}
