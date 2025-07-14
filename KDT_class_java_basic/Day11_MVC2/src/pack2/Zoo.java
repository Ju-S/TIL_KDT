package pack2;

import pack1.Animal;

public class Zoo {
    private Animal[] animals = new Animal[10];
    private int index = 0;

    public void addAnimal(Animal animal) {
        animals[index] = animal;
        index++;
    }

    public Animal findAnimalByIndex(int index) {
        return animals[index];
    }
}
