package unique.id.alg;

import unique.id.hashing.Hashing;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * Generator 1,
 * which combines host details, process id, current time and UUID for uniqueness
 *
 * @author Saivarma Akarapu
 */
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
        // private constructor, to prevent object creation
    }

    /**
     * Method that generates unique alphanumeric key/id
     * @return String which is hashed value of host, pid, time and UUID
     */
    public static String getUniqueAlphaNumericKey() {
        return Hashing.getHashWithSHA256(String.format("%s-%d-%d-%s",
                HOST_NAME, PID, System.currentTimeMillis(), UUID.randomUUID()));
    }

}
