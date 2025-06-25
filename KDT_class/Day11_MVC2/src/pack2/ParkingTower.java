package pack2;

import pack1.Car;

public class ParkingTower {
    private Car[] parkingZone = new Car[10];
    private int index = 0;

    public void parking(Car car){
        parkingZone[index] = car;
        index++;
    }

    public Car findCarByIndex(int index) {
        return parkingZone[index];
    }
}
