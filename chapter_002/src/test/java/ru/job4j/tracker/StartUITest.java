package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.actions.StubAction;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {

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
        new StartUI().init(input, new Tracker(), arrayList);
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void showMenu() {
        // Проверяет, что в списке ВСЕ действия что должы там быть
//        UserAction[] actions = {
//                new ExitOfProgramm(),
//                new Create(),
//                new Replace(),
//                new Delete(),
//                new FindAll(),
//                new FindByName(),
//                new FindById()
//        };
//        new StartUI().showMenu(actions);
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

        new StartUI().init(input, new Tracker(), arrayList);
        assertThat(action.isCall(), is(true));
    }


}