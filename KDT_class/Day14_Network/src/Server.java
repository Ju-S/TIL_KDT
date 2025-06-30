import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws Exception{
        int port = 19283;

        ServerSocket server = new ServerSocket(port);
        server.accept();
    }
}
