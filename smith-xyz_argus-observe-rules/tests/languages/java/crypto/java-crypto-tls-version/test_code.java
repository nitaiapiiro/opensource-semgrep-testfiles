package javacryptotlsversion;

import javax.net.ssl.SSLContext;

public class TestCode {
    public void tlsVersions() {
        SSLContext context1 = SSLContext.getInstance("TLSv1");
        SSLContext context2 = SSLContext.getInstance("TLSv1.1");
        SSLContext context3 = SSLContext.getInstance("TLSv1.2");
        SSLContext context4 = SSLContext.getInstance("TLSv1.3");
    }

    public void sslVersions() {
        SSLContext context1 = SSLContext.getInstance("SSL");
        SSLContext context2 = SSLContext.getInstance("SSLv3");
    }

    public void tlsContextInit() {
        SSLContext context = SSLContext.getInstance("TLSv1.2");
        context.init(null, null, null);
    }
}
