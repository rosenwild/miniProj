package miniProject.data;

import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(1027);
            int id = 0;
            while(true){
                Socket socket = server.accept();
                id++;
                System.out.println("Bookworm #"+id+" connected");
                StudentHandler sh = new StudentHandler(socket,id);
                sh.start();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}


