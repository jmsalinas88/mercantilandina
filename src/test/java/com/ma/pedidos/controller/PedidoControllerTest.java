package com.ma.pedidos.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import com.ma.pedidos.entity.Estado;
import com.ma.pedidos.entity.Pedido;
import com.ma.pedidos.entity.PedidoDet;
import com.ma.pedidos.entity.Producto;
import com.ma.pedidos.service.PedidoService;
import com.ma.pedidos.service.ProductService;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;

import static org.mockito.ArgumentMatchers.*;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo; 

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PedidoControllerTest {
	
	@MockBean
	private PedidoService pedidoService;
	@MockBean
	private  ProductService productService;
	@LocalServerPort
    private int port;
    private String uri;
 
    @PostConstruct
    public void init() {
        this.uri = "http://localhost:" + port + "/pedidos";
    }
	
    @Test
	public void when_create_pedido_then_return_201_with_pedido_created_in_body() {
    	
		PedidoDet det1 = new PedidoDet(1, 1, new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D));
		PedidoDet det2 = new PedidoDet(2, 1, new Producto(2,"Jamon y Morrones", "Pizza Jamon y Morrones Chica", "Fabulosa Pizza Jamon y Morrones Chica", 700D));
		PedidoDet det3 = new PedidoDet(3, 1, new Producto(3,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D));
		Pedido pedido = new Pedido(1, "Av. Rivadavia 700", "emerica@gmail.com", "1598746687", "21:00", new Date(), false, Estado.PENDIENTE, 1000D, Arrays.asList(det1, det2, det3));
		Mockito.when(this.pedidoService.saveOrUpdate(any(Pedido.class))).thenReturn(pedido);
		Mockito.when(this.productService.findById(any(Integer.class))).thenReturn(Optional.of(new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D)));
		
		with()
		 .body(pedido)
		 .header("Content-Type","application/json" )
		 .header("Accept","application/json" )
		 .when()
		 .request("POST", this.uri)
		 .then()
		 .assertThat()
		 .statusCode(is(HttpStatus.CREATED.value()))
		 .body("direccion", equalTo(pedido.getDireccion()), 
			   "email", equalTo(pedido.getEmail()),
			   "telefono", equalTo(pedido.getTelefono()),
			   "horario", equalTo(pedido.getHorario()),
			   "estado", equalTo(pedido.getEstado().toString()));
	}
    
    @Test
   	public void when_create_with_not_existing_product_then_return_400() {
    	
   		PedidoDet det1 = new PedidoDet(1, 1, new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D));
   		PedidoDet det2 = new PedidoDet(2, 1, new Producto(2,"Jamon y Morrones", "Pizza Jamon y Morrones Chica", "Fabulosa Pizza Jamon y Morrones Chica", 700D));
   		PedidoDet det3 = new PedidoDet(3, 1, new Producto(3,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D));
   		Pedido pedido = new Pedido(1, "Av. Rivadavia 700", "emerica@gmail.com", "1598746687", "21:00", new Date(), false, Estado.PENDIENTE, 1000D, Arrays.asList(det1, det2, det3));
   		Mockito.when(this.pedidoService.saveOrUpdate(any(Pedido.class))).thenReturn(pedido);   		
   		Mockito.when(this.productService.findById(any(Integer.class))).thenReturn(Optional.empty());
   		 
   		with()
   		 .body(pedido)
   		 .header("Content-Type","application/json" )
   		 .header("Accept","application/json" )
   		 .when()
   		 .request("POST", this.uri)
   		 .then()
   		 .assertThat()
   		 .statusCode(is(HttpStatus.BAD_REQUEST.value()))
   		 .body("size()", is(3));
   	}
	
	@Test
	public void when_list_pedidos_by_fecha_then_return_list_of_pedido() {
		
		PedidoDet det1 = new PedidoDet(1, 1, new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D));
		PedidoDet det2 = new PedidoDet(2, 1, new Producto(2,"Jamon y Morrones", "Pizza Jamon y Morrones Chica", "Fabulosa Pizza Jamon y Morrones Chica", 700D));
		PedidoDet det3 = new PedidoDet(3, 1, new Producto(3,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D));
		Pedido pedido1 = new Pedido(1, "Av. Rivadavia 700", "emerica@gmail.com", "1598746687", "21:00", new Date(), false, Estado.PENDIENTE, 1000D, Arrays.asList(det1, det2, det3));
		Pedido pedido2 = new Pedido(2, "Av. Rivadavia 700", "emerica@gmail.com", "1598746687", "21:00", new Date(), false, Estado.PENDIENTE, 1000D, Arrays.asList(det1, det2, det3));
		Mockito.when(this.pedidoService.findByFecha(any(Date.class))).thenReturn(Arrays.asList(pedido1, pedido2));
		
		when()
		.get(this.uri.concat("?fecha=2020-09-29"))
		.then()
	    .assertThat()
	    .statusCode(is(HttpStatus.OK.value()))
	    .body("size()", is(2));
	}
}
