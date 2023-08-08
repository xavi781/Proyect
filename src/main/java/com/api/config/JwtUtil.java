package com.api.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JwtUtil {

    private static String SECRET_KEY = "proyect_users";
    private static Algorithm ALGORITHM= Algorithm.HMAC256(SECRET_KEY);


    //ANIDA UN NUEVO JWT PARA UN USUARIO PUNTUAL 
    public String create( String username){
        return JWT.create()
            .withSubject(username)
            .withIssuer("proyect_users")
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
            .sign(ALGORITHM);
    }

    //valida si un jwt es correcto o no
    public boolean isValid(String jwt){
        try {
            JWT.require(ALGORITHM)
               .build()
            .verify(jwt);
            return true;
            
        } catch (JWTVerificationException e) {
            return false;
        }
        
    } 
    //pbtenemos si el usuario el subject a cual pertenece el jwt
    public String getUsername(String jwt){
            return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
        }
    
}
