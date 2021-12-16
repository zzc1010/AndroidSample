package edu.smu.cryptoguardsample.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by krishnokoli on 7/2/17.
 */
public class BadSymCrypto {


    private String algo = "AES/CBC/PKCS5PADDING";

    public BadSymCrypto(String algo) {
        this.algo = algo;
    }

    public String decrypt(String key, String initVector, String encrypted) {
        System.out.println("BadSymCrypto_decrypt");
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance(algo);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(encrypted.getBytes());

            return new String(original);
        } catch (Exception ex) {
            System.out.println("Error occurs.");
            ex.printStackTrace();
        }

        return null;
    }
}
