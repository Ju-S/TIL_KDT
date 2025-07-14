package dto;

public class CafeDTO {
    private int id;
    private String name;
    private int price;
    private String iceable;

    public CafeDTO() {}
    public CafeDTO(int id, String name, int price, String iceable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.iceable = iceable;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIceable() {
        return iceable;
    }

    public void setIceable(String iceable) {
        this.iceable = iceable;
    }
}
