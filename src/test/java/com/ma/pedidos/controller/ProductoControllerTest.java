package com.ma.pedidos.controller;

import java.util.Optional;
import javax.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import com.ma.pedidos.entity.Producto;
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
public class ProductoControllerTest {

	@MockBean
	private  ProductService productService;
	@LocalServerPort
    private int port;
    private String uri;
 
    @PostConstruct
    public void init() {
        this.uri = "http://localhost:" + port + "/productos/";
    }
    
    @Test
	public void when_create_pedido_then_return_201_with_producto_created_in_body() {
    	
		Producto producto = new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D);
		Mockito.when(this.productService.saveOrUpdate(any(Producto.class))).thenReturn(producto);
	
		with()
		 .body(producto)
		 .header("Content-Type","application/json" )
		 .header("Accept","application/json" )
		 .when()
		 .request("POST", this.uri)
		 .then()
		 .assertThat()
		 .statusCode(is(HttpStatus.CREATED.value()))
		 .body("nombre", equalTo(producto.getNombre()), 
			   "descripcionCorta", equalTo(producto.getDescripcionCorta()),
			   "descripcionLarga", equalTo(producto.getDescripcionLarga()),
			   "id", equalTo(producto.getId()),
			   "precioUnitario", equalTo(producto.getPrecioUnitario().floatValue()));
	}
    
    @Test
    public void when_update_existing_product_then_return_200_with_producto_updateded_in_body() {
    	
    	Producto producto = new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D);
    	Mockito.when(this.productService.findById(any(Integer.class))).thenReturn(Optional.of(producto));
		Mockito.when(this.productService.saveOrUpdate(any(Producto.class))).thenReturn(producto);
		
		with()
		 .body(producto)
		 .header("Content-Type","application/json" )
		 .header("Accept","application/json" )
		 .when()
		 .request("PUT", this.uri.concat(String.valueOf(producto.getId())))
		 .then()
		 .assertThat()
		 .statusCode(is(HttpStatus.OK.value()))
		 .body("nombre", equalTo(producto.getNombre()), 
			   "descripcionCorta", equalTo(producto.getDescripcionCorta()),
			   "descripcionLarga", equalTo(producto.getDescripcionLarga()),
			   "id", equalTo(producto.getId()),
			   "precioUnitario", equalTo(producto.getPrecioUnitario().floatValue()));
    }
    
    @Test
    public void when_update_not_existing_product_then_return_404() {
    	
    	Producto producto = new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D);
    	Mockito.when(this.productService.findById(any(Integer.class))).thenReturn(Optional.empty());
		Mockito.when(this.productService.saveOrUpdate(any(Producto.class))).thenReturn(producto);
		
		with()
		 .body(producto)
		 .header("Content-Type","application/json" )
		 .when()
		 .request("PUT", this.uri.concat(String.valueOf(producto.getId())))
		 .then()
		 .assertThat()
		 .statusCode(is(HttpStatus.NOT_FOUND.value()));
    }
    
    @Test
    public void when_find_one_existing_product_then_return_200_with_product_in_body() {
    	
    	Producto producto = new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D);
    	Mockito.when(this.productService.findById(any(Integer.class))).thenReturn(Optional.of(producto));
    	
    	when()
    	 .get(this.uri.concat(String.valueOf(producto.getId())))
    	 .then()
		 .assertThat()
		 .statusCode(is(HttpStatus.OK.value()))
		 .body("nombre", equalTo(producto.getNombre()), 
			   "descripcionCorta", equalTo(producto.getDescripcionCorta()),
			   "descripcionLarga", equalTo(producto.getDescripcionLarga()),
			   "id", equalTo(producto.getId()),
			   "precioUnitario", equalTo(producto.getPrecioUnitario().floatValue()));
    }
    
    @Test
    public void when_find_one_not_existing_product_then_return_404_with_message_in_body() {
    	
    	Producto producto = new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D);
    	Mockito.when(this.productService.findById(any(Integer.class))).thenReturn(Optional.empty());
    	
    	when()
    	 .get(this.uri.concat(String.valueOf(producto.getId())))
    	 .then()
		 .assertThat()
		 .statusCode(is(HttpStatus.NOT_FOUND.value()))
		 .body("error", equalTo("Producto no encontrado"));
    }
    
    @Test
    public void when_delete_existing_product_then_return_204() {
    	
    	Producto producto = new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D);
    	Mockito.when(this.productService.findById(any(Integer.class))).thenReturn(Optional.of(producto));
    	
    	with()
		 .header("Content-Type","application/json" )
		 .when()
		 .request("DELETE", this.uri.concat(String.valueOf(producto.getId())))
		 .then()
		 .assertThat()
		 .statusCode(is(HttpStatus.NO_CONTENT.value()));
    }
    
    @Test
    public void when_delete_not_existing_product_then_return_404() {
    	
    	Producto producto = new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D);
    	Mockito.when(this.productService.findById(any(Integer.class))).thenReturn(Optional.empty());
    	
    	with()
		 .header("Content-Type","application/json" )
		 .when()
		 .request("DELETE", this.uri.concat(String.valueOf(producto.getId())))
		 .then()
		 .assertThat()
		 .statusCode(is(HttpStatus.NOT_FOUND.value()));
    }
    
    @Test
    public void when_delete_product_associated_with_pedido_then_return_400() {
    	
    	Producto producto = new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D);
    	Mockito.when(this.productService.findById(any(Integer.class))).thenReturn(Optional.of(producto));
    	Mockito.when(this.productService.hasPedidosAssociated(any(Integer.class))).thenReturn(Boolean.TRUE);
    	
    	with()
		 .header("Content-Type","application/json" )
		 .when()
		 .request("DELETE", this.uri.concat(String.valueOf(producto.getId())))
		 .then()
		 .assertThat()
		 .statusCode(is(HttpStatus.BAD_REQUEST.value()))
		 .body("error", equalTo("El producto no puede ser eliminado, tiene pedidos asociados"));
    }
}
