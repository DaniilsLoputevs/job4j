package ru.job4j.patterns.factory;

public interface ShapeAbstFactory {
    TriangleShape createTriangle();

    RectangleShape createRectangle();

    EmptyShape createEmpty();
}
