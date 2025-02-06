package com.UFJF.planejaai.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;


@Service
public class JwtService {
	static final long EXPIRATIONTIME = 43200000;
	static final String PREFIX = "Bearer";
	
	private final Key key;
	
	public JwtService(@Value("${jwtkey-base64}") String base64Key){
		key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Key));
		
	}
	
	public String getToken(String nomeUsuario) {
		return Jwts.builder()
				.subject(nomeUsuario)
				.expiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(key)
				.compact();
	}
	
	public String getTokenUser(String token) {
		return Jwts.parser()
				.verifyWith((SecretKey) key)
				.build()
				.parseSignedClaims(token.replace(PREFIX,""))
				.getPayload()
				.getSubject();
	}
	
}
