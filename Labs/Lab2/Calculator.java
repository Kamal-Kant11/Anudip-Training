package Labs.Lab2;
public class Calculator {
    int add(int a, int b) {
        int sum = a + b;
        return sum;
    }

    int add(int a, int b, int c) {
        int sum = a + b + c;
        return sum;
    }

    double add(double a, double b) {
        double sum = a + b;
        return sum;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        int sum1 = calc.add(20, 30);
        int sum2 = calc.add(20, 30, 50);
        double sum3 = calc.add(25.40, 24.69);

        System.out.println(sum1);
        System.out.println(sum2);
        System.out.println(sum3);
    }
}