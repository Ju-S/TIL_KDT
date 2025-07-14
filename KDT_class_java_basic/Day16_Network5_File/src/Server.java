import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 43212;
        ServerSocket server = new ServerSocket(port);

        Socket client = server.accept();

        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());

        String homeDirPath = "C:\\Users\\keduit\\Downloads\\";
        File home = new File(homeDirPath);
        File[] files = home.listFiles();

        dataOutputStream.writeInt(files.length);
        for(File file : files) {
            dataOutputStream.writeUTF(file.getName());
        }
        dataOutputStream.flush();

        String targetFileName = dataInputStream.readUTF();
        FileInputStream fileInputStream = new FileInputStream(homeDirPath + targetFileName);
        byte[] fileContents = fileInputStream.readAllBytes();

        dataOutputStream.writeInt(fileContents.length);
        dataOutputStream.write(fileContents);
        dataOutputStream.flush();

        fileInputStream.close();
    }
}
