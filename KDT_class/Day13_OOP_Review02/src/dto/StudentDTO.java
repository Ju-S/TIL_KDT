package dto;

import java.util.Date;

public class StudentDTO {
    private int id;
    private String name;
    private int kor;
    private int eng;
    private int math;
    private Date evaluatedDate;

    public StudentDTO() {
    }

    public StudentDTO(int id, String name, int kor, int eng, int math, Date evaluatedDate) {
        this.id = id;
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        this.evaluatedDate = evaluatedDate;
    }

    public StudentDTO(String name, int kor, int eng, int math, Date evaluatedDate) {
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        this.evaluatedDate = evaluatedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEvaluatedDate() {
        return evaluatedDate;
    }

    public void setEvaluatedDate(Date evaluatedDate) {
        this.evaluatedDate = evaluatedDate;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getTotal() {
        return kor + eng + math;
    }

    public double getAvg() {
        return (kor + eng + math) / 3.0;
    }
}
