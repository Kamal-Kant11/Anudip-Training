public class Main {
    public static void main(String[] args) {
        // Creating Car object
        Car car = new Car("Toyota", "Corolla", 2020, 180);
        // Creating Bike object
        Bike bike = new Bike("Honda", "Shine", 2022, 120);

        // Printing attributes and calling drive()
        System.out.println("Car Details:");
        System.out.println("Make: " + car.make);
        System.out.println("Model: " + car.model);
        System.out.println("Year: " + car.year);
        System.out.println("Max Speed: " + car.maximumSpeed);
        car.drive();

        System.out.println("\nBike Details:");
        System.out.println("Make: " + bike.make);
        System.out.println("Model: " + bike.model);
        System.out.println("Year: " + bike.year);
        System.out.println("Max Speed: " + bike.maximumSpeed);
        bike.drive();
    }
}

class Vehicle {
    String make;
    String model;
    int year;
    int maximumSpeed;

    Vehicle (String make, String model, int year, int maximumSpeed) {
            this.make = make;
            this.model = model;
            this.year = year;
            this.maximumSpeed = maximumSpeed;
        }

    void drive() {
        System.out.println("Vehicle is driving");
    }
}

class Car extends Vehicle {
    Car(String make, String model, int year, int maximumSpeed) {
        super(make, model, year, maximumSpeed);
    }

    void drive() {
        System.out.println(make + " " + model + " Car is driving");
    }
}

class Bike extends Vehicle {
    Bike(String make, String model, int year, int maximumSpeed) {
        super(make, model, year, maximumSpeed);
    }

    void drive() {
        System.out.println(make + " " + model + " Bike is driving");
    }
}
