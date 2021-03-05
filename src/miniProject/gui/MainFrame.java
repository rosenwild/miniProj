package miniProject.gui;


import miniProject.gui.AddStudent;
import miniProject.gui.ListStudents;
import miniProject.gui.MainMenu;

import javax.swing.*;

public class MainFrame extends JFrame {

    private MainMenu mainMenuPage;
    private AddStudent addStudent;
    private ListStudents listStudents;

    public MainFrame(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Application");
        setSize(500,500);
        setLayout(null);

        mainMenuPage = new MainMenu(this);
        mainMenuPage.setVisible(true);
        add(mainMenuPage);

        addStudent = new AddStudent(this);
        addStudent.setVisible(false);
        add(addStudent);

        listStudents = new ListStudents(this);
        listStudents.setVisible(false);
        add(listStudents);

    }

    public MainMenu getMainMenuPage() {
        return mainMenuPage;
    }

    public AddStudent getAddStudentPage() {
        return addStudent;
    }

    public ListStudents getListStudentPage() {
        return listStudents;
    }

}

