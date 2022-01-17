//package com.example.demo.model;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//public class LineaPedidoId implements Serializable{
//	
//	
//	@Override
//	public int hashCode() {
//		return Objects.hash(pedido, producto);
//	}
//
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		LineaPedidoId other = (LineaPedidoId) obj;
//		return pedido == other.pedido && producto == other.producto;
//	}
//
//
//	private int producto;
//	private int pedido;
//	
//	
//	public LineaPedidoId() {
//	}
//
//}
