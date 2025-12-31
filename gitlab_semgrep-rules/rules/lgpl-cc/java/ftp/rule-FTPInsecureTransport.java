// License: Commons Clause License Condition v1.0[LGPL-2.1-only]

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.net.ftp.FTPClient;

class FTPInsecureTransport {

    public void run() throws Exception {
        System.out.println("The connections will time out after 1 second each as server addresses does not exist.");
        unsafe1();
        unsafe2();
        safe1();
    }

    public static void unsafe1() {
        System.out.println("Running Unsafe1()");
        String server = "www.yourserver.net";
        int port = 21;

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.setConnectTimeout(1000);
            // ruleid: java_ftp_rule-FTPInsecureTransport
            ftpClient.connect(server, port);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void unsafe2() {
        System.out.println("Running Unsafe2()");
        try {
            // ruleid: java_ftp_rule-FTPInsecureTransport
            URL url = new URL("ftp://user01:pass1234@ftp.foo.com/README.txt;type=i");
            URLConnection urlc = url.openConnection();
            urlc.setConnectTimeout(1000);
            InputStream is = urlc.getInputStream();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void safe1() {
        System.out.println("Running Safe1()");
        try {
            // ok: java_ftp_rule-FTPInsecureTransport
            URL url = new URL("http://somerandomurl23432.com");
            URLConnection urlc = url.openConnection();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
