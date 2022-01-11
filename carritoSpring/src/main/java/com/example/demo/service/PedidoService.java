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

	private ArrayList<Producto> listaProductosDefecto = new ArrayList<>();

	/**
	 * Recibe la posicion de un producto en la BBDD o ID, el pedido donde lo vas a add y la cantidad que quieres add de ese producto.
	 * @param posicion
	 * @param pedido
	 * @param cantidad
	 */
	public void addPedido(int posicion, Pedido pedido, int cantidad) {
		
			
			HashMap<Producto, Integer> map= pedido.getListaProductos();
			

			
			if(map.containsKey(listaProductosDefecto.get(posicion))) {
				
				int cantidadOld=map.get(listaProductosDefecto.get(posicion));
				int cantidadNew= cantidadOld+cantidad;
				map.put(listaProductosDefecto.get(posicion), cantidadNew);

				
			}else {
				
				map.put(listaProductosDefecto.get(posicion), cantidad);
			}
		
	}
	
	/**
	 * Recibe un pedido y devuelve el precio total. 
	 * @param pedido
	 * @return Double precioToptal
	 */
	public Double calculaPrecioTotal(Pedido pedido) {
		Double result=0.0;
		for (Map.Entry<Producto, Integer> producto : pedido.listaProductos.entrySet()) {
			
			result=result+(( producto.getKey()).getPrecio())*producto.getValue();
		}
		
		return result;
		
	}
	
	/**
	 *  Recibe un usuario y un id de pedido. Borra el pedido con dicho ID del usuario recibido. 
	 * @param usuario
	 * @param id
	 */
	public void borrarPedido(Usuario usuario, int id) {
		Pedido pedido = new Pedido(id);
		
		
		usuario.getListaPedidos().remove(pedido);
		
	}
	
	/**
	 * Recibe un id de pedido y un usuario. Devuelve el pedido dentro del usuario, con dicho ID.
	 * @param id
	 * @param usuario
	 * @return Pedido
	 */
	public Pedido findPedido(int id, Usuario usuario) {
		Pedido pedido = new Pedido(id);
		ArrayList<Pedido> listaPedidos = new ArrayList<>(usuario.getListaPedidos());
		
		return  listaPedidos.get(listaPedidos.indexOf(pedido));
	}
	
	/**
	 * Recibe un usuario. Devuelve
	 * @param usuario
	 * @return
	 */
	public Pedido findPedido(Usuario usuario) {
		return usuario.getListaPedidos().get(0);
		
	}
	/**
	 * Inicia una lista de productos por defecto. 
	 */
	@PostConstruct
	public void intiProductos() {
		listaProductosDefecto.addAll(Arrays.asList(
				new Producto("Mordedor-A", 20.0, 0, "/img/pesa.jpg"),
				new Producto("Mordedor-B", 15.0, 1, "/img/pesa2.png"),
				new Producto("Mordedor-C", 10.0, 2, "/img/pesa3.jpg")));
	}
	
	/**
	 * Devuelve los productos recogidos en nuestra lista por defecto
	 * @return
	 */
	public ArrayList<Producto> findAll() {
		
		return listaProductosDefecto;
		
		
	}
	
}
