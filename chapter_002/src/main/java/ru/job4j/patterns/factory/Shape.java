package ru.job4j.patterns.factory;

public class Shape {
}

class ShapeFactory {
    public static Shape create(String name) {
        Shape shape = new EmptyShape();
        if (name.equals("triangle")) {
            shape = new TriangleShape();
        } else if (name.equals("rectangle")) {
            shape = new RectangleShape();
        }
        return shape;
    }

//    public class ShapeFactory {
//        private final ShapeAbstFactory factory;
//
//        public ShapeFactory(ShapeAbstFactory factory) {
//            this.factory = factory;
//        }
//
//        public Shape create(String name) {
//            Shape shape = factory.createEmpty();
//            if ("triangle".equals(name)) {
//                shape = factory.createTriangle();
//            } else if ("rectangle".equals(name)) {
//                shape = factory.createRectangle();
//            }
//            return shape;
//        }
//    }

    public static void main(String[] args) {
        Shape shape = ShapeFactory.create("cycle");

        Shape[] list = {
                ShapeFactory.create("cycle"),
                ShapeFactory.create("triangle")
        };
    }
}

//  ShapeFactory.create("cycle"); - это и есть фабричный метод, который создает новые экземпляры.