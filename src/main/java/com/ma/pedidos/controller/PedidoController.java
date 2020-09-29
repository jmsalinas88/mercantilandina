package com.ma.pedidos.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ma.pedidos.entity.Pedido;
import com.ma.pedidos.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired 
	private PedidoService pedidoService;
	
	@PostMapping
	public ResponseEntity<Pedido> createPedido(@Validated @RequestBody Pedido pedido){
		if(pedido.getFecha() == null) {
			pedido.setFecha(new Date());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(this.pedidoService.saveOrUpdate(pedido));
	}
	
	@GetMapping
	public ResponseEntity<List<Pedido>> getPedidosByDate(@RequestParam("fecha") String date){
		Date convertedDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			convertedDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(this.pedidoService.findByFecha(convertedDate));
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
