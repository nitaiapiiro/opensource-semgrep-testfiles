// License: Commons Clause License Condition v1.0[LGPL-2.1-only]

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URL;

class Bad {
    public static void sendbad1() throws IOException {
        // ruleid: java_crypto_rule-HTTPUrlConnectionHTTPRequest
        URL obj = new URL("http://example.com");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        con.connect();
    }

    public static void sendbad2() throws IOException {
        String url = "http://example.com";
        // ruleid: java_crypto_rule-HTTPUrlConnectionHTTPRequest
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        con.connect();
    }

    public static void sendbad3() throws IOException {
        String url = "http://example.com";
        // ruleid: java_crypto_rule-HTTPUrlConnectionHTTPRequest
        URL urlObj = new URL(url);
        URLConnection urlCon = urlObj.openConnection();
        InputStream inputStream = urlCon.getInputStream();
    }

    public static void sendbad4() throws IOException {
        // ruleid: java_crypto_rule-HTTPUrlConnectionHTTPRequest
        URL urlObj = new URL("http://example.com");
        URLConnection urlCon = urlObj.openConnection();
        InputStream inputStream = urlCon.getInputStream();
    }
}

class Ok {
    public static void sendok1() throws IOException {
        // ok: java_crypto_rule-HTTPUrlConnectionHTTPRequest
        URL obj = new URL("https://example.com");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        con.connect();
    }

    public static void sendok2() throws IOException {
        String url = "https://example.com";
        // ok: java_crypto_rule-HTTPUrlConnectionHTTPRequest
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        con.connect();
    }

    public static void sendok3() throws IOException {
        String url = "https://example.com";
        // ok: java_crypto_rule-HTTPUrlConnectionHTTPRequest
        URL urlObj = new URL(url);
        URLConnection urlCon = urlObj.openConnection();
        InputStream inputStream = urlCon.getInputStream();
    }

    public static void sendok4() throws IOException {
        // ok: java_crypto_rule-HTTPUrlConnectionHTTPRequest
        URL urlObj = new URL("https://example.com");
        URLConnection urlCon = urlObj.openConnection();
        InputStream inputStream = urlCon.getInputStream();
    }
}
