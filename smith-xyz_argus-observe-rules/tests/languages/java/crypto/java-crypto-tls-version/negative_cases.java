package javacryptotlsversion;

import javax.net.ssl.SSLContext;

public class NegativeCases {
    public void defaultTLS() {
        SSLContext context = SSLContext.getDefault();
    }
}
