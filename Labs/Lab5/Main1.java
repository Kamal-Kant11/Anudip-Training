

public class Main1 {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Square square = new Square(4);  
        Rectangle rectangle = new Rectangle(6, 3);

        circle.getArea();
        square.getArea();   
        rectangle.getArea();
    }
}

class Shape {
    void getArea() {
        System.out.println("Area of Shape");
    }
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

class Square extends Shape {
    int side;
    Square(int side) {
        this.side = side;
    }

    void getArea() {
        int area = side * side;
        System.out.println("Area of Square: " + area);
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
