package javacryptopasswordhashing;

import org.mindrot.jbcrypt.BCrypt;

public class ExampleCode {
    public void bcryptHashpw() {
        String password = "password";
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
    }

    public void bcryptCheckpw() {
        String password = "password";
        String hash = "$2a$10$...";
        boolean matches = BCrypt.checkpw(password, hash);
    }

    public void bcryptGensalt() {
        int rounds = 10;
        String salt = BCrypt.gensalt(rounds);
    }

    public void bcryptFull() {
        String password = "password";
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        boolean matches = BCrypt.checkpw("input", hash);
    }

    public void pbkdf2PasswordHashing() {
        javax.crypto.SecretKeyFactory factory = javax.crypto.SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    }
}
