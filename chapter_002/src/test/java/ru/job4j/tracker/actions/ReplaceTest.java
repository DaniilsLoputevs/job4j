package ru.job4j.tracker.actions;

import org.hamcrest.Matchers;
import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.Tracker;

import static org.junit.Assert.assertThat;

public class ReplaceTest {

    @Test
    public void replaceActionClassTest() {
        // Подгатовка
        Tracker tracker = new Tracker();
        Item example = new Item("example");
        Item test = new Item("test");
        // Основной блок
        tracker.add(example);
        // Обновляем заявку в трекере.
        new ru.job4j.tracker.actions.Replace(1, "").execute(new StubInput(new String[] {example.getId(), test.getName()}), tracker);
        // Проверяем, что заявка с таким id имеет новое имя "test"
        assertThat(tracker.findById(example.getId()).getName(), Matchers.is("test"));
    }
}
