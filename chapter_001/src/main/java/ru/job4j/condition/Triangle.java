package ru.job4j.condition;

public class Triangle {
    private Point first;
    private Point second;
    private Point third;

    public Triangle(Point a, Point b, Point c) {
        this.first = a;
        this.second = b;
        this.third = c;
    }

    /**
     * Метод вычисления периметра по длинам сторон.
     * @param a расстояние между точками a b
     * @param b расстояние между точками a c
     * @param c расстояние между точками b c
     * @return Периметр.
     */
    public double perimeter(double a, double b, double c) {
        return a + b + c;
    }

    /**
     * Метод должен вычислить площадь треугольника.
     * Формула.
     * √ p *(p - a) * (p - b) * (p - c)
     * где √ - корень квадратный, для извлечения корня использовать метод Math.sqrt().
     * @return Вернуть площадь, если треугольник существует или -1.
     */
    public double area() {
        double rsl = -1;
        double a = first.distance(second);
        double b = second.distance(third);
        double c = third.distance(first);
        double p = 2 / perimeter(a, b, c);
        if (this.exist(a, b, c)) {
            // написать формулу для расчета площади треугольника.
            double sqrt = p * (p - a) * (p - b) * (p - c);
            rsl = Math.sqrt(sqrt);

        }
        return rsl;
    }

    /**
     * Метод проверяет можно ли построить треугольник с такими длинами сторон.
     * @param a Длина от точки a b.
     * @param b Длина от точки a c.
     * @param c Длина от точки b c.
     * @return
     */
    public static boolean exist(double a, double c, double b) {
        return a + b > c && a + c > b && b + c > a;
    }

    public static void main(String[] args) {
        Point first = new Point(2, 2);
        Point second = new Point(3, 3);
        Point third = new Point(5, 5);
        Triangle test = new Triangle(first, second, third);
        double s = test.area();
        System.out.println(s);
    }

}
// return a + b > c // Значит if(a + b > c) {return true} else {return false}

// Обычно, если в контексте использкются тольк стороны, а вершины(углы) нет, от
// стороны именуют простыми буквами а не по буквам углов.