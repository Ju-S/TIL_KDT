package dto;

public class Topaz  extends Membership{

    public Topaz() {}
    public Topaz(int id, String name, double point) {
        super(id, name, point);
    }

    @Override
    public double getBonus() {
        return this.getPoint() * 0.05;
    }

    @Override
    public int getGrade() {
        return 3;
    }
}
