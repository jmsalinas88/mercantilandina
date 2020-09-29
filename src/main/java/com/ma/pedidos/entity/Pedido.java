package com.ma.pedidos.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String direccion;
	private String email;
	private String telefono;
	private String horario;
	private Date fecha;
	private Boolean descuento;
	private String estado;
	private List<PedidoDet> detalle;
	private Double total;

}
