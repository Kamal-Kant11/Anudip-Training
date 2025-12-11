public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        Motorcycle bike = new Motorcycle();
        Garage garage = new Garage();
        garage.servicevehicle(car);
        garage.servicevehicle(bike);
    }
}

class Vehicle {
    void start() {
        System.out.println("Vehicle Started");
    }
}

class Car extends Vehicle {
    void start() {
        System.out.println("Car Started");
    }
}

class Motorcycle extends Vehicle {
    void start() {
        System.out.println("Motorcycle Started");
    }
}

class Garage {
    void servicevehicle(Vehicle vehicle) {
        vehicle.start();
        System.out.println("Vehicle Serviced");
    }
}