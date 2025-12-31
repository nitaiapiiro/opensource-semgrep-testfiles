package javacryptocertificatevalidation;

import javax.net.ssl.SSLContext;

public class NegativeCases {
    public void noCertificateValidation() throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
    }
}
