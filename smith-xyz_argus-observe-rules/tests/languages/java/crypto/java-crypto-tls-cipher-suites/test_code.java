package javacryptotlsciphersuites;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;
import java.net.Socket;

public class TestCode {
    public void setCipherSuites() throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        SSLParameters params = context.getDefaultSSLParameters();
        String[] suites = {"TLS_RSA_WITH_AES_128_CBC_SHA"};
        params.setCipherSuites(suites);
    }

    public void setCipherSuitesDirect() throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        SSLParameters params = context.getDefaultSSLParameters();
        params.setCipherSuites(new String[]{"TLS_RSA_WITH_AES_256_CBC_SHA"});
    }

    public void setEnabledCipherSuites() throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        SSLEngine engine = context.createSSLEngine();
        String[] suites = {"TLS_RSA_WITH_AES_128_CBC_SHA"};
        engine.setEnabledCipherSuites(suites);
    }

    public void setEnabledCipherSuitesOnSocket() throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        SSLSocketFactory factory = context.getSocketFactory();
        Socket socket = factory.createSocket("localhost", 443);
        if (socket instanceof SSLSocket) {
            SSLSocket sslSocket = (SSLSocket) socket;
            String[] suites = {"TLS_RSA_WITH_AES_128_CBC_SHA"};
            sslSocket.setEnabledCipherSuites(suites);
        }
    }

    public void setEnabledCipherSuitesOnServerSocket() throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        SSLServerSocketFactory factory = context.getServerSocketFactory();
        java.net.ServerSocket serverSocket = factory.createServerSocket(8443);
        if (serverSocket instanceof SSLServerSocket) {
            SSLServerSocket sslServerSocket = (SSLServerSocket) serverSocket;
            String[] suites = {"TLS_RSA_WITH_AES_128_CBC_SHA"};
            sslServerSocket.setEnabledCipherSuites(suites);
        }
    }
}
