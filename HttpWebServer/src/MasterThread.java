/**
 * <p>
 *  The Master thread implementation for HTTP web server. The master thread listens
 *  to incoming requests on a specified port and adds them to a blocking queue.
 *  Then it is the responsibility of worker threads to read from the queue
 *  and service requests.
 *
 *  For performance reasons, we should not need to implement a thread pool with more
 *  than two master threads. Creating more masters will just be resource overkill since
 *  the service they provide is not time consuming. Also the masters will then accept request
 *  much faster that workers can actually respond.
 * </p>
 *
 * <p>
 *     @author Harshit Mehrotra
 *     Date: July 26, 2014
 * </p>
 */
public class MasterThread implements Runnable {
    private int _portNum;

    public MasterThread(int portNum){
        _portNum = portNum;
    }

    public void run(){

        System.out.println("Executing master thread: " + Thread.currentThread().getId());

        try{
            Thread.sleep(300);
        }
        catch (Exception e){

        }
    }
}
