package com.rezeptor.utils.jwt;

import static java.time.temporal.ChronoUnit.DAYS;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rezeptor.exceptions.RSAKeyLoaderException;
import com.rezeptor.utils.RSAKeyLoader;;

@Service
public class JWTUtil {

	private RSAKeyLoader rsaKeyLoader;
	
	public JWTUtil(RSAKeyLoader rsaKeyLoader) {
		this.rsaKeyLoader = rsaKeyLoader;
	}

	public String issueJWTToken(String subject) {
		return issueJWTToken(subject, Map.of());
	}
	
	public String issueJWTToken(String subject,String ...scopes) {
		return issueJWTToken(subject, Map.of("scopes",scopes));
	}
	
	public String issueJWTToken(String subject, List<String> scopes) {
		return issueJWTToken(subject, Map.of("scopes",scopes));
	}
	
	public String issueJWTToken(String subject,Map<String, Object> claims) {
		try {
			String jwtToken = JWT.create()
							.withClaim(subject, claims)
							.withIssuer("https://alfi-rzptr1.github.io/")
							.withIssuedAt(Date.from(Instant.now()))
							.withExpiresAt(Date.from(Instant.now().plus(15, DAYS)))
							.sign(rsaKeyLoader.getAlgorithm());
			return jwtToken;
		} catch (IllegalArgumentException | JWTCreationException | RSAKeyLoaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private DecodedJWT decodedJWTToken(String jwtToken) {
		try {
			JWTVerifier jwtVerifier = JWT.require(rsaKeyLoader.getAlgorithm())
										 .withIssuer("https://alfi-rzptr1.github.io/")
										 .build();
			DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
			return decodedJWT;
		} catch (IllegalArgumentException | JWTVerificationException | RSAKeyLoaderException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public String getSubject(String jwtToken) {
		return decodedJWTToken(jwtToken).getSubject();
	}
	
	public Map<String, Claim> getClaim(String jwtToken) {
		return decodedJWTToken(jwtToken).getClaims();
		
	}
	
	public boolean isJWTTokenValid(String jwtToken,String username) {
		String subject = getSubject(jwtToken);
		return subject.equals(username) && !isJWTTokenExpired(jwtToken);
	}
	
	private boolean isJWTTokenExpired(String jwtToken) {
		Date todayDate = (Date) Date.from(Instant.now());
		return decodedJWTToken(jwtToken).getExpiresAt().before(todayDate);
	}
	
}
