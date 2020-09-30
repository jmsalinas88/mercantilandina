package com.ma.pedidos.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ma.pedidos.entity.Producto;
import com.ma.pedidos.repository.PedidoDetRepository;
import com.ma.pedidos.repository.ProductRepository;
import com.ma.pedidos.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	@Mock
	private ProductRepository productRepository;
	@Mock
	private PedidoDetRepository pedidoDetRepository;
	
	@InjectMocks
	private ProductService productService;
	
	@Test
	public void when_create_then_return_product_created() {
		
		Producto producto = new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D);
		Mockito.when(this.productRepository.save(any(Producto.class))).thenReturn(producto);
		Producto savedProduct = this.productService.saveOrUpdate(producto);
		assertNotNull(savedProduct);
	}
	
	@Test
	public void when_findById_return_product() {
		
		Producto producto = new Producto(1,"Naponiltana", "Pizza Napolitana Chica", "Fabulosa Pizza Napolitana Chica", 700D);
		Mockito.when(this.productRepository.findById(any(Integer.class))).thenReturn(Optional.of(producto));
		Optional<Producto> optProduct = this.productService.findById(1);
		assertNotNull(optProduct.get());
	}
	
	@Test
	public void when_findById_product_associet_with_pedidos_then_return_true (){
		
		Mockito.when(this.pedidoDetRepository.hasPedidosAssociated(any(Integer.class))).thenReturn(1);
		assertTrue(this.productService.hasPedidosAssociated(1));
	}
}
