package graduation.busstation.hardware.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Slf4j
public class SecureUtil {

    private static final String SECRET_KEY = "";
    private static final String IV = SECRET_KEY.substring(0, 16);

    // AES128 복호화 메소드
    public static String decryptAES256(String encryptedMacAddress) throws Exception {
        encryptedMacAddress = URLDecoder.decode(encryptedMacAddress, StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(encryptedMacAddress);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        String key = new String(decrypted).trim();
        log.info("{} : ",key);
        return key;
    }


    public static String encryptAES256(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes());

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

}
