package miniProject.gui;

import miniProject.DBManager;
import miniProject.Students;

import javax.swing.*;

public class AddStudent extends JPanel {

    private final JTextField nameField;
    private final JTextField surnameField;
    private final JComboBox ageBox;

    public AddStudent(MainFrame parent) {

        DBManager db = new DBManager();
        db.connect();

        Integer[] ages = new Integer[150];
        for(int i = 0; i <150; i++){
            ages[i] = i;
        }

        setSize(500,500);
        setLayout(null);

        JLabel name = new JLabel("Name:");
        name.setSize(100, 30);
        name.setLocation(100, 100);
        add(name);

        nameField = new JTextField("insert name");
        nameField.setSize(150, 30);
        nameField.setLocation(250, 100);
        add(nameField);

        JLabel surname = new JLabel("Surname: ");
        surname.setSize(100, 30);
        surname.setLocation(100, 160);
        add(surname);

        surnameField = new JTextField("insert surname");
        surnameField.setSize(150, 30);
        surnameField.setLocation(250, 160);
        add(surnameField);

        JLabel age = new JLabel("Age: ");
        age.setSize(100, 30);
        age.setLocation(100, 210);
        add(age);

        ageBox = new JComboBox(ages);
        ageBox.setSize(150, 30);
        ageBox.setLocation(250, 210);
        add(ageBox);

        JButton add = new JButton("ADD");
        add.setSize(100, 30);
        add.setLocation(100, 370);
        add(add);
        add.addActionListener(ee -> {
            String name1 = nameField.getText();
            String surname1 = surnameField.getText();
            Integer age1 = (Integer) ageBox.getSelectedItem();
            if(!name1.equals("") && !surname1.equals("")){

                Students student = new Students(null, name1, surname1, age1);
                db.addStudents(student);

                nameField.setText("");
                surnameField.setText("");
                ageBox.setSelectedIndex(0);
            }
        });

        JButton back = new JButton("BACK");
        back.setSize(100,30);
        back.setLocation(300,370);
        add(back);
        back.addActionListener(actionEvent -> {
            parent.getAddStudentPage().setVisible(false);
            parent.getMainMenuPage().setVisible(true);
        });
    }
}

