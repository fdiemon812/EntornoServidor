package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService implements UsuarioServiceInterfaz {
	
	@Autowired
	private UsuarioRepository usuRepo;
	
	
	private HashSet<Usuario> listaUsuarios = new HashSet<>();
	
	
	
	/**
	 * Recibe un nombreUsuario y Contraseña. Devuelve true si existe ese usuario con esa contraseña
	 * @param usuario
	 * @param password
	 * @return boolean respuesta
	 */
	public boolean compruebaUsuario(String usuario, String password) {
		
		boolean result=false;
		
		Usuario user = findById(usuario);
		
		if(user!=null && user.getPassword().equals(password)) {
			
			result=true;
			
		}
		
		
		return result;
		
	}
	
	
//public boolean compruebaUsuario(String usuario, String password) {
//		
//		boolean result=false;
//		
//		Usuario user = obtenerUsuarioId(usuario);
//		Usuario user = new Usuario(usuario, password);
//		
//		if(listaUsuarios.contains(user)) {
//			
//			ArrayList<Usuario> listaUsuariosArray = new ArrayList<>(listaUsuarios);
//			
//			Usuario usuarioBD = listaUsuariosArray.get(listaUsuariosArray.indexOf(user));
//			
//			if(usuarioBD.getUser().equalsIgnoreCase(usuario)&& usuarioBD.getPassword().equals(password)) {
//				result=true;
//			}
//			
//			
//			
//			
//		}
//		
//		
//		return result;
//		
//	}
	

	
	/**
	 * Recibe un nombreUsuario. Devuelve el usuario que tenga dicho nombre. Si no existe devuelve null. 
	 * @param nombreUsuario
	 * @return
	 */
	public Usuario obtenerUsuario(String nombreUsuario) {
		ArrayList<Usuario> arrayUsuarios = new ArrayList<>(listaUsuarios);
		

		Usuario result = null;
		
		boolean isEncontrado = false;
		int indice = 0;
		
		while(!isEncontrado && indice<arrayUsuarios.size()) {
			if((nombreUsuario.equals(arrayUsuarios.get(indice).getUser()))) {
				isEncontrado = true;
				result = arrayUsuarios.get(indice);
			}else {
				indice++;
			}
		}
		
		return result;
	}
	
//	@PostConstruct
//	public void init() {
//		listaUsuarios.addAll(
//				Arrays.asList(new Usuario("user","José", "Pérez", "user", "C/Málaga, 9 7A", 650000000, "user@user.com"),
//						new Usuario("admin","Antonia", "García", "admin", "C/Arroyo, 9 7A", 690000000, "admin@admin.com"),
//						new Usuario("flavio","Flavio", "de Diego", "flavio", "C/Jaen, 9 7A", 850000000, "flavio@flavio.com")
//						)
//				);
//		
//	}
	
	
	
	
//	@PostConstruct
//	public void init() {
//		
//		usuRepo.save(new Usuario("user","José", "Pérez", "user", "C/Málaga, 9 7A", 650000000, "user@user.com"));
//		usuRepo.save(new Usuario("admin","Antonia", "García", "admin", "C/Arroyo, 9 7A", 690000000, "admin@admin.com"));
//		usuRepo.save(new Usuario("flavio","Flavio", "de Diego",
//				"flavio", "C/Jaen, 9 7A", 850000000, "flavio@flavio.com"));
//						
//				
//		
//	}
	
	
	




	@Override
	public Usuario add(Usuario usuario) {
		return usuRepo.save(usuario);
		
	}




	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Usuario findById(String id) {
		return usuRepo.findById(id).orElse(null);
	}




	@Override
	public Usuario edit(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}


	public Usuario saveUser(Usuario userLogado) {
		return usuRepo.save(userLogado);
	}
	
	 
	
	
	

	
	

	
	
	
}





