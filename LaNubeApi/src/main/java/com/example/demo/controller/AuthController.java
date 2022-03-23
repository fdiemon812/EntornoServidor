package com.example.demo.controller;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ApiError;
import com.example.demo.exception.CentroNotFoundException;
import com.example.demo.exception.ComidaInvalidException;
import com.example.demo.exception.LoginInvalidException;
import com.example.demo.model.Centro;
import com.example.demo.model.LoginCredentials;
import com.example.demo.model.Profesor;
import com.example.demo.model.Tutor;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CentroRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.security.JWTUtil;

/**
 * Clase que controla el Login y registro de los usuarios
 * @author estudiante
 *
 */
@RestController
public class AuthController {

    @Autowired private UserRepo userRepo;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserRepo usuRepo;
    @Autowired private CentroRepo centroRepo;
    
    private final String PROFESOR ="PROFESOR";
    private final String ADMINISTRADOR ="ADMINISTRADOR";
    private final String TUTOR ="TUTOR";
    
    /**
     * Registra un usuario. Recibe un JSON con el usuario, password y rol.
     * @param user
     * @return
     */
    @PostMapping("centro/{idCentro}/register")
    public Map<String, Object> registerHandler(@PathVariable int idCentro ,@RequestBody Usuario user) throws Exception{
        
    	if(!centroRepo.existsById(idCentro)) {
			throw new CentroNotFoundException(idCentro+"");
		}
    	
    	String encodedPass = passwordEncoder.encode(user.getPassword());
    	Centro centro = centroRepo.getById(idCentro);

        Map<String, Object> map=null;
        if(user.getRole().equals(PROFESOR) || user.getRole().equals(ADMINISTRADOR) ) {
        	
        	
    		
        	
        	Profesor profe = new Profesor( user);
        	profe.setPassword(encodedPass);
        	
    		centro.getProfesores().add(profe);
        	profe = userRepo.save(profe);
        	centroRepo.save(centro);
             String token = jwtUtil.generateToken(profe.getEmail(), profe.getRole());
             map= Collections.singletonMap("jwt_token", token);

        }else if(user.getRole().equals(TUTOR)){
        	Tutor tutor = new Tutor( user);
        	tutor.setPassword(encodedPass);
        	
        	centro.getTutores().add(tutor);
        	tutor = userRepo.save(tutor);
        	centroRepo.save(centro);
            String token = jwtUtil.generateToken(tutor.getEmail(), tutor.getRole());
            map= Collections.singletonMap("jwt_token", token);
            
        }
        

        return map;
    }

    
    /**
     * Loga un usuario. Recibe email y contraseña en un JSON. 
     * @param body
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authManager.authenticate(authInputToken);
            
            String rol = userRepo.findByEmail(body.getEmail()).get().getRole();
            String token = jwtUtil.generateToken(body.getEmail(), rol );
            return Collections.singletonMap("jwt_token", token);
        }catch (AuthenticationException authExc){
//            throw new RuntimeException("Invalid Login Credentials");
        	throw new LoginInvalidException();
        }
    }
   
	
	
	/**
	 * Devuelve el usuario al logarse. 
	 */
	@GetMapping("/home/usuario")
    public ResponseEntity<Usuario>  compruebaRol() {
		
		
		
	ResponseEntity<Usuario> respuesta = ResponseEntity.badRequest().build();
		
	String correo= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	Usuario usuario=usuRepo.findByEmail(correo).orElse(null);
	
	if(usuario!=null) {
	 respuesta = ResponseEntity.ok(usuario);
	}
	
    return respuesta;		
		
		
		
	}
	
	
    
	/**
	 * Si el token es valido no devuelve nada. Si el token no es válido devuelve error. 
	 */
	@GetMapping("/home/token")
    public void compruebaToken() {}
	
	
    

    
	/**
	 * Devuelve true o false si el mail existe en la bbdd. 
	 * @param email
	 * @return
	 */
	@GetMapping("/usuario")
	public boolean isUsuario(String email){
		boolean respuesta = false;
		Usuario usuario=usuRepo.findByEmail(email).orElse(null);

		
		if(usuario==null) {
			respuesta=true;
		}
		
		
		return respuesta;
		
	}
	
	
	/**
	 * Gestiona si se intenta logar con login invalido
	 * @param ex
	 * @return JSON bien formado
	 */
	@ExceptionHandler(LoginInvalidException.class)
	public ResponseEntity<ApiError> LoginInvalidException(LoginInvalidException userException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), userException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	
	
	/**
	 * Gestiona si no existe un usuario buscado
	 * @param ex
	 * @return JSON bien formado
	 */
	@ExceptionHandler(CentroNotFoundException.class)
	public ResponseEntity<ApiError> CentroException(CentroNotFoundException userException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), userException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	

}
