package nordakademie.client.logik;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * Ver- und entschlüsselt Strings mittels AES.
 *
 *
 */
public class AES {
	private static String key = "770A8A65DA156D24";

	public static String Encrypt(String plainText) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		if (plainText == null)

		{
			plainText = "leer";
		} else {
		}
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
		return new Base64().encodeAsString(encryptedTextBytes);
	}

	public static String Decrypt(String encryptedText) throws UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		if (encryptedText == null)

		{
			encryptedText = Encrypt("leer");
		} else {
		}
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] encryptedTextBytes = new Base64().decodeBase64(encryptedText);
		byte[] decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
		return new String(decryptedTextBytes);
	}
}