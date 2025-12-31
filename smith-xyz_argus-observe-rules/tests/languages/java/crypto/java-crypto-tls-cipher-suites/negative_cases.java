package javacryptotlsciphersuites;

import javax.net.ssl.SSLContext;

public class NegativeCases {
    public void noCipherSuites() throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
    }
}
