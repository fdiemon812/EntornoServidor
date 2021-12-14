package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;

@Service
public class PedidoService {

	private ArrayList<Producto> listaProductosDefecto = new ArrayList<Producto>();


	public void addPedido(int posicion, Pedido pedido, int cantidad) {
		
			
			HashMap<Producto, Integer> map= pedido.getListaProductos();
			
//			pedido.listaProductos.add(listaProductosDefecto.get(posicion));
			
			if(map.containsKey(listaProductosDefecto.get(posicion))) {
				
				int cantidadOld=map.get(listaProductosDefecto.get(posicion));
				int cantidadNew= cantidadOld+cantidad;
				map.put(listaProductosDefecto.get(posicion), cantidadNew);

				
			}else {
				
				map.put(listaProductosDefecto.get(posicion), cantidad);
			}
		
	}
	
	public Double calculaPrecioTotal(Pedido pedido) {
		Double result=0.0;
		for (Map.Entry<Producto, Integer> producto : pedido.listaProductos.entrySet()) {
			
			result=result+(((Producto) producto.getKey()).getPrecio())*producto.getValue();
		}
		
		return result;
		
	}
	
	public Pedido findPedido(int id, Usuario usuario) {
		Pedido pedido = new Pedido(id);
		ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>(usuario.getListaPedidos());
		
		return  listaPedidos.get(listaPedidos.indexOf(pedido));
	}
	
	public void borrarPedido(Usuario usuario, int id) {
		Pedido pedido = new Pedido(id);
		System.out.println(id + "el ID ES");
		System.out.println(pedido.getId() + " borrado en teoria");
		
		usuario.getListaPedidos().remove(pedido);
		
	}
	
	
	public Pedido findPedido(Usuario usuario) {
		return usuario.getListaPedidos().get(0);
		
	}
	
	@PostConstruct
	public void intiProductos() {
		listaProductosDefecto.addAll(Arrays.asList(
				new Producto("Mordedor-A", 20.0, 0, "/img/pesa.jpg"),
				new Producto("Mordedor-B", 15.0, 1, "/img/pesa2.png"),
				new Producto("Mordedor-C", 10.0, 2, "/img/pesa3.jpg")));
	}
	
	
	public ArrayList<Producto> findAll() {
		
		return listaProductosDefecto;
		
		
	}
	
}
