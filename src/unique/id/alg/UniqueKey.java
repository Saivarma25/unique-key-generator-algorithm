package unique.id.alg;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.UUID;

public class UniqueKey {

    private static final String HOST_NAME;

    private static final long PID;

    static {
        try {
            HOST_NAME = InetAddress.getLocalHost().getHostName();
            PID = ProcessHandle.current().pid();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private UniqueKey() {
        // private constructor
    }

    public static String getUniqueKey() {
        return getHashWithSHA256(String.format("%s-%d-%d-%s",
                HOST_NAME, PID, System.currentTimeMillis(), UUID.randomUUID()));
    }

    private static String getHashWithSHA256(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Perform the hashing & Convert bytes to hex format
            return HexFormat.of().formatHex(digest.digest(input.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
