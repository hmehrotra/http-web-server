import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *  Implementation of HTTP Server. This class contains a bare bone skeleton to instantiate master and slave threads.
 *  Thread pool management for both master and slave thread is done through Executors.
 *  Also the functionality of both master and worker threads is explained in respective classes
 * </p>
 *
 * @author Harshit Mehrotra
 * date July 25, 2014
 */
public class HttpServer {

    private static final HTTPServerResources _serverResources = new HTTPServerResources();
    private static ExecutorService _masterPool;
    private static ExecutorService _workerPool;
    private static HttpServer _httpServer;

    private HttpServer(){}

    public static HttpServer getInstance(){
        if (_httpServer == null){
            _httpServer = new HttpServer();
        }
        return _httpServer;
    }

    private ExecutorService getMasterPool(){
        return _masterPool;
    }

    private ExecutorService getWorkerPool(){
        return _workerPool;
    }

    /* Constructor initializes the thread pools */
    public void initializeThreadExecutors(){
        /* Create master and worker fixed thread pools */
        _masterPool = Executors.newFixedThreadPool(_serverResources.MAX_MASTER_THREADS);

        _workerPool = Executors.newFixedThreadPool(_serverResources.MAX_WORKER_THREADS);
    }

    public static void main(String args[]){
        HttpServer.getInstance().initializeThreadExecutors();

        Thread worker = new Thread(new WorkerThreadExecutor(HttpServer.getInstance().getWorkerPool()));
        worker.start();

        while(true){
            HttpServer.getInstance().getMasterPool().execute(new MasterThread(8001));
        }
    }
}


