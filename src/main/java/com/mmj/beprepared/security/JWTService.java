package com.mmj.beprepared.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private  static  final String SECRET_KEY = "y7p2OCnSKEPcX70SAh5CIggGM0zI4kJ5bug7lg6r4dt6m5ftyu";
    private  static final long JWT_EXPIRATION = 86400000; //Quanto tempo o tokne ira durar

    public String extractUserFromToken(String token){
        return extractClaim(token, Claims::getSubject);

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims,
                                UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, JWT_EXPIRATION);

    }


    private String buildToken(Map<String, Object> extraClaims,
                              UserDetails userDetails,
                              long expiration) /*pega a expiracao do token*/{
        return  Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }


    public boolean isTokenValid(String token, UserDetails userDetails){
        final  String username = extractUserFromToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token){
        return  extractClaim(token,Claims::getExpiration);
    }

    public Claims extractAllClaimsFromToken(String token) { //Serva para extrair os usuarios do noaao token
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody(); //assim transformamos os tokens em dados normais


    }

    private Key getSignInKey(){//Metodo para extrair as clains dos user's
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }



}
