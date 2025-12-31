// License: Commons Clause License Condition v1.0[LGPL-2.1-only]

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class Bad {

    BufferedReader in = null;
    PrintWriter out = null;
    Socket pingSocket = null;

    public void badsocket1() {
        try {
            // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
            pingSocket = new Socket("telnet://example.com", 23);
            out = new PrintWriter(pingSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

            out.println("ping");
            System.out.println(in.readLine());
            out.close();
            in.close();
            pingSocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void badsocket2() throws Exception {
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket("ftp://example.com", 21);
        out = new PrintWriter(pingSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void badsocket3() throws Exception {
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket("http://example.com", 80);
        out = new PrintWriter(pingSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void badsocket4() throws Exception {
        String servername = "telnet://example.com";
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket(servername, 23);
        out = new PrintWriter(pingSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void badsocket5() throws Exception {
        String servername = "ftp://example.com";
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket(servername, 21);
        out = new PrintWriter(pingSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void badsocket6() throws Exception {
        String servername = "http://example.com";
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket(servername, 80);

        out = new PrintWriter(pingSocket.getOutputStream(), true);

        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void badsocket7() throws Exception {
        String servername = "TELNET://example.com";
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket(servername, 23);

        out = new PrintWriter(pingSocket.getOutputStream(), true);

        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void badsocket8() throws Exception {
        String servername = "FTP://example.com";
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket(servername, 21);

        out = new PrintWriter(pingSocket.getOutputStream(), true);

        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void badsocket9() throws Exception {
        String servername = "HTTP://example.com";
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket(servername, 80);

        out = new PrintWriter(pingSocket.getOutputStream(), true);

        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void badsocket10() throws Exception {
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket("HTTP://example.com", 80);

        out = new PrintWriter(pingSocket.getOutputStream(), true);
        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        pingSocket.close();
    }

    public void badsocket11() throws Exception {
        BufferedReader in = null;
        Socket pingSocket = null;
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket("http://example.com", 80);

        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        String serverResponse;
        while ((serverResponse = in.readLine()) != null) {
            System.out.println("Server: " + serverResponse);
        }

        System.out.println(in.readLine());
        in.close();
        pingSocket.close();
    }

    public void badsocket12() throws Exception {

        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket("example.com", 23);
        out = new PrintWriter(pingSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void badsocket13() throws Exception {
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket("example.com", 21);
        out = new PrintWriter(pingSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void badsocket14() throws Exception {
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket("example.com", 80);
        out = new PrintWriter(pingSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void badsocket15() throws Exception {
        String servername = "example.com";
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket(servername, 23);
        out = new PrintWriter(pingSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void badsocket16() throws Exception {
        String servername = "example.com";
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket(servername, 21);
        out = new PrintWriter(pingSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void badsocket17() throws Exception {
        String servername = "example.com";
        // ruleid: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket(servername, 80);
        out = new PrintWriter(pingSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }
}

class Ok {

    BufferedReader in = null;
    PrintWriter out = null;
    Socket pingSocket = null;

    public void oksocket1() throws Exception {
        // ok: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket("ssh://example.com", 22);

        out = new PrintWriter(pingSocket.getOutputStream(), true);

        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void oksocket2() throws Exception {
        // ok: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket("sftp://example.com", 22);

        out = new PrintWriter(pingSocket.getOutputStream(), true);

        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void oksocket3() throws Exception {
        // ok: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket("https://example.com", 443);

        out = new PrintWriter(pingSocket.getOutputStream(), true);

        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void oksocket4() throws Exception {
        String servername = "ssh://example.com";
        // ok: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket(servername, 22);

        out = new PrintWriter(pingSocket.getOutputStream(), true);

        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void oksocket5() throws Exception {
        String servername = "sftp://example.com";
        // ok: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket(servername, 22);

        out = new PrintWriter(pingSocket.getOutputStream(), true);

        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void oksocket6() throws Exception {
        String servername = "https://example.com";
        // ok: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket(servername, 443);
        out = new PrintWriter(pingSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }

    public void badsocket17() throws Exception {
        String servername = "example.com";
        // ok: java_crypto_rule-SocketRequestUnsafeProtocols
        pingSocket = new Socket(servername, 443);
        out = new PrintWriter(pingSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }
}
