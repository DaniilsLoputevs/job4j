package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.actions.*;

import java.util.ArrayList;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    Consumer<String> output = System.out::println;

    @Test
    public void init() {
        // создаём искуственный ввод информации
        StubInput input = new StubInput(
                new String[] {"0", "0"}
        );
        // создаём искуственное действие
        StubAction action = new StubAction(1, "", false);
        ArrayList arrayList = new ArrayList<UserAction>();
        arrayList.add(action);
        // проверяем метод (действие должно вернуть true, т.к. оно было вызвано)
        new StartUI().init(input, new Tracker(), arrayList, output);
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void showMenu() {
        // Проверяет, что в списке ВСЕ действия что должы там быть
        ArrayList<UserAction> actions = new ArrayList();
        actions.add(new ExitOfProgramm(0, "=== Exit ===="));
        actions.add(new Create(1, "=== Create a new Item ===="));
        actions.add(new Replace(2, "=== Replace Item ===="));
        actions.add(new Delete(3, "=== Delete Item ===="));
        actions.add(new FindAll(4, "=== Show all Items ===="));
        actions.add(new FindByName(5, "=== Find Item by Name ===="));
        actions.add(new FindById(6,  "=== Find Item by Id ===="));
        new StartUI().showMenu(actions);
    }



    // Тесты ниже, это тесты Tracker через main (ввод через консоль)


    @Test // 0. Exit
    public void whenExit() {
        StubInput input = new StubInput(
                new String[] {"0", "0"}
        );
        StubAction action = new StubAction(0, "", false);
        ArrayList arrayList = new ArrayList<UserAction>();
        arrayList.add(action);

        new StartUI().init(input, new Tracker(), arrayList, output);
        assertThat(action.isCall(), is(true));
    }


}