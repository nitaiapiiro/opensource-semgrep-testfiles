// License: Commons Clause License Condition v1.0[LGPL-2.1-only]

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.HttpClient;

class Bad {
    public static void bad1() throws IOException {
        // ruleid: java_crypto_rule-HttpGetHTTPRequest
        HttpGet httpGet = new HttpGet("http://example.com");
        HttpClients.createDefault().execute(httpGet);
    }

    public static void bad2() throws IOException {
        String url = "http://example.com";
        // ruleid: java_crypto_rule-HttpGetHTTPRequest
        HttpGet httpGet = new HttpGet(url);
        HttpClients.createDefault().execute(httpGet);
    }

    public static void bad3() throws IOException {
        HttpClient client = new DefaultHttpClient();
        // ruleid: java_crypto_rule-HttpGetHTTPRequest
        HttpGet request = new HttpGet("http://test.com");
        client.execute(request);
    }
}

class Ok {
    public static void ok1() throws IOException {
        // ok: java_crypto_rule-HttpGetHTTPRequest
        HttpGet httpGet = new HttpGet("https://example.com");
        HttpClients.createDefault().execute(httpGet);
    }

    public static void ok2() throws IOException {
        String url = "https://example.com";
        // ok: java_crypto_rule-HttpGetHTTPRequest
        HttpGet httpGet = new HttpGet(url);
        HttpClients.createDefault().execute(httpGet);
    }
}
