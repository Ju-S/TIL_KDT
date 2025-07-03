package server;

import server.dao.PostDAO;
import server.dao.UserDAO;
import server.thread.ServerThread;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 24567;
        ServerSocket server = new ServerSocket(port);

        UserDAO userDAO = new UserDAO();
        PostDAO postDAO = new PostDAO();

        while(true) {
            Socket client = server.accept();
            new ServerThread(client, userDAO, postDAO).start();
        }
    }
}
