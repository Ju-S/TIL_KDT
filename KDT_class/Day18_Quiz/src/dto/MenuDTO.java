package dto;

import java.util.Date;

public class MenuDTO {
    private String name;
    private int price;
    private Date launchingDate;

    public MenuDTO() {}
    public MenuDTO(String name, int price, Date launchingDate) {
        this.name = name;
        this.price = price;
        this.launchingDate = launchingDate;
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

    public Date getLaunchingDate() {
        return launchingDate;
    }

    public void setLaunchingDate(Date launchingDate) {
        this.launchingDate = launchingDate;
    }
}
