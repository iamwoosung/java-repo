package crypt.aes;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptAES {
	
	private static class CryptBase {
		
		// AES256
		private static final byte[] KEY = {
	        0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08,
	        0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10,
	        0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18,
	        0x19, 0x1A, 0x1B, 0x1C, 0x1D, 0x1E, 0x1F, 0x20
	    };

	    private static final byte[] IV = {
	        0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
	        0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F
	    };
	    
	    private static final String ALGORITHM = "AES";
	    private static final String MODE      = "CBC";
	    private static final String PADDING   = "PKCS5Padding";
	    // private static final String PADDING   = "ZeroPadding"; // 미지원
	}
	
	public static String encrypt(String data) {
		try {
			Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
			
	        byte[] buff = data.getBytes();
	        byte[] padd = new byte[((buff.length + 15) / 16) * 16]; 
	        System.arraycopy(buff, 0, padd, 0, buff.length);

	        byte[] enc = cipher.doFinal(padd);
	        return Base64.getEncoder().encodeToString(enc);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	public static String decrypt(String data) {
        try {
			Cipher cipher = getCipher(Cipher.DECRYPT_MODE);

            byte[] buff = Base64.getDecoder().decode(data);
            byte[] dec = cipher.doFinal(buff);

            return new String(dec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	private static Cipher getCipher(int mode) {
		try {
			Cipher cipher = null;
			SecretKeySpec key = new SecretKeySpec(CryptBase.KEY, CryptBase.ALGORITHM);
	        IvParameterSpec iv = new IvParameterSpec(CryptBase.IV);

	        cipher = Cipher.getInstance(String.format("%s/%s/%s", CryptBase.ALGORITHM, CryptBase.MODE, CryptBase.PADDING));
			cipher.init(mode, key, iv);
			
			return cipher;
		}catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
}
