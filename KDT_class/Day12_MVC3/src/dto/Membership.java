package dto;

abstract public class Membership {
    private int id;
    private String name;
    private double point;
    private int grade;

    public Membership() {
    }

    public Membership(int id, String name, double point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    abstract public double getBonus();

    abstract public int getGrade();
}
