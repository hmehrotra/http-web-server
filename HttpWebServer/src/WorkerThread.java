/**
 * <p>
 *  This class contains implementation of worker threads for HTTP Web server.
 *  Each worker threads reads request from a blocking queue and responds to it.
 *
 *  It future extension, a worker thread will delegate request for image compression to a proxy server
 * </p>
 *
 * @author Harshit Mehrotra
 * Date: July 26, 2014
 */
public class WorkerThread implements Runnable {

    public void run(){
        System.out.println("Inside the worker thread: " + Thread.currentThread().getId());
        try{
            Thread.sleep(300);
        }
        catch (Exception e){

        }
    }
}
