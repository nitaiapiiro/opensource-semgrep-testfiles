// License: LGPL-3.0 License (c) find-sec-bugs

import org.apache.http.impl.client.CloseableHttpClient;

class Bad {
    public void bad1() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://targethost/homepage");
        // ruleid: java_crypto_rule-HttpComponentsRequest
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
    }

    public void bad2() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // ruleid: java_crypto_rule-HttpComponentsRequest
        CloseableHttpResponse response1 = httpclient.execute(new HttpPost("http://example.com"));
    }
}

class Ok {
    public void ok1() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://targethost/homepage");
        // ok: java_crypto_rule-HttpComponentsRequest
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
    }

    public void ok2() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // ok: java_crypto_rule-HttpComponentsRequest
        CloseableHttpResponse response1 = httpclient.execute(new HttpPost("https://example.com"));
    }
}
