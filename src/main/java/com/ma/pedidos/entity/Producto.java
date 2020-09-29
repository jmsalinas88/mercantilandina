package com.ma.pedidos.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcionCorta;
	private String descripcionLarga;
	private Double precioUnitario;
	
	public Producto() {}
	
	public Producto(Integer id,String nombre, String descripcionCorta, String descripcionLarga, Double precioUnitario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcionCorta = descripcionCorta;
		this.descripcionLarga = descripcionLarga;
		this.precioUnitario = precioUnitario;
	}

	public Integer getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public String getDescripcionLarga() {
		return descripcionLarga;
	}
	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
}
