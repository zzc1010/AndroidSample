package edu.smu.cryptoguardsample.crypto;

import java.security.KeyPair;

public class VeryBadMain {

    public static void main(String[] args) throws Exception {

      edu.smu.cryptoguardsample.crypto.BadAssymCrypto badCrypto = new edu.smu.cryptoguardsample.crypto.BadAssymCrypto();
        KeyPair kp = badCrypto.generateKeyPairExplicit(512);

        System.out.println(kp.getPrivate().getEncoded());

      edu.smu.cryptoguardsample.crypto.BadDigestAndRandom badDigestAndRandom = new edu.smu.cryptoguardsample.crypto.BadDigestAndRandom();

        System.out.println(new String(badDigestAndRandom.getDigest("MD5", "Hello World!")));
        System.out.println(new String(badDigestAndRandom.getDigest("SHA1", "Hello World!")));
        System.out.println(new String(badDigestAndRandom.getDigest("SHA-512", "Hello World!")));

        String key = "my+secret+key+lol";
        String initVector = "RandomInitVector";
      edu.smu.cryptoguardsample.crypto.BadSymCrypto crypto = new edu.smu.cryptoguardsample.crypto.BadSymCrypto("AES/CBC/PKCS5PADDING");

        String decrypted = crypto.decrypt(key, initVector, "154asf15as4d5as4dasdfasf1sa5d");
        System.out.println(decrypted);

        String encrypted =edu.smu.cryptoguardsample.crypto.PasswordUtils.encryptPassword("mypass,PBEWITHHMACSHA1ANDAES_128,my-sensitive-key,f77aLYLo,2000");
        System.out.println(encrypted);
        System.out.println(edu.smu.cryptoguardsample.crypto.PasswordUtils.encryptPassword(encrypted));
    }
}