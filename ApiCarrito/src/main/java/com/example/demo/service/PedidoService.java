package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
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

	@Autowired
	private UsuarioService usuServ;


	/**
	 * Recibe la posicion de un producto en la BBDD o ID, el pedido donde lo vas a
	 * add y la cantidad que quieres add de ese producto.
	 * 
	 * @param posicion
	 * @param pedido
	 * @param cantidad
	 */
	public void addPedido(int posicion, Pedido pedido, int cantidad) {

		LineaPedido nuevaLinea = new LineaPedido(produRepo.findAll().get(posicion - 1), pedido);

		List<LineaPedido> lineasPedido =  pedido.getListaLineaPedido();

		if (lineasPedido.contains(nuevaLinea)) {

			int indice = lineasPedido.indexOf(nuevaLinea);
			int cantidadOld = lineasPedido.get(indice).getCantidad();
			int cantidadNueva = cantidadOld + cantidad;
			lineasPedido.get(indice).setCantidad(cantidadNueva);
		} else {
			nuevaLinea.setCantidad(cantidad);
			lineasPedido.add(nuevaLinea);
		}
		pedido.setTotalPedido(this.calculaPrecioTotal(pedido));
		pedidoRepo.save(pedido);

	}

	/**
	 * Recibe un pedido y devuelve el precio total.
	 * 
	 * @param pedido
	 * @return Double precioToptal
	 */
	public Double calculaPrecioTotal(Pedido pedido) {
		Double result = 0.0;

		for (LineaPedido linea : pedido.getListaLineaPedido()) {

			result += (linea.getProducto().getPrecio()) * linea.getCantidad();

		}

		return result;

	}

	/**
	 * Recibe un usuario y un id de pedido. Borra el pedido con dicho ID del usuario
	 * recibido.
	 * 
	 * @param usuario
	 * @param id
	 */
	public void borrarPedido(String idUsuario, int id) {
		
		Usuario usuario = usuServ.findById(idUsuario);
		int posicion = usuario.getListaPedidos().indexOf(new Pedido(id));
		Pedido pedido =usuario.getListaPedidos().get(posicion);
		usuario.getListaPedidos().remove(pedido);
		usuServ.saveUser(usuario);

	}

	/**
	 * Recibe un id de pedido y un usuario. Devuelve el pedido dentro del usuario,
	 * con dicho ID.
	 * 
	 * @param id
	 * @param usuario
	 * @return Pedido
	 */
	public Pedido findPedido(int id, Usuario usuario) {
		Pedido pedido = new Pedido(id);
		ArrayList<Pedido> listaPedidos = new ArrayList<>(usuario.getListaPedidos());

		return listaPedidos.get(listaPedidos.indexOf(pedido));
	}

	/**
	 * Recibe un usuario. Devuelve su ultimo pedido.
	 * 
	 * @param usuario
	 * @return
	 */
	public Pedido findPedido(Usuario usuario) {
		 
		return usuario.getListaPedidos().get(0);

	}
	

	/**
	 * Devuelve los productos recogidos en la BBDD
	 * 
	 * @return
	 */
	public ArrayList<Producto> findAll() {

		
		return  (ArrayList<Producto>) produRepo.findAll();

	}
	/**
	 * Recibe un pedido. Guarda o actualiza un pedido en la BBDD
	 * @param pedido
	 */
	public Pedido savePedido(Pedido pedido) {
		pedidoRepo.save(pedido);
		
			
		Pedido aux = pedidoRepo.getById(pedido.getId());
		aux.setListaLineasPedidos(pedido.getListaLineaPedido());
		
		return pedidoRepo.save(pedido);
	}

	/**
	 * Devuelve un pedido pasando ID
	 * @param id
	 * @return
	 */
	public Pedido getPedidoById(int id) {
		
		return pedidoRepo.getById(id);
		
	}
	
	/**
	 * Devuelve true si existe en la bbdd ese pedidoi ID
	 * @param id
	 * @return
	 */
	public boolean contains(int id) {

		return pedidoRepo.existsById(id);
	}

	/**
	 * Devuelve todos los pedidos
	 * @return
	 */
	public List<Pedido> findAllPedidos() {
		return pedidoRepo.findAll();
	}
	
	
	/**
	 * Devuelve todas las lineas de pedidos
	 * 
	 * @return
	 */
	public List<LineaPedido> findAllLineas() {
		return lineaRepo.findAll();
	}

	
	/**
	 * Devuelve true si contiene la liena en la BBDD
	 * @param idLinea
	 * @return
	 */
	public boolean containsLinea(int idLinea) {
		return lineaRepo.existsById(idLinea);
	}

	
	/**
	 * Devuelve una linea segun suy ID
	 * @param idLinea
	 * @return
	 */
	public LineaPedido getLineaById(int idLinea) {
		return lineaRepo.getById(idLinea);
	}

	
	/**
	 * Elimina una linea de la bbdd
	 * @param idUsuario
	 * @param idLinea
	 */
	public void borrarLinea(String idUsuario, int idLinea) {
		
		Usuario usuario = usuServ.findById(idUsuario);
		int idPedido = this.getLineaById(idLinea).getPedido().getId();
		Pedido pedido=getPedidoById(idPedido);
		int posicionLinea = pedido.getListaLineaPedido().indexOf(new LineaPedido(idLinea));	
		LineaPedido linea =pedido.getListaLineaPedido().get(posicionLinea);
		pedido.getListaLineaPedido().remove(linea);
		lineaRepo.delete(linea);
		pedido.setTotalPedido(this.calculaPrecioTotal(pedido));
		this.savePedido(pedido);
				
	}
	
	/**
	 * Edita una linea de la BBDD
	 * @param idLinea
	 * @param idProducto
	 * @param idCantidad
	 * @return
	 */
	public LineaPedido editarLinea(int idLinea, int idProducto, int idCantidad) {
		
		int idPedido = this.getLineaById(idLinea).getPedido().getId();
		Pedido pedido=getPedidoById(idPedido);
		int posicionLinea = pedido.getListaLineaPedido().indexOf(new LineaPedido(idLinea));	
		LineaPedido linea =pedido.getListaLineaPedido().get(posicionLinea);
		
		linea.setCantidad(idCantidad);
		linea.setProducto(produRepo.getById(idProducto));
		lineaRepo.save(linea);
		pedido.setTotalPedido(this.calculaPrecioTotal(pedido));
		this.savePedido(pedido);	
		
		return linea;
	}

}
