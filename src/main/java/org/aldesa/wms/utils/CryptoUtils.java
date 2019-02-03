package org.aldesa.wms.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class CryptoUtils {
	private static final String validChars="abcdefghijklmnopqrstuvwxyzBCDEFGHIJKLMNOPQRSTUVWXYZ/*-+._=1234567890?!><,;:@#^&()";
	private static final char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private static final String[] validMethods = {"MD2", "MD5", "SHA-1", "SHA-256", "SHA-512"};
			
 	public static String byteArray2Hex(byte[] bytes) {
	    StringBuffer sb = new StringBuffer(bytes.length * 2);
	    for(final byte b : bytes) {
	        sb.append(hex[(b & 0xF0) >> 4]);
	        sb.append(hex[b & 0x0F]);
	    }
	    return sb.toString();
	}
	
	public static String crearPalabraAleatoria(int tam){
		Random r = new Random();
		String palabra="";
		for(int i=0;i<tam;i++)
			palabra+=validChars.charAt(r.nextInt(validChars.length()));
		return palabra;
	}
	
	public static String obtenerHash(String clave, String metodo, int pasadas)throws NoSuchAlgorithmException{
		if(pasadas<1)
			return clave;
		MessageDigest messageDigest = MessageDigest.getInstance(metodo);
	    messageDigest.update(clave.getBytes());
	    return obtenerHash(byteArray2Hex(messageDigest.digest()), metodo, pasadas-1);
	}
	
	public static String obtenerHash(String clave, String sal, String metodo, int pasadas) throws NoSuchAlgorithmException{
		String n = sal+clave+sal;
		return obtenerHash(n, metodo, pasadas);
	}
	
	public static String crearContrasena(String clave) throws NoSuchAlgorithmException{
		Random r = new Random();
		String sal = crearPalabraAleatoria(4);
		int pasadas = r.nextInt(5)+1;
		int hashIndex = r.nextInt(5);
		String metodo=validMethods[hashIndex];
		// estructura <algoritmo_hash>$<iteraciones>$<sal>$<hash>
		return ""+metodo+"$"+pasadas+"$"+sal+"$"+obtenerHash(clave, sal, metodo, pasadas);		
	}
	
	public static boolean esClave(String clave, String contrasena) throws NoSuchAlgorithmException{

		String metodo, sal, hash;
		int pasadas, inicio=0, fin=contrasena.indexOf("$", 0);
		
		// buscar metodo
		metodo=contrasena.substring(inicio, fin);
		
		// buscar cantidad de iteraciones
		inicio=fin+1;
		fin=contrasena.indexOf("$", inicio);
		pasadas = Integer.parseInt(contrasena.substring(inicio,fin));
		
		// buscar sal
		inicio=fin+1;
		fin=contrasena.indexOf("$", inicio);
		sal = contrasena.substring(inicio,fin);
		
		// buscar hash
		inicio=fin+1;
		hash=contrasena.substring(inicio);
		System.out.println("VALOR REAL : " + hash);
		System.out.println("HASH GENERADO : " +obtenerHash(clave, sal, metodo, pasadas));
		
		return hash.equalsIgnoreCase(obtenerHash(clave, sal, metodo, pasadas));
	}
	
	public static String HMAC_MD5_encode(String key, String message) throws Exception {

        SecretKeySpec keySpec = new SecretKeySpec(
                key.getBytes(),
                "HmacMD5");

        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(keySpec);
        byte[] rawHmac = mac.doFinal(message.getBytes());

        return Hex.encodeHexString(rawHmac);
    }
}
