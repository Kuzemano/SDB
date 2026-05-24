package r.real.users; // stavi go vo .util

import java.util.Base64;

public class TokenUtil {
    public static String extractToken(String token) {
        try {
            return new String(Base64.getDecoder().decode(token.split("\\.")[1]));
        } catch (Exception ex) {
            return token;
        }
    }
}