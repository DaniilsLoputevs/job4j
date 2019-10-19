package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    @Test // add Item || create Item
    public void whenAddItem() {
        String[] answers = {"Fix PC"};
        Input input = new StubInput(answers);

        Tracker tracker = new Tracker();

//        StartUI.createItem(input, tracker);

        Item created = tracker.findAll()[0];
        Item expected = new Item("Fix PC");
        assertThat(created.getName(), is(expected.getName()));
    }

    @Test // replace Item || edit Item
    public void whenReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {
                item.getId(), // id сохраненной заявки в объект tracker.
                "replaced item"
        };
//        StartUI.replaceItem(new StubInput(answers), tracker);
        Item replaced = tracker.findById(item.getId());
        assertThat(replaced.getName(), is("replaced item"));
    }

    @Test // delete Item
    public void deleteV2() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("item one");
        Item item2 = new Item("item two");
        Item item3 = new Item("item last");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        String[] answers = {
                item3.getId(), // id сохраненной заявки в объект tracker.
        };
//        StartUI.deleteItem(new StubInput(answers), tracker);
        Item[] test = new Item[] {
                item1, item2
        };
        assertThat(tracker.findAll(), is(test));
    }
    @Test // 0. Exit
    public void whenExit() {
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction();
        new StartUI().init(input, new Tracker(), new UserAction[] { action });
        assertThat(action.isCall(), is(true));
    }
    @Test // init
    public void init() {
        StubInput input = new StubInput(
                new String[] {"0"}
        );
//        Tracker tracker = new Tracker();
        StubAction action = new StubAction();
        new StartUI().init(input, new Tracker(), new UserAction[] { action });
        assertThat(action.isCall(), is(true));

    }
}