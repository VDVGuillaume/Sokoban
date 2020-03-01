package persistentie;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Security 
{
	public static String getNextSalt() 
	{
		Random random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt.toString();
	}
	
	public static String hash(String key, byte[] salt) 
	{
		String keyHashed = null;
		try 
		{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt);
			byte[] bytes = md.digest(key.getBytes());
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < bytes.length; i++) 
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			keyHashed = sb.toString();
		}catch(NoSuchAlgorithmException e) 
		{
			// java...
			e.printStackTrace();
		}
		
		return keyHashed;
	}
	
}
