package dto;

public class SilverDTO extends Membership{

    public SilverDTO() {}

    public SilverDTO(int id, String name, double point) {
        super(id, name, point);
    }

    @Override
    public double getBonus() {
        return this.getPoint() * 0.03;
    }

    @Override
    public int getGrade() {
        return 1;
    }
}
