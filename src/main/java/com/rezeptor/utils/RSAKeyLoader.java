package com.rezeptor.utils;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.algorithms.Algorithm;
import com.rezeptor.exceptions.RSAKeyLoaderException;

@Service
public class RSAKeyLoader {
	
	@Value("${rsa.private.key.path}")
	private String PRIVATE_KEY_PATH;
	
	@Value("${rsa.public.key.path}")
	private String PUBLIC_KEY_PATH;
	
	public Algorithm getAlgorithm()  throws RSAKeyLoaderException{
		try {
			Algorithm algorithm = Algorithm.RSA256(loadPublicKey(), loadPrivateKey());
			return algorithm;
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RSAKeyLoaderException("Error creating algorithm",e);
		}
	}
	
	public RSAPrivateKey loadPrivateKey() throws RSAKeyLoaderException {
		try {
			String privateKeyPEM = new String(loadPEM(PRIVATE_KEY_PATH));
			privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").replaceAll("\\s", "");
        
			byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			throw new RSAKeyLoaderException("Error loading private key", e);
		}
        
    }

    public RSAPublicKey loadPublicKey() throws RSAKeyLoaderException{
    	try {
    		String publicKeyPEM = new String(loadPEM(PUBLIC_KEY_PATH));
    		publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replaceAll("\\s", "");
        
    		byte[] encoded = Base64.getDecoder().decode(publicKeyPEM);
    		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
    		return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			throw new RSAKeyLoaderException("Error loading public key", e);
		}
        
    }

    private byte[] loadPEM(String path) {
    	try (FileInputStream fis = new FileInputStream(path)) {
			return fis.readAllBytes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new byte[0];
		}
    }
	
}
