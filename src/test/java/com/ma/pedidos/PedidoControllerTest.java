package com.ma.pedidos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.ma.pedidos.controller.PedidoController;
import com.ma.pedidos.entity.Estado;
import com.ma.pedidos.entity.Pedido;
import com.ma.pedidos.entity.PedidoDet;
import com.ma.pedidos.entity.Producto;
import com.ma.pedidos.service.PedidoService;

import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.Matchers.*;



public class PedidoControllerTest {
	
	
	
	
	private static final String BASE_URI = "/pedidos";
	
	
	private WebTestClient webTestClient;
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		
	}
	
	
	@BeforeEach
	public void setUp() {
		 
	}
	

	@Test
	public void when_create_pedido_then_return_201_with_pedido_created_in_body() {
	
		
		
		

		PedidoDet det1 = new PedidoDet(1, 1, new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D));
		PedidoDet det2 = new PedidoDet(1, 1, new Producto(1,"Jamon y Morrones", "Pizza Jamon y Morrones Chica", "Fabulosa Pizza Jamon y Morrones Chica", 700D));
		PedidoDet det3 = new PedidoDet(1, 1, new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D));
		Pedido pedido = new Pedido(1, "Av. Rivadavia 700", "emerica@gmail.com", "1598746687", "21:00", new Date(), false, Estado.PENDIENTE, 1000D, Arrays.asList(det1, det2, det3));
		
		RestAssured
			.given()
			.baseUri(BASE_URI)
			.and()
			.body(pedido)
			.when()
			.post()
			.then()
			.statusCode(is(equalTo(HttpStatus.CREATED)))
			.body("id", equalTo(1), 
				  "direccion", equalTo("Av. Rivadavia 700"), 
				  "email", equalTo("emerica@gmail.com"),
				  "telefono", equalTo("1598746687"));
//			.body("lotto.lottoId", equalTo(5),"lotto.winners.winnerId", hasItems(23, 54));
	}

}
