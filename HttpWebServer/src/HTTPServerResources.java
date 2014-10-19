import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/**
 * Contains configurations for HTTP Web Server
 *
 * @author Harshit Mehrotra
 * date July 25, 2014
 */
public class HTTPServerResources {

    protected static final int MAX_QUEUE_SIZE = 100;
    protected static final int MAX_WORKER_THREADS = 10;

    /* request queue */
    protected static BlockingQueue <Socket> requestQueue;

}
