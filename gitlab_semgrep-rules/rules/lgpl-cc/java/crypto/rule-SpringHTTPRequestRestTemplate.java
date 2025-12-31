// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/problem-based-packs/insecure-transport/java-spring/spring-http-request.java

import java.net.URI;
import custom.Foo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class Bad {
    public void bad1() {
        RestTemplate restTemplate = new RestTemplate();
        // ruleid: java_crypto_rule-SpringHTTPRequestRestTemplate
        restTemplate.delete("http://example.com");
    }

    public void bad2() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://example.com";
        // ruleid: java_crypto_rule-SpringHTTPRequestRestTemplate
        restTemplate.delete(url);
    }

    public void bad3() {
        RestTemplate restTemplate = new RestTemplate();
        URI url = new URI("http://example.com");
        // ruleid: java_crypto_rule-SpringHTTPRequestRestTemplate
        restTemplate.delete(url);
    }

    public void bad4(Object obj) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        // ruleid: java_crypto_rule-SpringHTTPRequestRestTemplate
        restTemplate.put(URI.create("http://example.com"), obj);
    }

    public void bad5(Object object) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://example.com";
        // ruleid: java_crypto_rule-SpringHTTPRequestRestTemplate
        restTemplate.put(url, object);
    }

    public void bad6(Object object) {
        RestTemplate restTemplate = new RestTemplate();
        URI url = new URI("http://example.com");
        // ruleid: java_crypto_rule-SpringHTTPRequestRestTemplate
        restTemplate.put(url, object);
    }

    public void bad7() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/spring-rest/foos";
        // ruleid: java_crypto_rule-SpringHTTPRequestRestTemplate
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
    }

    public void bad8() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Foo> request = new HttpEntity<>(new Foo("bar"));
        String fooResourceUrl = "http://example.com";
        // ruleid: java_crypto_rule-SpringHTTPRequestRestTemplate
        Foo foo = restTemplate.postForObject(fooResourceUrl, request, Foo.class);
    }

    public void bad9(String headers) {
        RestTemplate template = new RestTemplate();
        Foo updatedInstance = new Foo("newName");
        updatedInstance.setId("1");
        String resourceUrl = "http://example.com";
        HttpEntity<Foo> requestUpdate = new HttpEntity<>(updatedInstance, headers);
        // ruleid: java_crypto_rule-SpringHTTPRequestRestTemplate
        template.exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Void.class);
    }
}

class Safe {
    public void safe1() {
        RestTemplate restTemplate = new RestTemplate();
        // ruleid: java_crypto_rule-SpringHTTPRequestRestTemplate
        restTemplate.delete("http://example.com");
    }

    public void safe2() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://example.com";
        // ok: java_crypto_rule-SpringHTTPRequestRestTemplate
        restTemplate.delete(url);
    }

    public void safe3() {
        RestTemplate restTemplate = new RestTemplate();
        URI url = new URI("https://example.com");
        // ok: java_crypto_rule-SpringHTTPRequestRestTemplate
        restTemplate.delete(url);
    }

    public void safe4(Object obj) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        // ok: java_crypto_rule-SpringHTTPRequestRestTemplate
        restTemplate.put(URI.create("https://example.com"), obj);
    }

    public void safe5(Object object) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://example.com";
        // ok: java_crypto_rule-SpringHTTPRequestRestTemplate
        restTemplate.put(url, object);
    }

    public void safe6(Object object) {
        RestTemplate restTemplate = new RestTemplate();
        URI url = new URI("https://example.com");
        // ok: java_crypto_rule-SpringHTTPRequestRestTemplate
        restTemplate.put(url, object);
    }

    public void safe7() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://localhost:8080/spring-rest/foos";
        // ok: java_crypto_rule-SpringHTTPRequestRestTemplate
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
    }

    public void safe8() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Foo> request = new HttpEntity<>(new Foo("bar"));
        String fooResourceUrl = "https://example.com";
        // ok: java_crypto_rule-SpringHTTPRequestRestTemplate
        Foo foo = restTemplate.postForObject(fooResourceUrl, request, Foo.class);
    }

    public void safe9(String headers) {
        RestTemplate template = new RestTemplate();
        Foo updatedInstance = new Foo("newName");
        updatedInstance.setId("1");
        String resourceUrl = "https://example.com";
        HttpEntity<Foo> requestUpdate = new HttpEntity<>(updatedInstance, headers);
        // ok: java_crypto_rule-SpringHTTPRequestRestTemplate
        template.exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Void.class);
    }
}