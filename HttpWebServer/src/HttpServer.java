import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *  Implementation of HTTP Server. This class does the following
 *  1) Instantiates {@link ExecutorService} for managing worker thread pool
 *  2) Registers HTTP server for shutdown even
 *  3) Listens to all incoming requests ideally from a HTTPClient (or load balancer) and adds
 *     them to blocking queue to be processed by one of the worker threads
 *
 *  The functionality of worker threads is explained {@link WorkerThread}
 * </p>
 *
 * @author Harshit Mehrotra
 * date July 25, 2014
 */
public class HttpServer {

    private static final HTTPServerResources _serverResources = new HTTPServerResources();
    private static ExecutorService _workerPool;
    private static HttpServer _httpServer;

    private static int _serverPort;
    private static ServerSocket _serverSocket;
    private static volatile boolean shouldContinueRunning;

    private HttpServer(){}

    public static HttpServer getInstance(){
        if (_httpServer == null){
            _httpServer = new HttpServer();
        }
        return _httpServer;
    }

    private ExecutorService getWorkerPool(){
        return _workerPool;
    }

    /**
     *  Constructor initializes the thread pools
     **/
    private void initialize(){
        shouldContinueRunning = true;

        HTTPServerResources.requestQueue = new ArrayBlockingQueue<Socket>(HTTPServerResources.MAX_QUEUE_SIZE);

        /* Create worker fixed thread pools */
        _workerPool = Executors.newFixedThreadPool(_serverResources.MAX_WORKER_THREADS);
    }

    /**
     * Registers server for capturing runtime shutdown events
     */
    private void registerShutdown(){
        // Capture shutdown requests
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){

            }
        });
    }

    /**
     * Starts the HTTP server and listens and responds to requests
     */
    private void start(int port) throws IOException{

       // Read port number argument
       _serverPort = port;
       _serverSocket = new ServerSocket(_serverPort);

       // Listen indefinitely to incoming HTTP requests
       while (shouldContinueRunning){
          try{
               Socket socket = _serverSocket.accept();
               HTTPServerResources.requestQueue.add(socket);
          }
          catch(IOException e){
              System.out.print("IOException occurred during accepting requests:" + e.getMessage());
          }
       }
    }

    public static void main(String args[]){

        // Initialize server resources
        HttpServer.getInstance().initialize();

        // Register server for shutdown events
        HttpServer.getInstance().registerShutdown();

        // Start executor service for Worker thread pool
        Thread worker = new Thread(new WorkerThreadExecutor(HttpServer.getInstance().getWorkerPool()));
        worker.start();

        try{
            // start the server
            HttpServer.getInstance().start(Integer.parseInt(args[0]));
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("No or invalid input provided for port number");
        }
        catch(IOException e){
            System.out.print("IOException occurred during starting server:" + e.getMessage());
        }
    }
}


