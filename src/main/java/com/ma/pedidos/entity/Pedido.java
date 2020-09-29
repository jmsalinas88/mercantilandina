package com.ma.pedidos.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pedidos_cabecera")
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
	private Double total;
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JoinColumn(name="pedido_cabecera_id")
	private List<PedidoDet> detalle = new ArrayList<PedidoDet>();

	public Pedido() {}

	public Integer getId() {
		return id;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getHorario() {
		return horario;
	}

	public Date getFecha() {
		return fecha;
	}

	public Boolean getDescuento() {
		return descuento;
	}

	public String getEstado() {
		return estado;
	}

	public Double getTotal() {
		return total;
	}

	public List<PedidoDet> getDetalle() {
		return detalle;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setDescuento(Boolean descuento) {
		this.descuento = descuento;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setDetalle(List<PedidoDet> detalle) {
		this.detalle = detalle;
	}
}
