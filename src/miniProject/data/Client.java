package miniProject.data;

import miniProject.gui.MainFrame;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1",1027);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

