package edu.smu.cryptoguardsample.crypto;


import java.security.MessageDigest;
import java.util.Random;

public class BadDigestAndRandom {

    public byte[] generateRandomBytes(long seed) {
        System.out.println("generateRandomBytes");
        byte[] randomBytes = new byte[64];
        new Random(seed).nextBytes(randomBytes);
        return randomBytes;
    }

    public static byte[] getDigest(String algo, String msg) throws Exception {
        System.out.println("getDigest");
        MessageDigest md = MessageDigest.getInstance(algo);
        return md.digest(msg.getBytes());
    }

}
