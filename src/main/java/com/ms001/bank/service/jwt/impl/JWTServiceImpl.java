package com.ms001.bank.service.jwt.impl;

import com.ms001.bank.service.jwt.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts
                .builder() //starts the process of creating a new JWT token,
                .subject(userDetails.getUsername()) //subject is customer fincode,set the subject claim of the JWT token, you are essentially specifying which user the token is issued for,represents the identity of the user or entity associated with the token.
                .setIssuedAt(new Date((System.currentTimeMillis()))) //sets the "issued at" claim of the JWT token to the current time
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 24 * 60)) // sets the expiration time of the token. Here, 1000*24*60 represents 24 hours in milliseconds
                .signWith(getSignKey(), SignatureAlgorithm.ES256)//Yani, SignatureAlgorithm belirli bir algoritmayı seçerek kriptografik anahtarın nasıl kullanılacağını tanımlar ve bu sayede JWT tokeninin imzalanması ve doğrulanması için gerekli olan işlemleri belirler. Kriptografik anahtar ise seçilen bu algoritmanın işlevini yerine getirmesi için kullanılan bir parçadır.
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);//method reference that provides a way to extract the subject claim from the claims of a JWT token
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);//apply method is used to execute or apply the claimsResolver function to the Claims object
    }

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode("413F4428472B4B6250655368566D5970337336763979244226452948404D6351");//it's decoded from Base64 to obtain the raw bytes of the key.
        return Keys.hmacShaKeyFor(key); //raw bytes are used to create an HMAC-SHA key using the Keys.hmacShaKeyFor() method.
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
