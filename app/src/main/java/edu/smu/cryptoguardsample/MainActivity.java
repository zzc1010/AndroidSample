package edu.smu.cryptoguardsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;

import edu.smu.cryptoguardsample.crypto.BadAssymCrypto;
import edu.smu.cryptoguardsample.crypto.BadCertificateValidation;
import edu.smu.cryptoguardsample.crypto.BadDigestAndRandom;
import edu.smu.cryptoguardsample.crypto.BadHostnameVerification;
import edu.smu.cryptoguardsample.crypto.BadSymCrypto;
import edu.smu.cryptoguardsample.crypto.PasswordUtils;
import edu.smu.cryptoguardsample.crypto.VeryBadMain;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            VeryBadMain.main(null);
            BadAssymCrypto bac = new BadAssymCrypto();
            bac.generateKeyPairExplicit(0);
            bac.generateKeyPairImplicit();

            BadCertificateValidation bcv = new BadCertificateValidation();
            bcv.getSocketFactoryWithBadSelfsignedCertValidation();
            bcv.getSocketFactoryWithTrustAllCertValidation();

            BadDigestAndRandom bdar = new BadDigestAndRandom();
            bdar.generateRandomBytes(0);
            BadDigestAndRandom.getDigest(null,null);

            BadHostnameVerification bhnv = new BadHostnameVerification();
            bhnv.connectWithBadHostnames();
            bhnv.writeToBadSocket();

            BadSymCrypto bsc = new BadSymCrypto("PKCS5PADDING");
            bsc.decrypt(null,null,null);

            PasswordUtils.encryptPassword("new passWord");
            PasswordUtils.decryptPassword("new password2");

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


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}