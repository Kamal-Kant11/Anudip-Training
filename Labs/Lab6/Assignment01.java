package Labs.Lab6;

public class Assignment01 {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);

        circle.getArea();
        rectangle.getArea();
    }
}

abstract class Shape {
    abstract void getArea();
}

class Circle extends Shape {
    int radius;
    Circle(int radius) {
        this.radius = radius;
    }

    void getArea() {
        double area = 3.14 * radius * radius;
        System.out.println("Area of Circle: " + area);
    }
}

class Rectangle extends Shape {
    int length, width;
    Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    void getArea() {
        int area = length * width;
        System.out.println("Area of Rectangle: " + area);
    }
}
