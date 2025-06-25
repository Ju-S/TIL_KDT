package main;

import pack1.Animal;
import pack1.Car;
import pack2.ParkingTower;
import pack2.Zoo;

public class Main {
    public static void main(String[] args) {
//        ParkingTower tower = new ParkingTower();
//
//        Car car1 = new Car("기아", "모닝");
//        Car car2 = new Car("현대", "소나타");
//        Car car3 = new Car("BMW", "M3");
//
//        tower.parking(car1);
//        tower.parking(car2);
//        tower.parking(car3);
//
//        //----------------------주차완료
//
//        System.out.println(tower.findCarByIndex(2).getBrand() + " : " + tower.findCarByIndex(2).getModel());

        Zoo zoo = new Zoo();

        Animal animal1 = new Animal("얼룩말", 5);
        Animal animal2 = new Animal("고양이", 7);
        Animal animal3 = new Animal("강아지", 3);

        zoo.addAnimal(animal1);
        zoo.addAnimal(animal2);
        zoo.addAnimal(animal3);

        Animal animal = zoo.findAnimalByIndex(2);

        System.out.println(animal.getName() + " : " + animal.getAge());
    }
}
