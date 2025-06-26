package dto;

public class SilverDTO {
    private int id;
    private String name;
    private double point;
    private double bonus;

    public SilverDTO() {}

    public SilverDTO(int id, String name, double point) {
        this.id = id;
        this.name = name;
        this.point = point;
        bonus = point * 0.03;
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

    public double getBonus() {
        return bonus;
    }
}
