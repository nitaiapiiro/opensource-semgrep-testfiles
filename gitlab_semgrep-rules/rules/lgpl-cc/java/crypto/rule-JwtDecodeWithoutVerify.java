// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://semgrep.dev/r?q=java.java-jwt.security.audit.jwt-decode-without-verify.java-jwt-decode-without-verify
package crypto;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JwtDecodeWithoutVerify {

    private void verifyToken(String token, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
        DecodedJWT jwt2 = verifier.verify(token);
    }

    public void ok(String[] args) {
        System.out.println("Hello World!");

        try {
            Algorithm algorithm = Algorithm.HMAC256(args[0]);

            String token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
            // ok: java_crypto_rule_JwtDecodeWithoutVerify
            DecodedJWT jwt = JWT.decode(token);

        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Could not convert Claims.
        }

    }
}

abstract class App2 {

    private void bad(String[] args) {
        System.out.println("Hello World!");

        try {
            Algorithm algorithm = Algorithm.none();

            String token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
            // ruleid: java_crypto_rule_JwtDecodeWithoutVerify
            DecodedJWT jwt = JWT.decode(token);

        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Could not convert Claims.
        }

    }
}
