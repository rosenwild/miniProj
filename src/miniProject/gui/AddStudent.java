package miniProject.gui;

import miniProject.DBManager;
import miniProject.Students;
import miniProject.data.PackageData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AddStudent extends JPanel {

    private MainFrame parent;
    private JLabel name, surname, age;
    private JTextField nameField, surnameField;
    private JButton add, back;
    private Integer[] ages = new Integer[150];
    private JComboBox ageBox;

    public AddStudent(MainFrame parent) {

        DBManager db = new DBManager();
        db.connect();

        for(Integer i = 0; i <150; i++){
            ages[i] = i;
        }

        this.parent = parent;

        setSize(500,500);
        setLayout(null);

        name = new JLabel("Name:");
        name.setSize(100, 30);
        name.setLocation(100, 100);
        add(name);

        nameField = new JTextField("insert name");
        nameField.setSize(150, 30);
        nameField.setLocation(250, 100);
        add(nameField);

        surname = new JLabel("Surname: ");
        surname.setSize(100, 30);
        surname.setLocation(100, 160);
        add(surname);

        surnameField = new JTextField("insert surname");
        surnameField.setSize(150, 30);
        surnameField.setLocation(250, 160);
        add(surnameField);

        age = new JLabel("Age: ");
        age.setSize(100, 30);
        age.setLocation(100, 210);
        add(age);

        ageBox = new JComboBox(ages);
        ageBox.setSize(150, 30);
        ageBox.setLocation(250, 210);
        add(ageBox);

        add = new JButton("ADD");
        add.setSize(100, 30);
        add.setLocation(100, 370);
        add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ee) {
                String name1 = nameField.getText();
                String surname1 = surnameField.getText();
                Integer age1 = (Integer) ageBox.getSelectedItem();
                if(!name1.equals("") && !surname1.equals("")){

                    Socket socket = null;
                    try {
                        socket = new Socket("127.0.0.1", 1027);
                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                        Students student = new Students(null, name1, surname1, age1);
                        db.addStudents(student);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    nameField.setText("");
                    surnameField.setText("");
                    ageBox.setSelectedIndex(0);
                }
            }
        });

        back = new JButton("BACK");
        back.setSize(100,30);
        back.setLocation(300,370);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.getAddStudentPage().setVisible(false);
                parent.getMainMenuPage().setVisible(true);
            }
        });
    }
}

