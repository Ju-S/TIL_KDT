package models;

import classes.StudentDTO;

public class StudentDAO {
    private StudentDTO[] studentDTOS = new StudentDTO[3];
    private int index = 0;

    public void addStudent(StudentDTO studentDTO) {
        studentDTOS[index] = studentDTO;
        index++;
    }

    public StudentDTO[] getStudentDTOS() {
        return studentDTOS;
    }
}
