package com.landvibe.common.utils;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {
	
	@Autowired
	private DeEncrypter deEncrypter;

	public String generateToken() {
		String token = null;
		SecureRandom secureRandom;
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			secureRandom.setSeed(secureRandom.generateSeed(8));
			token = new String(digest.digest((secureRandom.nextLong() + "").getBytes()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return euckr_decode(token);
		return token;
	}

	public String generateToken2(Long user_no, String email) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// KeyGenerator generator = KeyGenerator.getInstance("AES");
		// SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		// generator.init(128, random);
		// Key secureKey = generator.generateKey();

		// PBEKeySpec keySpec = new PBEKeySpec("패스워드".toCharArray());
		// SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBE");
		// Key secureKey = keyFactory.generateSecret(keySpec);

//		Key secureKey = new SecretKeySpec("passwordpassword".getBytes(), "AES");
//
//		Cipher cipher = Cipher.getInstance("AES");
//		cipher.init(Cipher.ENCRYPT_MODE, secureKey);
//		byte[] encryptedData = cipher.doFinal("positiwee".getBytes());
//		
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String token= "";
		Date today = new Date();
		
		System.out.println("token : " + token);
		
		token = deEncrypter.encrypt(String.valueOf(user_no+"?"+email+"?"+dateFormat.format(today)));
		
		System.out.println("token : " + token);
		return token;

	}



}
