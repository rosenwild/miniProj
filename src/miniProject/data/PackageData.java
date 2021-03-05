package miniProject.data;

import miniProject.Students;

import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {

    String operationType;
    ArrayList<Students> students;
    Students student;

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public ArrayList<Students> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Students> students) {
        this.students = students;
    }

    public Students getStudent() {
        return student;
    }
}
