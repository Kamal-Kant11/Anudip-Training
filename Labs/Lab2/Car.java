package Labs.Lab2;
public class Car {
    String make;
    String model;
    short year;
    int price;

    Car(String make, String model, short year, int price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    void displayCar() {
        System.out.println("Make : " + this.make);
        System.out.println("Model : " + this.model);
        System.out.println("Year : " + this.year);
        System.out.println("Price : " + this.price);
    }

    public static void main(String[] args) {
        Car obj = new Car("Suzuki", "Dzire", (short)21, 800000);
        System.out.println("Car Details : ");
        obj.displayCar();
    }
}

