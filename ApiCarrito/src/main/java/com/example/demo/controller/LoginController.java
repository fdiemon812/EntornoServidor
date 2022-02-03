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
	
	
	
	
	
	/**
	 * Nos dirige a la página de inicio o LOGIN e invalida cualquier sesión existente.
	 * @param model
	 * @return login.html
	 */
//	@GetMapping({"/", "/login"})
//	public String login(Model model) {
//		sesion.invalidate();
//		model.addAttribute("usuario", new Usuario());
//
//	return "login";	
//	}
//	
	
	/**
	 * Nos manda al menú principal de la aplicación. Si no hay usuario logado nos devuelve al login. 
	 * @return seleccion.html. Login si no hay usuario logado
	 */
//	@GetMapping("/login/seleccion")
//	public String seleccionGET() {
//		String result="seleccion";
//		if(sesion.getAttribute("usuario")==null){
//			sesion.invalidate();
//			result="redirect:/login";
//		
//		}
//		
//		
//		
//		
//		return result;
//		
//	}
	

	

	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Nos muestra un resumen del pedido. Si no hay usuario logado nos devuelve al login
	 * @param model
	 * @return
	 */
	@PostMapping("/login/resumen")
	public String resumenPedido(Model model) {
		
		
		String result="resumen";
		
		if(sesion.getAttribute("usuario")==null){
			sesion.invalidate();
			result="redirect:/login";
		
		}else {
			
		Usuario userLogado = (Usuario) sesion.getAttribute("usuario");
		Pedido pedido = pedService.findPedido(userLogado);		
		model.addAttribute("usuario", userLogado);
		model.addAttribute("listaPedido", pedido.getListaLineaPedido());
		model.addAttribute("pedido", pedido);
		
		
		}
		
		
		
		return result;
	}
	
	
