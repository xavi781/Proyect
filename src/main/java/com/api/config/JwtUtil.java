package com.api.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

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
    
}
