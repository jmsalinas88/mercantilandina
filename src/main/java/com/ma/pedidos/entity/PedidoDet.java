package com.ma.pedidos.entity;

public class PedidoDet {

	private Producto producto;
	private Integer cantidad;
	
	public Producto getProducto() {
		return producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
