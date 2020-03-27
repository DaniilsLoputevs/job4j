package ru.job4j.tracker.actions;

import org.hamcrest.Matchers;
import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.TrackerLocal;

import java.util.function.Consumer;

import static org.junit.Assert.assertThat;

public class ReplaceTest {
    private Consumer<String> output = System.out::println;

    @Test
    public void replaceActionClassTest() {
        // Подгатовка
        TrackerLocal trackerLocal = new TrackerLocal();
        Item example = new Item("example");
        Item test = new Item("test");
        // Основной блок
        trackerLocal.add(example);
        // Обновляем заявку в трекере.
        new Replace(1, "").execute(new StubInput(new String[] {example.getId(), test.getName()}), trackerLocal, output);
        // Проверяем, что заявка с таким id имеет новое имя "test"
        assertThat(trackerLocal.findById(example.getId()).getName(), Matchers.is("test"));
    }
}
