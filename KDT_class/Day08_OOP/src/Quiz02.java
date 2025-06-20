import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPFile;

import java.io.File;
import java.util.Scanner;

public class Quiz02 {
    static Scanner sc = new Scanner(System.in);
    static FTPClient client = new FTPClient();

    public static void main(String[] args) {
        while (true) {
            System.out.println("=== FTP Client Program");
            System.out.println("1. Connect FTP Server");
            System.out.println("2. Exit Program");
            System.out.print(">>> ");
            switch (inputToInt()) {
                case 1:
                    connectToUrlPort();
                    loginWithIdPassword();
                    break;
                case 2:
                    disconnectFTPServer();
                    System.exit(0);
            }

            while(client.isConnected()) {
                System.out.println("1. Upload File");
                System.out.println("2. Download File");
                System.out.println("3. Disconnect FTP Server");
                System.out.print(">>> ");
                switch (inputToInt()) {
                    case 1:
                        tryToUpload();
                        break;
                    case 2:
                        tryToDownload();
                        break;
                    case 3:
                        disconnectFTPServer();
                        break;
                }
            }
        }
    }

    //-------------------FTP 서버 연결-----------------
    public static void connectToUrlPort() {
        String url;
        int port;

        while (true) {
            System.out.print("input url>>> ");
            url = inputToString();
            System.out.print("input port>>> ");
            port = inputToInt();

            try {
                System.out.println("url: " + url + ", port: " + port + "로 접속 시도 중 입니다..");
                client.connect(url, port);
                System.out.println("FTP Server is connected!");
                break;
            } catch (Exception e) {
                System.out.println("없는 주소이거나 잘못된 포트번호 입니다.\n다시 확인해주세요.");
            }
        }
    }

    public static void loginWithIdPassword() {
        String id, password;

        while (true) {
            System.out.print("input id: ");
            id = inputToString();
            System.out.print("input password: ");
            password = inputToString();

            try {
                client.login(id, password);
                System.out.println("Login Success!");
                break;
            } catch (Exception e) {
                System.out.println("아이디 혹은 비밀번호가 잘못되었습니다.\n다시 확인해주세요.");
            }
        }
    }

    //------------------FTP 기능----------------------
    public static void tryToUpload() {
        File uploadDirectory, uploadFile;

        try{
            System.out.println("업로드할 파일의 경로를 입력하세요.( e.g, C\\Users\\Download )");
            System.out.print(">>>");
            uploadDirectory = new File(inputToString());

            for(String i : uploadDirectory.list()){
                System.out.println(i);
            }

            System.out.print("업로드할 파일을 입력하세요>>> ");
            uploadFile = new File(uploadDirectory.getPath() + "\\" + inputToString());

            client.upload(uploadFile);
            System.out.println("Upload Complete!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void tryToDownload() {
        try {
            System.out.println("Current Directory: " + client.currentDirectory());

            FTPFile[] fileList = client.list();
            for (int i = 0; i < fileList.length; i++) {
                System.out.println(fileList[i].getName() + "." + fileList[i].getType() + "\t" + fileList[i].getSize());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.print("다운받을 파일명을 입력하세요>>> ");
            String downloadFile = inputToString();

            System.out.print("다운받을 경로와 새로운 파일명을 입력하세요>>>");
            File newFile = new File(inputToString());

            client.download(downloadFile, newFile);
            System.out.println("Download Complete!");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("다운받을 파일이 존재하지 않습니다.\n다시 확인해주세요.");
        }
    }

    //------------------연결해제----------------------
    public static void disconnectFTPServer() {
        try {
            if (client.isConnected()) {
                client.disconnect(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //------------------입력예외----------------------
    public static int inputToInt() {
        int input;

        while (true) {
            try {
                input = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("잘못된 입력입니다.\n다시 확인해주세요.");
                System.out.print(">>> ");
            }
        }
        return input;
    }

    public static String inputToString() {
        String input;

        while (true) {
            try {
                input = sc.nextLine();
                break;
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("잘못된 입력입니다.\n다시 확인해주세요.");
                System.out.print(">>> ");
            }
        }
        return input;
    }
}
