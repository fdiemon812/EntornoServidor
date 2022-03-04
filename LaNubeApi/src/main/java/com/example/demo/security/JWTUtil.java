package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;


/**
 * Clase para generar y validar token
 * @author estudiante
 *
 */
@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    
    /**
     * Genera el token
     * @param email
     * @return
     * @throws IllegalArgumentException
     * @throws JWTCreationException
     */
    public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
    	
        Calendar calendar = Calendar.getInstance();

              calendar.setTime(new Date());  
              calendar.add(Calendar.MILLISECOND, 100000);  
              Date date= calendar.getTime();
    	
        return JWT.create()
                .withSubject("User Details")
//                .withExpiresAt(date)   //Para darle caducidad
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .sign(Algorithm.HMAC256(secret));
    }

    
    /**
     * Valida el token
     * @param token
     * @return
     * @throws JWTVerificationException
     */
    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }

}