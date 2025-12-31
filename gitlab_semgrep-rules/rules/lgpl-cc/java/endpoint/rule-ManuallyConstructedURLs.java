// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
package com.gitlab.spring.controller.endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// ref:java_endpoint_rule-ManuallyConstructedURLs
@Controller
public class ManuallyConstructedURLs {

    @RequestMapping(value = "/endpoint/unsafeurlget", method = RequestMethod.GET)
    public String unsafeULRConstruction1(@RequestParam String url) throws IOException {

        // ruleid: java_endpoint_rule-ManuallyConstructedURLs
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("GET request did not work.");
        }
        return "user";
    }

    @RequestMapping(value = "/endpoint/safeurlget1", method = RequestMethod.GET)
    public String safeULRConstruction1(@RequestParam int id) throws IOException {

        // ok: java_endpoint_rule-ManuallyConstructedURLs
        String url = String.format("https://%s/api/v1/employee/%d", "placeholder.restapiexample.com", id);
        String res = url_execution(url);
        return "user";
    }

    @RequestMapping(value = "/endpoint/safeurlget2", method = RequestMethod.GET)
    public String safeULRConstruction2(@RequestParam int id) throws IOException {

        // ok: java_endpoint_rule-ManuallyConstructedURLs
        String url = String.format("https://%s/api/v1/employees", "placeholder.restapiexample.com");
        String res = url_execution(url);
        return "user";
    }

    private String url_execution(String url) throws ProtocolException {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println(response.toString());
                return response.toString();
            } else {
                System.out.println("GET request did not work.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}