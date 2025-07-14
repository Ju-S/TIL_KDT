package dao;

import dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private List<StudentDTO> studentList = new ArrayList<StudentDTO>();
    private int studentId = 1001;
    // movieDTO의 id 초기값

    public StudentDAO() {}

    // 영화 등록
    public void regStudent(StudentDTO newStudentInfo) {
        newStudentInfo.setId(studentId++);
        studentList.add(newStudentInfo);
    }

    // 영화 목록 반환
    public List<StudentDTO> getStudentList() {
        return studentList;
    }

    //-----------검색-------------
    // 학생 검색(이름)(resultList.isEmpty()로 검색결과가 있는지 확인)
    public List<StudentDTO> findStudentByName(String name) {
        ArrayList<StudentDTO> resultList = new ArrayList<>();

        for(StudentDTO student : studentList) {
            if(student.getName().contains(name)) {
                resultList.add(student);
            }
        }

        return resultList;
    }

    // 영화 단일 검색(id)(null 이라면 검색안됨)
    public StudentDTO findStudentById(int targetId) {
        for(StudentDTO student : studentList) {
            if(student.getId() == targetId) {
                return student;
            }
        }
        return null;
    }

    // 단일 영화 Index 검색(id)(-1 이라면 검색안됨)
    public int findIndexById(int targetId) {
        StudentDTO targetStudent = findStudentById(targetId);

        if(targetStudent != null) {
            return studentList.indexOf(targetStudent);
        } else {
            return -1;
        }
    }
    //---------------------------

    // 영화 수정
    public void modifyStudentInfo(StudentDTO modifiedInfo) {
        int targetIndex = findIndexById(modifiedInfo.getId());

        if(targetIndex != -1) {
            studentList.set(targetIndex, modifiedInfo);
        }
    }

    // 영화 삭제(true 반환 시, 삭제 성공)
    public boolean removeStudent(int targetId) {
        int targetIndex = findIndexById(targetId);

        if(targetIndex != -1) {
            studentList.remove(targetIndex);
            return true;
        } else {
            return false;
        }
    }
}
