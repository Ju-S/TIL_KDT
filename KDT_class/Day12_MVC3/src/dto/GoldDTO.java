package dto;

public class GoldDTO extends Membership {

    public GoldDTO() {}

    public GoldDTO(int id, String name, double point) {
        super(id, name, point);
    }

    //추상메서드를 구현하는것은 implementing 이라 한다.
    //override - 무효화 시키다
    @Override
    public double getBonus() {
        return this.getPoint() * 0.04;
    }

    @Override
    public int getGrade() {
        return 2;
    }
}
