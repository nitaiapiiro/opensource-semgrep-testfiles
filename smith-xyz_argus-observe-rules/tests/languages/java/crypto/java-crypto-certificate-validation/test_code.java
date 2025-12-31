package javacryptocertificatevalidation;

import javax.net.ssl.X509TrustManager;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;

public class TestCode {
    public void trustManagerCheckClient() throws Exception {
        X509TrustManager trustManager = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
    }

    public void trustManagerCheckServer() throws Exception {
        X509TrustManager trustManager = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
    }

    public void trustManagerClass() throws Exception {
        class CustomTrustManager implements X509TrustManager {
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }
    }

    public void hostnameVerifier() {
        HostnameVerifier verifier = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }

    public void setDefaultHostnameVerifier() {
        HostnameVerifier verifier = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(verifier);
    }

    public void setHostnameVerifier() throws Exception {
        java.net.URL url = new java.net.URL("https://example.com");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        HostnameVerifier verifier = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        connection.setHostnameVerifier(verifier);
    }
}
