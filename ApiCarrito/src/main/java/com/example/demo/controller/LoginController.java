package com.example.demo.controller;




import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ApiError;
import com.example.demo.exception.PedidoNotFoundException;
import com.example.demo.exception.LineaPedidoException;
import com.example.demo.exception.ProductoNotFoundException;
import com.example.demo.exception.UsuarioNotFoundException;
import com.example.demo.model.DatosUsuarioPedido;
import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class LoginController {

	
	/**
	 * Inyecta la sesion
	 */
	@Autowired
	private HttpSession sesion;
	
	
	/**
	 * Inyecta el servicio para trabajar con el usuario
	 */
	@Autowired
	private UsuarioService userServ;
	
	
	/**
	 * Inyecta el servicio para trabajar con el usuario
	 */
	@Autowired
	private ProductoService  productoService;
	
	
	/**
	 * Inyecta el servicio para trabajar con los pedidos
	 */
	@Autowired
	private PedidoService pedService;
	
	
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> findAllUsuarios(){
		
		List<Usuario> usuarios=userServ.findAll();
		
		ResponseEntity<List<Usuario>> respuesta = ResponseEntity.ok(usuarios);
		
		if (usuarios.isEmpty()) {
			respuesta = ResponseEntity.notFound().build();
		} 
		
		return respuesta;
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable String id) throws Exception{

		ResponseEntity<Usuario> respuesta = ResponseEntity.notFound().build();

		if(!userServ.contains(id)) {
			throw new UsuarioNotFoundException(id);
		}else {
			Usuario usuario=userServ.findById(id);
			respuesta = ResponseEntity.ok(usuario);
		}
		return respuesta;
		
	}
	
	@GetMapping("/productos")
	public ResponseEntity<List<Producto>> findAllProducto(){
		
		List<Producto> productos=productoService.findAll();
		
		ResponseEntity<List<Producto>> respuesta = ResponseEntity.ok(productos);
		
		if (productos.isEmpty()) {
			respuesta = ResponseEntity.notFound().build();
		} 
		
		return respuesta;
	}
	
	
	@GetMapping("/producto/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) throws Exception{
		
		ResponseEntity<Producto> respuesta = ResponseEntity.notFound().build();

		if(!productoService.contains(id)) {
			
			throw new ProductoNotFoundException(id);
		}else {
			Producto producto=productoService.findById(id);
			 respuesta = ResponseEntity.ok(producto);
		}
		return respuesta;
		
	}
	
	
	
	
	

	
	/**
	 * Crea un pedido vacio en el usuario usuario con el id pasado. Devuelve el id del pedido si se ha creado correctamente. 
	 * @return String
	 */
	@PostMapping("/nuevopedido/{idUsuario}")
	public String crearPedido(@PathVariable String idUsuario) throws Exception{
		
		
		String result="";
		if(!userServ.contains(idUsuario)) {
			
				throw new UsuarioNotFoundException(idUsuario);	
			
		}else {
			Usuario userLogado = userServ.findById(idUsuario);
			Pedido pedido= new Pedido(userLogado);
			result=pedido.getId()+" = ID pedido";
			userLogado.addListaPedidos(pedido);
			userServ.saveUser(userLogado);
		}
		
		return result;
		
	}
	
	
	@PostMapping("/login/addproducto/{idPedido}")
	public String addProducto(@RequestBody Producto producto,@PathVariable int idPedido) throws Exception{
		
		String result;
																			
	
			if(!pedService.contains(idPedido)) {
				
				throw new PedidoNotFoundException(idPedido);

			}else if(!productoService.contains(producto.getId()) || producto.getCantidad()<=0) {
				
				throw new ProductoNotFoundException(producto.getId());
				
			}else {
				Pedido pedido = pedService.getPedidoById(idPedido);
				pedService.addPedido(producto.getId(), pedido, producto.getCantidad());
				result="Añadido al pedido "+idPedido+" , el producto: "+producto.getId();
			
			
		}
		
		return result;
		
	}
	
	
	
	@GetMapping("/pedido/{idPedido}")
	public Pedido listarPedido(@PathVariable int idPedido) throws Exception{
		
		Pedido pedido;
		if(!pedService.contains(idPedido)) {
			throw new PedidoNotFoundException(idPedido);
		}else {
			
			pedido = pedService.getPedidoById(idPedido);
		}
		
		
		
		return pedido;
	}
	
	
	@GetMapping("/pedidos")
	public ResponseEntity<List<Pedido>> findAllPedidos(){
		
		List<Pedido> pedidos=pedService.findAllPedidos();
		
		ResponseEntity<List<Pedido>> respuesta = ResponseEntity.ok(pedidos);
		
		if (pedidos.isEmpty()) {
			respuesta = ResponseEntity.notFound().build();
		} 
		
		return respuesta;
	}
	
	/**
	 * Localiza el pedido por ID y lo elimina. SI no hay usuario logado devuelve al login. 
	 * @param id
	 * @param model
	 * @return
	 */
	@DeleteMapping("/eliminar/{idUsuario}/{idPedido}")
	public String eliminarPedido(@PathVariable String idUsuario,@PathVariable int idPedido) throws Exception{
		
		String result="Pedido borrado " + idPedido;
		
		
		
		if(!userServ.contains(idUsuario)){
			
			throw new UsuarioNotFoundException(idUsuario);
			
		}else if(!pedService.contains(idPedido)) {
			
			throw new PedidoNotFoundException(idPedido);
			
		}else {
			
			pedService.borrarPedido(idUsuario, idPedido);
				
		}
		
		
		
		
		return result;
	}
	
	@PutMapping("/editar/{idPedido}")
	public Pedido editarDatosPedido(@RequestBody DatosUsuarioPedido datos,@PathVariable int idPedido) throws Exception {
		Pedido pedido;
		
		if(!pedService.contains(idPedido)) {
			throw new PedidoNotFoundException(idPedido);
		}else {
			pedido = pedService.getPedidoById(idPedido);
			pedido.setApellidos(datos.getApellidos());
			pedido.setDireccion(datos.getDireccion());
			pedido.setMail(datos.getMail());
			pedido.setNombre(datos.getNombre());
			pedido.setTlf(datos.getTlf());
			pedService.savePedido(pedido);
		}
			
		
		return pedido;
	}
	
	
	
	
	@GetMapping("/lineas")
	public ResponseEntity<List<LineaPedido>> findAllLineas(){
		
		List<LineaPedido> lineas=pedService.findAllLineas();
		
		ResponseEntity<List<LineaPedido>> respuesta = ResponseEntity.ok(lineas);
		
		if (lineas.isEmpty()) {
			respuesta = ResponseEntity.notFound().build();
		} 
		
		return respuesta;
	}
	
	
	@GetMapping("/linea/{idLinea}")
	public LineaPedido listarLinea(@PathVariable int idLinea) throws Exception{
		
		LineaPedido linea;
		if(!pedService.containsLinea(idLinea)) {
			throw new LineaPedidoException(idLinea);
		}else {
			
			linea = pedService.getLineaById(idLinea);
		}
		
		
		
		return linea;
	}
	
	
	
	@DeleteMapping("/eliminar/{idUsuario}/linea/{idLinea}")
	public String eliminarLineaPedido(@PathVariable String idUsuario, @PathVariable int idLinea) throws Exception {
		

		if(!userServ.contains(idUsuario)) {
			throw new UsuarioNotFoundException(idUsuario);
		}else if(!pedService.containsLinea(idLinea)) {
			throw new LineaPedidoException(idLinea);

		}	else {
			
			pedService.borrarLinea(idUsuario, idLinea);
		}
		
		
		
		
		
		return "Linea borrada "+idLinea;
	}
	
	@PutMapping("editar/{idUsuario}/linea/{idLinea}")
	public LineaPedido editarLineaPedido(@RequestBody Producto producto,@PathVariable String idUsuario , @PathVariable int idLinea) throws Exception{
		
		
		if(!userServ.contains(idUsuario)) {
			throw new UsuarioNotFoundException(idUsuario);
		}else if(!pedService.containsLinea(idLinea)) {
			throw new LineaPedidoException(idLinea);

		}	else {
		Usuario usuario = userServ.findById(idUsuario);
		
		}
		
		return pedService.editarLinea(idLinea, producto.getId(), producto.getCantidad());
	}
	
	
	/**
	 * Para cuando existe un error de un JSON mal formado
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción --> ignora la traza de la excepción
	 */
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ApiError> UsuarioException(UsuarioNotFoundException userException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), userException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
	@ExceptionHandler(PedidoNotFoundException.class)
	public ResponseEntity<ApiError> PedidoException(PedidoNotFoundException pedidoException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), pedidoException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
	@ExceptionHandler(ProductoNotFoundException.class)
	public ResponseEntity<ApiError> ProductoException(ProductoNotFoundException productoException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), productoException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
	@ExceptionHandler(LineaPedidoException.class)
	public ResponseEntity<ApiError> LineaPedidoException(LineaPedidoException lineaException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), lineaException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
	
	/**
	 * Para cuando existe un error de un JSON mal formado
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción --> ignora la traza de la excepción
	 */
	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<ApiError> handleJsonMappingException(JsonMappingException jsonException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), jsonException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
		
	
	
}
