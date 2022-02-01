package com.example.demo.controller;




import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;

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
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable String id) {

		ResponseEntity<Usuario> respuesta = ResponseEntity.notFound().build();

		if(userServ.contains(id)) {
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
	public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
		
		ResponseEntity<Producto> respuesta = ResponseEntity.notFound().build();

		if(productoService.contains(id)) {
			Producto producto=productoService.findById(id);
			 respuesta = ResponseEntity.ok(producto);
		}
		return respuesta;
		
	}
	
	
	
	
	

	
	/**
	 * Crea un pedido vacio en el usuario usuario con el id pasado. Devuelve el id del pedido si se ha creado correctamente. 
	 * @return String
	 */
	@GetMapping("/nuevopedido/{idUsuario}")
	public String crearPedido(@PathVariable String idUsuario) {
		
		
		String result="Usuario no existe";
		if(userServ.contains(idUsuario)) {
			
					
			Usuario userLogado = userServ.findById(idUsuario);
			Pedido pedido= new Pedido(userLogado);
			result=pedido.getId()+"";
			userLogado.addListaPedidos(pedido);
			userServ.saveUser(userLogado);
		}
		
		return result;
		
	}
	
	
	@PostMapping("/login/addproducto/{idPedido}")
	public String addProducto(@RequestBody Producto producto,@PathVariable int idPedido) {
		
		String result="Error";
																			
	
			
		
			Pedido pedido = pedService.getPedidoById(idPedido);
			
			
			
			if(productoService.contains(producto.getId()) && producto.getCantidad()>0) {
				pedService.addPedido(producto.getId(), pedido, producto.getCantidad());
				result="Añadido al pedido "+idPedido+" , el producto: "+producto.getId();
			
			
		}
		
		return result;
		
	}
	
	
	
	@GetMapping("/pedido/{idPedido}")
	public Pedido listarPedido(@PathVariable int idPedido){
		StringBuilder stringBuilder = new StringBuilder("[");
		List<LineaPedido> articulos=pedService.getPedidoById(idPedido).getListaLineaPedido();
		
//		int i=0;
//		for (LineaPedido lineaPedido : articulos) {
//			i++;
//			stringBuilder.append(""
//					+ "{"+(char)34+"id_producto"+(char)34+":"+(char)34+lineaPedido.getProducto().getId()+(char)34+", "+
//					+(char)34+"cantidad"+(char)34+":"+(char)34+lineaPedido.getCantidad()+(char)34+", "+
//					+(char)34+"id"+(char)34+":"+(char)34+lineaPedido.getId()+(char)34+"} ");
//			
//			if(i<articulos.size()) {
//				stringBuilder.append(",");
//			}
//		}
//		
//		stringBuilder.append("]");
//	
//		
	
		Pedido pedido = pedService.getPedidoById(idPedido);
		
		
		return pedido;
	}
	
	/**
	 * Localiza el pedido por ID y lo elimina. SI no hay usuario logado devuelve al login. 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/eliminar/{idUsuario}/{idPedido}")
	public String eliminarPedido(@PathVariable String idUsuario,@PathVariable int idPedido) {
		
		String result="Pedido borrado";
		
					
				pedService.borrarPedido(idUsuario, idPedido);
				
		
		
		
		
		return result;
	}
	
	
}
