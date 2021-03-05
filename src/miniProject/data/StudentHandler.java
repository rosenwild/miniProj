package miniProject.data;

import miniProject.DBManager;
import miniProject.Students;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class StudentHandler extends Thread{
    private final Socket socket;
    private final int id;
    static PackageData response = new PackageData();

    public StudentHandler(Socket socket,int id) {
        this.socket = socket;
        this.id = id;
    }

    public void run(){
        DBManager db = new DBManager();
        db.connect();
        try{
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            PackageData data;
            while((data = (PackageData) inputStream.readObject())!=null){
                switch (data.getOperationType()) {
                    case "ListStudent":
                        System.out.println("The user#" + id + " is watching students list");
                        ArrayList<Students> students = db.getAllStudents();
                        System.out.println(students);
                        response.setStudents(students);
                        outputStream.writeObject(response);
                        outputStream.reset();
                        break;
                    case "AddStudent":
                        System.out.println("The user#" + id + " is adding a new student");
                        Students student = ((PackageData) inputStream.readObject()).getStudent();
                        System.out.println(student);
                        db.addStudents(student);
                        System.out.println("success");
                        break;
                    case "EXIT":
                        System.out.println("The client#" + id + " left the server");
                        break;
                }
            }
        }catch (Exception ignored){

        }
    }
}

