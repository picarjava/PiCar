package com.DigestService;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DigestService {
	
	public String digest(String digestpassword) {
       
        MessageDigest md;
        try
        {
            // Select the message digest for the hash computation -> SHA-256
            md = MessageDigest.getInstance("SHA-256");

            // Generate the salted hash
            md.update(digestpassword.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();

            digestpassword = String.format("%064x", new BigInteger(1, digest));
            
            System.out.println(digestpassword);
      
        } catch (NoSuchAlgorithmException e) {   
            e.printStackTrace();
        }
        
        return digestpassword;
    }
}