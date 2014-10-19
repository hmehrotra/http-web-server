import java.net.Socket;

/**
 * <p>
 *  This class contains implementation of worker threads for HTTP Web server.
 *  Each worker threads reads request from a given socket and responds to it.
 *
 *  It future extension, a worker thread will delegate request for image compression to a proxy server
 * </p>
 *
 * @author Harshit Mehrotra
 * Date: July 26, 2014
 */
public class WorkerThread implements Runnable {
    private Socket _socket;

    public WorkerThread(Socket socket){
        _socket = socket;
    }

    public void run(){
        // Read from the socket and complete the request
    }
}
