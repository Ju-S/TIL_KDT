import dao.ContactDAO;
import dto.ContactDTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception{
        int port = 24563;
        ServerSocket server = new ServerSocket(port);

        Socket client = server.accept();

        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

        ContactDAO contactDAO = new ContactDAO();

        while(true) {
            int selectedMenu = dataInputStream.readInt();

            switch(selectedMenu) {
                case 1:
                    String phno = dataInputStream.readUTF();
                    contactDAO.addContact(new ContactDTO(phno));
                    break;
                case 2:
                    for(ContactDTO contact : contactDAO.getContactList()) {
                        dataOutputStream.writeUTF(contact.getPhno());
                        dataOutputStream.flush();
                    }
                    dataOutputStream.writeUTF("end");
                    dataOutputStream.flush();
                    break;
                default:
                    dataOutputStream.writeUTF("없는 메뉴입니다.");
                    dataOutputStream.flush();
                    break;
            }
        }
    }
}
