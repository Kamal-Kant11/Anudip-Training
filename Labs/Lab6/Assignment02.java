package Labs.Lab6;

import java.util.Scanner;

public class Assignment02 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Create Animal");
        System.out.println("2. Create Dog");
        System.out.println("3. Create Cat");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            Animal a = new Animal();
            a.makeSound();
        }
        else if (choice == 2) {
            Dog d = new Dog();
            d.makeSound();
        }
        else if (choice == 3) {
            Cat c = new Cat();
            c.makeSound();
        }
        else {
            System.out.println("Invalid choice!");
        }

        sc.close();
    }
}

// Superclass
class Animal {
    void makeSound() {
        System.out.println("The animal makes a sound.");
    }
}

// Subclass 1
class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("The dog barks.");
    }
}

// Subclass 2
class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("The cat meows.");
    }
}
