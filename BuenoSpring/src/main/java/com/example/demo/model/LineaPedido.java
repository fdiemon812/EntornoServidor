package com.example.demo.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="lineapedido")
public class LineaPedido{
//@IdClass(LineaPedidoId.class)
//public class LineaPedido {
//	
//	@Id
//	@ManyToOne
//	@NotFound(action=NotFoundAction.IGNORE)
//	@JoinColumn(name="pedido_id")
//	private Pedido pedido;
//	
//	@Id
//	@ManyToOne
//	@NotFound(action=NotFoundAction.IGNORE)
//	@JoinColumn(name="producto_id")
//	private Producto producto;
	
	
	
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	
	
	@ManyToOne
	@JoinColumn(name="producto_id")
	private Producto producto;
	
	private static int contador=1;
	
	@Id
	private int id;
	
	@Column(name = "cantidad", nullable = false)
	int cantidad=0;


	
	public LineaPedido() {
		
		this.id=contador;
		contador++;
	};
	
	public LineaPedido(Producto producto, Pedido pedido) {
		
		this.producto=producto;
		this.pedido=pedido;
		this.id=contador;
		contador++;
	}


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	@Override
	public int hashCode() {
		return Objects.hash(pedido, producto);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineaPedido other = (LineaPedido) obj;
		return Objects.equals(pedido, other.pedido) && Objects.equals(producto, other.producto);
	}


	

}