/**
 * Saca la factura del pedido realizado, junto al envio, con todos los datos correspondientes. 
 * @param model
 * @param envio
 * @param nombre
 * @param apellidos
 * @param direccion
 * @param mail
 * @param tlf
 * @return
 */
	@PostMapping("/login/factura")
	public String facturaPedido(Model model, 
			@RequestParam(name="envio") int envio, 
			@RequestParam(name="nombre") String nombre, 
			@RequestParam(name="apellidos") String apellidos, 
			@RequestParam(name="direccion") String direccion, 
			@RequestParam(name="mail") String mail, 
			@RequestParam(name="tlf") String tlf) {
		
		
		String result="factura";
		
		if(sesion.getAttribute("usuario")==null){
			sesion.invalidate();
			result="redirect:/login";
		
		}else {
			
	
		Usuario userLogado = (Usuario) sesion.getAttribute("usuario");
		Pedido pedido = pedService.findPedido(userLogado);		
		model.addAttribute("usuario", userLogado);
		model.addAttribute("listaPedido", pedido.getListaLineaPedido());
		model.addAttribute("gastoEnvio",envio);
		
		Double totalPedido=pedService.calculaPrecioTotal(pedido)+envio;
		pedido.setTotalPedido(totalPedido);
		pedido.setPrecioEnvio(envio);
		pedido.setNombre(nombre);
		pedido.setApellidos(apellidos);
		pedido.setDireccion(direccion);
		pedido.setMail(mail);
		pedido.setTlf(tlf);
		
		pedService.savePedido(pedido);
		userServ.saveUser(userLogado);

		model.addAttribute(pedido);
		model.addAttribute("total",totalPedido);
		
		
		}
		
		
		
		return result;
	}
	
	
	
	/**
	 * Nos muestra el resumen de la factura. Si no hay usuario logado nos manda al login. Si el pedido no tiene articulos lo elimina 
	 * @return seleccion de pedidos
	 */
	@PostMapping("/login/factura/fin")
	public String finFactura() {
		String result="redirect:/login/seleccion";
		Usuario userLogado = (Usuario) sesion.getAttribute("usuario");
		Pedido pedido = pedService.findPedido(userLogado);		
		if(sesion.getAttribute("usuario")==null){
			sesion.invalidate();
			result="redirect:/login";
		
		}
		
		if(pedido.getListaLineaPedido().isEmpty()) {
			userLogado.getListaPedidos().remove(0);
			
		}
		
		return result;
		
	}
	
	
	
	/**
	 * Lista todos los pedidos del usuario. Si no hay usuario logado va al login.
	 * 
	 * @param model
	 * @return pedidos.html
	 */
	@GetMapping("/login/pedidos")
	public String listarPedidos(Model model) {
		String result = "pedidos";
		
		
		if(sesion.getAttribute("usuario")==null){
			sesion.invalidate();
			result="redirect:/login";
		
		}else {
			Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		

			model.addAttribute("listaPedidos", usuario.getListaPedidos());
					
			model.addAttribute("usuario", usuario);
			
			
		}
		
		return result;
	}

	
	/**
	 * 
	 *  Nos lleva a una pantalla de edicion del pedido concreto, localizado por ID. Si no tiene usuario logado devielve al login
	 * 
	 * @param id
	 * @param model
	 * @return pagina de editar.
	 */
	@GetMapping("/login/editar/{id}")
	public String editarPedido(@PathVariable int id, Model model) {
		
		String result="editar";
		
		if(sesion.getAttribute("usuario")==null ){
			sesion.invalidate();
			result="redirect:/login";
		
		}else {
		
		Pedido pedido = pedService.findPedido(id, (Usuario) sesion.getAttribute("usuario") );
		model.addAttribute("pedido", pedido);
		}
		
		
		return result;
	}
	
	
	/**
	 * 
	 * Se recogen los datos del envio y modificas cada producto del envio. Si metes cantidades negativas no las guarda. 
	 * @param model
	 * @param nombre
	 * @param apellidos
	 * @param direccion
	 * @param mail
	 * @param tlf
	 * @param idEnviado
	 * @param cantidades
	 * @return
	 */
	@PostMapping("/login/editar/submit")
	public String editadoPedido(Model model, 
			 
			@RequestParam(name="nombre") String nombre, 
			@RequestParam(name="apellidos") String apellidos, 
			@RequestParam(name="direccion") String direccion, 
			@RequestParam(name="mail") String mail, 
			@RequestParam(name="tlf") String tlf,
			@RequestParam(name="idEnviado") int idEnviado,
			@RequestParam(name="cantidades") int[] cantidades){
		
		String result="redirect:/login/pedidos";
	

		if(sesion.getAttribute("usuario")==null ){
			sesion.invalidate();
			result="redirect:/login";
		
		}else {
		
	Pedido pedido = pedService.findPedido(idEnviado, (Usuario) sesion.getAttribute("usuario") );

		
			pedido.setNombre(nombre);
			pedido.setApellidos(apellidos);
			pedido.setDireccion(direccion);
			pedido.setMail(mail);
			pedido.setTlf(tlf);
			
			
			int i=0;
			
			
			for (LineaPedido linea : pedido.getListaLineaPedido()) {
				
				if(cantidades[i]>=0) {
					
					linea.setCantidad(cantidades[i]); 
					i++;
				}
				
			}
			
			
			pedService.savePedido(pedido);
			userServ.saveUser((Usuario) sesion.getAttribute("usuario"));
			
		
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	//A PARTIR DE AQUÍ LOS DE LA API
	//A PARTIR DE AQUÍ LOS DE LA API
	//A PARTIR DE AQUÍ LOS DE LA API
	//A PARTIR DE AQUÍ LOS DE LA API
	//A PARTIR DE AQUÍ LOS DE LA API
	//A PARTIR DE AQUÍ LOS DE LA API
	//A PARTIR DE AQUÍ LOS DE LA API
	//A PARTIR DE AQUÍ LOS DE LA API
	//A PARTIR DE AQUÍ LOS DE LA API
	
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
	public String eliminarPedido(@PathVariable String idUsuario,@PathVariable int idPedido) {
		
		String result="Pedido borrado";
		
					
				pedService.borrarPedido(idUsuario, idPedido);
				
		
		
		
		
		return result;
	}
	
	@PutMapping("/editar/{idPedido}")
	public Pedido editarDatosPedido(@RequestBody DatosUsuarioPedido datos,@PathVariable int idPedido) {
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
	public String eliminarLineaPedido(@PathVariable String idUsuario, @PathVariable int idLinea) {
		

		
		
			pedService.borrarLinea(idUsuario, idLinea);
		
		
		
		
		return "Linea borrada "+idLinea;
	}
	
	@PutMapping("editar/{idUsuario}/linea/{idLinea}")
	public LineaPedido editarLineaPedido(@RequestBody Producto producto,@PathVariable String idUsuario , @PathVariable int idLinea) {
		
		Usuario usuario = userServ.findById(idUsuario);
		
		
		
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
