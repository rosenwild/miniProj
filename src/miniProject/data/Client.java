package miniProject.data;

import miniProject.gui.MainFrame;

public class Client {
    public static void main(String[] args) {
        try{
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

