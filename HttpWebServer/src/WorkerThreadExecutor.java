import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * This class spawns a thread to run worker threads infinitely
 *
 * @author Harshit Mehrotra
 * Date: July 26, 2014
 */
public class WorkerThreadExecutor implements Runnable{
    private static final HTTPServerResources _serverResources = new HTTPServerResources();
    private ExecutorService _workerPool;

    public WorkerThreadExecutor(ExecutorService workerPool){
        _workerPool = workerPool;
    }

    public void run(){
        while(true){
            try{
                Socket socket = _serverResources.requestQueue.take();
                _workerPool.execute(new WorkerThread(socket));
            }
            catch(InterruptedException it){
                System.out.println("Reading from blocking queue interrupted");
            }
        }
    }
}