package miniProject.gui;

import miniProject.Students;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListStudents extends JPanel {

    private final String[] header = {"Name", "Surname", "Age"};
    private final JTable table;

    public ListStudents(MainFrame parent) {

        setSize(500, 500);
        setLayout(null);

        JLabel label = new JLabel("SECOND PAGE");
        label.setSize(300, 30);
        label.setLocation(100, 100);
        add(label);

        JButton back = new JButton("Back");
        back.setSize(300, 30);
        back.setLocation(100, 150);
        add(back);
        back.addActionListener(actionEvent -> {
            parent.getListStudentPage().setVisible(false);
            parent.getMainMenuPage().setVisible(true);
        });

        table = new JTable();
        table.setFont(new Font("Calibri", Font.PLAIN, 12));
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(300, 200);
        scrollPane.setLocation(100, 200);
        add(scrollPane);
    }

    public void generateTable(ArrayList<Students> students){

        Object[][] data = new Object[students.size()][3];

        for(int i =0;i<students.size();i++){
            data[i][0] = students.get(i).getName();
            data[i][1] = students.get(i).getSurname();
            data[i][2] = students.get(i).getAge();
        }

        DefaultTableModel model = new DefaultTableModel(data, header);
        table.setModel(model);

    }
}
