// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/problem-based-packs/insecure-transport/java-stdlib/unirest-http-request.java
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;

class UnirestHTTPRequest {

    public void bad1() {
        // ruleid: java_crypto_rule-UnirestHTTPRequest
        HttpResponse<JsonNode> response = Unirest.post("http://httpbin.org/post")
                .header("accept", "application/json")
                .queryString("apiKey", "123")
                .field("parameter", "value")
                .field("firstname", "Gary")
                .asJson();
    }

    public void bad2() {
        // ruleid: java_crypto_rule-UnirestHTTPRequest
        Unirest.get("http://httpbin.org")
                .queryString("fruit", "apple")
                .queryString("droid", "R2D2")
                .asString();
    }

    public void bad3() {
        // ruleid: java_crypto_rule-UnirestHTTPRequest
        HttpResponse response = Unirest.delete("http://httpbin.org").asEmpty();

        // ruleid: java_crypto_rule-UnirestHTTPRequest
        Unirest.patch("http://httpbin.org");

        String url = "http://httpbin.org";
        // ruleid: java_crypto_rule-UnirestHTTPRequest
        HttpResponse response = Unirest.delete(url).asEmpty();

        String url2 = "http://httpbin.org";
        // ruleid: java_crypto_rule-UnirestHTTPRequest
        Unirest.patch(url2);
    }

    public void ok1() {
        // ok: java_crypto_rule-UnirestHTTPRequest
        HttpResponse<JsonNode> response = Unirest.post("https://httpbin.org/post")
                .header("accept", "application/json")
                .queryString("apiKey", "123")
                .field("parameter", "value")
                .field("firstname", "Gary")
                .asJson();
    }

    public void ok2() {
        // ok: java_crypto_rule-UnirestHTTPRequest
        Unirest.get("https://httpbin.org")
                .queryString("fruit", "apple")
                .queryString("droid", "R2D2")
                .asString();
    }

    public void ok3() {
        // ok: java_crypto_rule-UnirestHTTPRequest
        HttpResponse response = Unirest.delete("https://httpbin.org").asEmpty();

        // ok: java_crypto_rule-UnirestHTTPRequest
        Unirest.patch("https://httpbin.org");
    }
}