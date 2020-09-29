package com.ma.pedidos.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pedidos_detalle")
public class PedidoDet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer cantidad;
	@OneToOne
	@JoinColumn(name="producto_id" , referencedColumnName = "id")
	private Producto producto;
	
	public PedidoDet() {}
	
	public PedidoDet(Integer id, Integer cantidad, Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public Integer getId() {
		return id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
