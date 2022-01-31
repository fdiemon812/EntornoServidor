package com.example.demo.controller;




import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	
	/**
	 * Localiza el pedido por ID y lo elimina. SI no hay usuario logado devuelve al login. 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/login/eliminar/{id}")
	public String eliminarPedido(@PathVariable int id, Model model) {
		
		String result="redirect:/login/pedidos";
		
		if(sesion.getAttribute("usuario")==null){
			sesion.invalidate();
			result="redirect:/login";
			}else {
				
				Usuario userLogado = (Usuario) sesion.getAttribute("usuario");
				pedService.borrarPedido(userLogado, id);
			}
		
		
		
		
		return result;
	}
	
	
	/**
	 * Nos devuelve al login
	 * @param usuario
	 * @return login
	 */
	@GetMapping({ "/login/resumen", "/login/factura", "/login/factura/fin", "/login/editar/submit"})
	public String forzarInicio(@ModelAttribute("usuario") Usuario usuario) {
		
		return "redirect:/login";
		
	}
	
	
	/**
	 * Invalida sesión y devuelve al login. 
	 * @return login
	 */
	@GetMapping("/login/logout")
	public String cerrarSesion() {
		
		sesion.invalidate();
		return "redirect:/login";
	}
	
	
	
	
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
	 * Recibe en el body un Usuario con user y password. Realiza Login.
	 * @param usuario
	 * @return Devuelve true si el login es correctro
	 */
	@PostMapping("/login")
	public Boolean logandose(@Valid @RequestBody Usuario usuario) {
		
		boolean isUser= userServ.compruebaUsuario(usuario.getUser(), usuario.getPassword());
		boolean result=false;
		if(isUser) {
			
			Usuario userLogado = userServ.findById(usuario.getUser());
			sesion.setAttribute("usuario", userLogado);
						
			
			result=true;
		}

		return result;
		
	}
	
	
	@GetMapping("/sesion")
	public Usuario usuarioLogado() {

		
		Usuario userLogado = (Usuario) sesion.getAttribute("usuario");
		return userLogado;
		
		
	}
	
	
	
	/**
	 * Crea un pedido vación en el usuario logado. Devuelve el id del pedido si se ha creado correctamente. 
	 * @return String
	 */
	@GetMapping("/login/nuevopedido")
	public String crearPedido( ) {
		

		String result="";
																			
		if(sesion.getAttribute("usuario")==null){
			sesion.invalidate();
			result="Sesión caducada";
			

		}else {
			
					
			Usuario userLogado = (Usuario) sesion.getAttribute("usuario");
			Pedido pedido= new Pedido();
			result=pedido.getId()+"";
			userLogado.addListaPedidos(pedido);
			pedService.savePedido(pedido);

		}
		
		return result;
		
	}
	
	
	/**
	 * 
	 * @param producto
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/login/addproducto/{idPedido}")
	public String addProducto(@Valid @RequestBody Producto producto,@PathVariable int idPedido, BindingResult bindingResult) {
		
		String result="";
																			
		if(sesion.getAttribute("usuario")==null){
			sesion.invalidate();
			result="Sesion caducada";
		}else {
			
		
			Usuario userLogado = (Usuario) sesion.getAttribute("usuario");
			Pedido pedido = pedService.findPedido(idPedido, userLogado);
			
			
			
			if(!bindingResult.hasErrors() && productoService.contains(producto.getId())) {
				pedService.addPedido(producto.getId(), pedido, producto.getCantidad());
				result="ok";
			}
			
		}
		
		return result;
		
	}
	
	

	
	
}
