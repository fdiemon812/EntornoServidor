package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;

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
