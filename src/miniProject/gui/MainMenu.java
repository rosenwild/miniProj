package miniProject.gui;


import miniProject.Students;
import miniProject.data.PackageData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MainMenu extends JPanel {

    private MainFrame parent;

    private JButton addStudentButton;
    private JButton listStudentsButton;
    private JButton exitButton;

    public MainMenu(MainFrame parent) {

        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",1027);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            final PackageData[] packageData = {new PackageData()};

            this.parent = parent;

            setSize(500,500);
            setLayout(null);

            addStudentButton = new JButton("ADD STUDENT");
            addStudentButton.setSize(300,30);
            addStudentButton.setLocation(100,100);
            add(addStudentButton);
            addStudentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    parent.getMainMenuPage().setVisible(false);
                    parent.getAddStudentPage().setVisible(true);
                }
            });

            listStudentsButton = new JButton("LIST STUDENTS");
            listStudentsButton.setSize(300,30);
            listStudentsButton.setLocation(100,150);
            add(listStudentsButton);
            listStudentsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
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
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    parent.getMainMenuPage().setVisible(false);
                    parent.getListStudentPage().setVisible(true);
                }
            });

            exitButton = new JButton("EXIT");
            exitButton.setSize(300,30);
            exitButton.setLocation(100,200);
            add(exitButton);
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.exit(0);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

