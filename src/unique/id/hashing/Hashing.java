package unique.id.hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/**
 * Class to perform hashing for both type of unique keys
 *
 * @author Saivarma Akarapu
 */
public class Hashing {

    private Hashing() {
        // private constructor, to prevent object creation
    }

    /**
     * Method to generate hash from the unique combination
     * @param input String to perform hashing
     * @return hash value of input string
     */
    public static String getHashWithSHA256(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Perform the hashing & Convert bytes to hex format - using java 17+
            return HexFormat.of().formatHex(digest.digest(input.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
