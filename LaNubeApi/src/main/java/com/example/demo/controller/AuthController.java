package com.example.demo.controller;


import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LoginCredentials;
import com.example.demo.model.Profesor;
import com.example.demo.model.Tutor;
import com.example.demo.model.Usuario;
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
    
    
    private final String PROFESOR ="PROFESOR";
    private final String ADMINISTRADOR ="ADMINISTRADOR";
    private final String TUTOR ="TUTOR";
    
    /**
     * Registra un usuario. Recibe un JSON con el usuario, password y rol.
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody Usuario user){
        String encodedPass = passwordEncoder.encode(user.getPassword());
        
        
        Map<String, Object> map=null;
        if(user.getRole().equals(PROFESOR)) {
        	Profesor profe = new Profesor( user);
        	profe.setPassword(encodedPass);
        	profe = userRepo.save(profe);
             String token = jwtUtil.generateToken(profe.getEmail());
             map= Collections.singletonMap("jwt_token", token);

        }else if(user.getRole().equals(TUTOR)){
        	Tutor tutor = new Tutor( user);
        	tutor.setPassword(encodedPass);
        	tutor = userRepo.save(tutor);
            String token = jwtUtil.generateToken(tutor.getEmail());
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

            String token = jwtUtil.generateToken(body.getEmail());

            return Collections.singletonMap("jwt_token", token);
        }catch (AuthenticationException authExc){
            throw new RuntimeException("Invalid Login Credentials");
        }
    }
    
    
    /**
	 * Si el token es valido no devuelve nada. Si el token no es válido devuelve error. 
	 */
	@GetMapping("/home/token")
    public void compruebaToken() {}
	
	
    


}
