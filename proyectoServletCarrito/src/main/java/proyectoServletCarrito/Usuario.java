package proyectoServletCarrito;

import java.util.HashMap;

public class Usuario {
	
	HashMap<String, String> usuarios = new HashMap<String, String>();
	
	
	
	
	public Usuario() {
		
		usuarios.put("user", "user");
		usuarios.put("admin", "admin");
		usuarios.put("flavio", "flavio");

		
		
		
	}
	
	
	public boolean compruebaUsuario(String nombre, String pass) {
		boolean result= false;
		
		if(usuarios.containsKey(nombre)) { //si usamos el operdor perzoso no funciona
			
			if(usuarios.get(nombre).equals(pass)) {
				
				result=true;
				
			}
			
			
		}
		
		
		return result;
		
	}
	
	
	
	

}
