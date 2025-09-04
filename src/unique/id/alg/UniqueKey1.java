package unique.id.alg;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

public class UniqueKey1 {

    private static final String HOST_NAME;

    private static final long PID;

    private static AtomicLong atomicLong;

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
        // private constructor
    }

    public static String getUniqueKey() {
        return String.format("%s-%d-%d-%s", HOST_NAME, PID, System.currentTimeMillis(), atomicLong.incrementAndGet());
    }

}
