package proyectoservletcarrito;

import java.util.HashMap;

public class Usuario {
	
	HashMap<String, String> usuarios = new HashMap<>();
	
	/**
	 * Constructor nos crea por defecto 3 usuarios predefinidos
	 */
	public Usuario() {
		
		usuarios.put("user", "user");
		usuarios.put("admin", "admin");
		usuarios.put("flavio", "flavio");
		
	}
	
	/**
	 * Comprueba si el  usuario existe en la base de datos. 
	 * @param nombre
	 * @param pass
	 * @return true si el usuario existe. False si no existe.
	 */
	public boolean compruebaUsuario(String nombre, String pass) {
		boolean result= false;
		
		if(usuarios.containsKey(nombre) && usuarios.get(nombre).equals(pass)) { 
			
			result=true;
			
	
		}
		
		return result;
		
	}

}
