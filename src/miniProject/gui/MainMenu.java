package miniProject.gui;


import miniProject.Students;
import miniProject.data.PackageData;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MainMenu extends JPanel {

    public MainMenu(MainFrame parent) {

        Socket socket;
        try {
            socket = new Socket("127.0.0.1",1027);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            final PackageData[] packageData = {new PackageData()};

            setSize(500,500);
            setLayout(null);

            JButton addStudentButton = new JButton("ADD STUDENT");
            addStudentButton.setSize(300,30);
            addStudentButton.setLocation(100,100);
            add(addStudentButton);
            addStudentButton.addActionListener(actionEvent -> {
                parent.getMainMenuPage().setVisible(false);
                parent.getAddStudentPage().setVisible(true);
            });

            JButton listStudentsButton = new JButton("LIST STUDENTS");
            listStudentsButton.setSize(300,30);
            listStudentsButton.setLocation(100,150);
            add(listStudentsButton);
            listStudentsButton.addActionListener(actionEvent -> {
                packageData[0].setOperationType("ListStudent");
                try {
                    outputStream.writeObject(packageData[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    if ((packageData[0] = (PackageData) inputStream.readObject())!=null) {
                        ArrayList<Students> students = packageData[0].getStudents();
                        parent.getListStudentPage().generateTable(students);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                parent.getMainMenuPage().setVisible(false);
                parent.getListStudentPage().setVisible(true);
            });

            JButton exitButton = new JButton("EXIT");
            exitButton.setSize(300,30);
            exitButton.setLocation(100,200);
            add(exitButton);
            exitButton.addActionListener(actionEvent -> System.exit(0));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

