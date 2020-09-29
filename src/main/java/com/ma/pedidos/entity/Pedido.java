package com.ma.pedidos.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="pedidos_cabecera")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "La direccion no puede ser null")
	@NotEmpty(message = "La direccion no puede ser vacio")
	private String direccion;
	@NotNull(message = "El email no puede ser null")
	@NotEmpty(message = "El email no puede ser vacio")
	private String email;
	@NotNull(message = "El telefono no puede ser null")
	@NotEmpty(message = "El telefono no puede ser vacio")
	private String telefono;
	@NotNull(message = "El horario no puede ser null")
	@NotEmpty(message = "El horario no puede ser vacio")
	private String horario;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private Boolean descuento = false;
	private Estado estado = Estado.PENDIENTE;
	private Double total;
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JoinColumn(name="pedido_cabecera_id")
	@NotNull(message = "Se debe ingresar el detalle de los productos")
	private List<PedidoDet> detalle;

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

	public Estado getEstado() {
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

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setDetalle(List<PedidoDet> detalle) {
		this.detalle = detalle;
	}
	
	private boolean isConDescuento() {
		Integer cantidadArticulos = this.detalle.stream()
												.map(d -> d.getCantidad())
												.mapToInt(Integer::intValue)
												.sum();
		
		return cantidadArticulos > 3;
	}
	
	private void calcularTotal() {
		this.total = this.detalle.stream()
								 .map(d -> d.getCantidad() * d.getProducto().getPrecioUnitario())
								 .mapToDouble(Double::doubleValue)
								 .sum();		
		if(this.isConDescuento()) {
			this.total = this.total - (total * 0.3);
			this.descuento = true;
		}
	}
	
	public void generarOrden() {
		this.calcularTotal();
	}
}
