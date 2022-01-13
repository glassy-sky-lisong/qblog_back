package cn.quasar.blog;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class JwtUtils {
    public static Key key;

    static {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
}
