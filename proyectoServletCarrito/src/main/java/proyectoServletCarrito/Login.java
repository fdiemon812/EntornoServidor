package proyectoServletCarrito;

import java.util.HashSet;
import java.util.Iterator;

public class Login {
	
	private HashSet<Usuario> listaUsuarios;

	
	public Login() {
		
		listaUsuarios = new HashSet<Usuario>();
	}
	
	
	public boolean addUser(Usuario user) {
		
		return listaUsuarios.add(user);
		
	}
	
	public boolean compruebaLogin(String user, String pass) {
		
		boolean result=false;
		
		if(listaUsuarios.contains(new Usuario(user))) {
			
			Iterator<Usuario> i = listaUsuarios.iterator();
			
			while(i.hasNext() && !result) {
				
				Usuario usuario=i.next();
				
				if(usuario.getUser().equalsIgnoreCase(user) && usuario.getUser().equals(pass)) {
					result=true;
				}
					
			}
			
		}
		
		return result;
		
	}
	
	
	
	
	
	
	
}
