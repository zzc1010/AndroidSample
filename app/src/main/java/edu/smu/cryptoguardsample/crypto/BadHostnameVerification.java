package edu.smu.cryptoguardsample.crypto;

import java.io.OutputStream;
import java.net.URL;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class BadHostnameVerification {

    public HttpsURLConnection connectWithBadHostnames() throws Exception {
        System.out.println("connectWithBadHostnames");
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        URL url = new URL("https://example.org/");
        HttpsURLConnection urlConnection =
                (HttpsURLConnection) url.openConnection();
        urlConnection.setHostnameVerifier(hostnameVerifier);

        return urlConnection;
    }

    public void writeToBadSocket() throws Exception {
        System.out.println("writeToBadSocket");
        SocketFactory sf = SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) sf.createSocket("gmail.com", 443);

        OutputStream out = socket.getOutputStream();
        out.write("Hello Bad guy! ;)".getBytes());

        socket.close();
    }


}
