package sqlConnection;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AdvancedEncryptionStandard {

	private byte[] key;

    private static final String ALGORITHM = "AES";
    
    public AdvancedEncryptionStandard() {
    	this.key = "MZygpewJsCpRrfOr".getBytes(StandardCharsets.UTF_8);
    }
    

    public byte[] encrypt(byte[] plainText) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(plainText);
    }

    public byte[] decrypt(byte[] cipherText) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return cipher.doFinal(cipherText);
    }
    
    public String cipherText(String pTxt) {
    	byte[] plainText = pTxt.getBytes(StandardCharsets.UTF_8);
    	
    	byte[] cipherText;
		try {
			cipherText = this.encrypt(plainText);
			return new String(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null; 	
    }
    
    public String decryptedCipherText(String cipherText) {
    	
    	byte[] decryptedCipherText;
    	byte[] str = cipherText.getBytes(StandardCharsets.UTF_8);
		try {
			decryptedCipherText = this.decrypt(str);
		   	return new String(decryptedCipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
  
}
