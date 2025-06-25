package classes;

import java.util.Date;

public class RentalDTO {
    private int id;
    private int carId;

    private String name;

    private Date rentalStartDate;
    private Date rentalEndDate;

    public RentalDTO() {}
    public RentalDTO(int carId, String name, Date rentalStartDate) {
        this.carId = carId;
        this.name = name;

        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = calcRentalEndDate(rentalStartDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(Date rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public Date getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(Date rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public Date calcRentalEndDate(Date rentalStartDate) {
        return new Date(rentalStartDate.getTime() + (86400000 * 3));
    }
}
