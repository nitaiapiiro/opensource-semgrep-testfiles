// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://semgrep.dev/r?q=java.jjwt.security.jwt-none-alg.jjwt-none-alg
package crypto;

class JWTNoneAlgorithm {

    public void bad1() {
        // ruleid: java_crypto_rule_JwtNoneAlgorithm
        String token = io.jsonwebtoken.Jwts.builder()
                .subject("Bob")
                .compact();

        System.out.println("Lib1 bad1() JWT: " + token);
    }

    public void ok1() {
        javax.crypto.SecretKey key = io.jsonwebtoken.Jwts.SIG.HS256.key().build();
        // ok: java_crypto_rule_JwtNoneAlgorithm
        String token = io.jsonwebtoken.Jwts.builder()
                .subject("Bob")
                .signWith(key)
                .compact();

        System.out.println("Lib1 ok() JWT: " + token);
    }

    public void bad2() throws Exception {
        try {
            // ruleid: java_crypto_rule_JwtNoneAlgorithm
            String token = com.auth0.jwt.JWT.create()
                    .withIssuer("server")
                    .withSubject("userId")
                    .sign(com.auth0.jwt.algorithms.Algorithm.none());

            System.out.println("Lib2 bad2() JWT: " + token);
        } catch (Exception e) {
            System.out.println("Exception : " + e.getLocalizedMessage());
        }
    }

    public void bad3() {
        try {
            // PlainHeaders are set with None Algo.
            // ruleid: java_crypto_rule_JwtNoneAlgorithm
            com.nimbusds.jose.PlainHeader header = new com.nimbusds.jose.PlainHeader();

            com.nimbusds.jose.Payload payload = new com.nimbusds.jose.Payload("{\"iss\":\"abc\", \"exp\":1300819111}");
            com.nimbusds.jose.PlainObject plainObject = new com.nimbusds.jose.PlainObject(header, payload);
            String token = plainObject.serialize();
            System.out.println("Lib3 bad3() JWT: " + token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bad4() {
        try {
            // PlainHeaders are set with None Algo.
            // ruleid: java_crypto_rule_JwtNoneAlgorithm
            com.nimbusds.jose.PlainHeader header = new com.nimbusds.jose.PlainHeader.Builder().contentType("text/plain")
                    .customParam("exp", "1300819111")
                    .build();

            com.nimbusds.jose.Payload payload = new com.nimbusds.jose.Payload("{\"iss\":\"abc\"}");
            com.nimbusds.jose.PlainObject plainObject = new com.nimbusds.jose.PlainObject(header, payload);
            String token = plainObject.serialize();
            System.out.println("Lib3 bad4() JWT: " + token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
