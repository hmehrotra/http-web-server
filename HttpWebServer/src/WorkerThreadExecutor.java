import java.util.concurrent.ExecutorService;

/**
 * This class spawns a thread to run worker threads infinitely
 *
 * @author Harshit Mehrotra
 * Date: July 26, 2014
 */
public class WorkerThreadExecutor implements Runnable{
    private ExecutorService workerPool;

    public WorkerThreadExecutor(ExecutorService masterPool){
        this.workerPool = masterPool;
    }
    public void run(){
        while(true)
            workerPool.execute(new WorkerThread());
    }
}