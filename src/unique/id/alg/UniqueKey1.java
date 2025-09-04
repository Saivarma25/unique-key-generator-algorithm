package unique.id.alg;

import unique.id.hashing.Hashing;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Generator 2,
 * which combines host details, process id, current time and a number that is stored in memory
 *
 * @author Saivarma Akarapu
 */
public class UniqueKey1 {

    private static final String HOST_NAME;

    private static final long PID;

    private static final AtomicLong atomicLong;

    static {
        try {
            HOST_NAME = InetAddress.getLocalHost().getHostName();
            PID = ProcessHandle.current().pid();
            atomicLong = new AtomicLong();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private UniqueKey1() {
        // private constructor, to prevent object creation
    }

    /**
     * Method that generates unique alphanumeric key/id
     * @return String which hashed value of host, pid, time and incremented number
     */
    public static String getUniqueAlphaNumericKey() {
        return Hashing.getHashWithSHA256(String.format("%s-%d-%d-%s",
                HOST_NAME, PID, System.currentTimeMillis(), atomicLong.incrementAndGet()));
    }

}
