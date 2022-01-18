package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.LineaPedidoRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProductoRepository;

@Service
public class PedidoService {


	
	@Autowired
	private ProductoRepository produRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private LineaPedidoRepository lineaRepo;
	
	private ArrayList<Producto> listaProductosDefecto = new ArrayList<>();
	
	/**
	 * Recibe la posicion de un producto en la BBDD o ID, el pedido donde lo vas a 
	 * add y la cantidad que quieres add de ese producto.
	 * @param posicion
	 * @param pedido
	 * @param cantidad
	 */
	public void addPedido(int posicion, Pedido pedido, int cantidad) {
		
			
		
		LineaPedido nuevaLinea= new LineaPedido(produRepo.findAll().get(posicion-1), pedido);
		
		ArrayList<LineaPedido> lineasPedido=(ArrayList<LineaPedido>) pedido.getListaProductos();
			
		if(lineasPedido.contains(nuevaLinea)) {
			
			int indice=lineasPedido.indexOf(nuevaLinea);
			int cantidadOld=lineasPedido.get(indice).getCantidad();
			int cantidadNueva=cantidadOld+cantidad;
			lineasPedido.get(indice).setCantidad(cantidadNueva);
			System.out.println("Editando la cantidad es "+ (lineasPedido.get(indice)).getCantidad());
		}else {
			nuevaLinea.setCantidad(cantidad);
			lineasPedido.add(nuevaLinea);
			lineaRepo.save(nuevaLinea);
			System.out.println("Creando la cantidad es "+ nuevaLinea.getCantidad());

			
			
		}
		
		
	}
		
//			HashMap<Producto, Integer> map= pedido.getListaProductos();
//			
//
//			
//			if(map.containsKey(listaProductosDefecto.get(posicion))) {
//				
//				int cantidadOld=map.get(listaProductosDefecto.get(posicion));
//				int cantidadNew= cantidadOld+cantidad;
//				map.put(listaProductosDefecto.get(posicion), cantidadNew);
//
//				
//			}else {
//				
//				map.put(listaProductosDefecto.get(posicion), cantidad);
//			}
		
		
		
//		List<Producto> productos=pedido.getListaProductos();
//		
//		if(productos.contains(listaProductosDefecto.get(posicion))) {
//			
//			int cantidadOld=productos.get(posicion).getCantidad();
//			int cantidadNew=cantidadOld+cantidad;
//			productos.get(posicion).setCantidad(cantidadNew);
//			
//		}else {
//			Producto prod = new Producto(listaProductosDefecto.get(posicion).getNombre(), 
//					listaProductosDefecto.get(posicion).getPrecio(),
//					listaProductosDefecto.get(posicion).getId(),
//					listaProductosDefecto.get(posicion).getImg());
//				
//			prod.setCantidad(cantidad);
//			productos.add(prod);
//	}
		
		
		
		
//		LineaPedido nuevaLinea= new LineaPedido(listaProductosDefecto.get(posicion), pedido);
//		
//		ArrayList<LineaPedido> lineasPedido=(ArrayList<LineaPedido>) pedido.getListaProductos();
//			
//		if(lineasPedido.contains(nuevaLinea)) {
//			
//			int indice=lineasPedido.indexOf(nuevaLinea);
//			int cantidadOld=lineasPedido.get(indice).getCantidad();
//			int cantidadNueva=cantidadOld+cantidad;
//			lineasPedido.get(indice).setCantidad(cantidadNueva);
//			System.out.println("Editando la cantidad es "+ (lineasPedido.get(indice)).getCantidad());
//		}else {
//			nuevaLinea.setCantidad(cantidad);
//			lineasPedido.add(nuevaLinea);
//			
//			System.out.println("Creando la cantidad es "+ nuevaLinea.getCantidad());
//
//			
//			
//		}
	
	
	/**
	 * Recibe un pedido y devuelve el precio total. 
	 * @param pedido
	 * @return Double precioToptal
	 */
	public Double calculaPrecioTotal(Pedido pedido) {
		Double result=0.0;
		
		
		
		for (LineaPedido linea : pedido.getListaProductos()) {
			
			result+=(linea.getProducto().getPrecio())*linea.getCantidad();
			
		}
		
		
		
		
		
		
//		for (Producto producto : pedido.getListaProductos()) {
//			
//			result=result+producto.getPrecio()*producto.getCantidad();
//		}
		
		
//		for (Map.Entry<Producto, Integer> producto : pedido.listaProductos.entrySet()) {
//			
//			result=result+(( producto.getKey()).getPrecio())*producto.getValue();
//		}
		
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
		deletePedido(id);
		
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
//	@PostConstruct
//	public void intiProductos() {
//		listaProductosDefecto.addAll(Arrays.asList(
//				new Producto("Mordedor-A", 20.0, 0, "/img/pesa.jpg"),
//				new Producto("Mordedor-B", 15.0, 1, "/img/pesa2.png"),
//				new Producto("Mordedor-C", 10.0, 2, "/img/pesa3.jpg")));
//	}
	
	/**
	 * Devuelve los productos recogidos en nuestra lista por defecto
	 * @return
	 */
	public ArrayList<Producto> findAll() {
		
		ArrayList<Producto> listaProductos = new ArrayList(produRepo.findAll());
		System.out.println(listaProductos.get(1));
		return listaProductos;
		
		
	}
	
	
	public void savePedido(Pedido pedido) {
		pedidoRepo.save(pedido);
	}
	
	public void deletePedido(int id) {
		pedidoRepo.deleteById(id);
	}
	
}
