package com.ma.pedidos.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ma.pedidos.entity.Producto;
import com.ma.pedidos.error.ApiError;
import com.ma.pedidos.service.ProductService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired 
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<Producto> createProduct(@RequestBody Producto producto){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.saveOrUpdate(producto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Producto> updateProduct(@RequestBody Producto producto, @PathVariable("id") Integer id){
		return productService.findById(id)
			   .map(p -> {
				   	producto.setId(p.getId());
				   	return ResponseEntity.ok().body(this.productService.saveOrUpdate(producto));
				})
			   .orElse(ResponseEntity.notFound().build());			   
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOneProduct(@PathVariable("id") Integer id){
		Optional<Producto> optProducto = this.productService.findById(id);
		 if(optProducto.isPresent()) {
			 return ResponseEntity.ok(optProducto.get());
		 }else {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError("Producto no encontrado"));
		 }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id){
		return productService.findById(id)
				   .map(p -> {
					   if(this.productService.hasPedidosAssociated(p.getId())) {
						   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError("El producto no puede ser eliminado, tiene pedidos asociados"));
					   }else {
						   this.productService.delete(id);
						   return ResponseEntity.noContent().build();
					   }
				   })
				   .orElse(ResponseEntity.notFound().build());
	}
}
