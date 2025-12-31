// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/problem-based-packs/insecure-transport/java-stdlib/telnet-request.java

import org.apache.commons.net.telnet.TelnetClient;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

class Bad {
    public void badtelnet1() {

        TelnetClient telnet = new TelnetClient();
        // ruleid: java_crypto_rule-TelnetRequest
        telnet.connect("rainmaker.wunderground.com");
    }

    public void badtelnet2() {
        TelnetClient telnet = null;
        telnet = new TelnetClient();
        // ruleid: java_crypto_rule-TelnetRequest
        telnet.connect("rainmaker.wunderground.com");
    }

    public void safe() {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession("username", "hostname", 22);
            session.setPassword("password");
            session.setConfig("StrictHostKeyChecking", "no");
            // ok: java_crypto_rule-TelnetRequest
            session.connect();
            System.out.println("Connected securely.");
        } catch (Exception e) {
            System.err.println("Secure connection failed: " + e.getMessage());
        }
    }
}
