package ru.job4j.exam;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * ВАЖНО!!!
 * Если использовать Map.of(...) - то нарушается порядок обработки в методе, что приводит к появления магии!
 * * это связанно со спецификой метода Map.of(...).
 * * + желательно использовать LinkedHashMap.
 */
public class PostTest {
    private final Post post = new Post();

    @Test
    public void taskTest() {
        var init = new LinkedHashMap<String, Set<String>>();
        init.put("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        init.put("user2", Set.of("foo@gmail.com", "ups@pisem.net"));
        init.put("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));
        init.put("user4", Set.of("ups@pisem.net", "aaa@bbb.ru"));
        init.put("user5", Set.of("xyz@pisem.net"));

        var rsl = post.sotUsers(init);
        var expected = Map.of(
                "user1", Set.of("aaa@bbb.ru", "ups@pisem.net", "xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"),
                "user5", Set.of("xyz@pisem.net", "vasya@pupkin.com")
        );

        assertTrue(expected.values().containsAll(rsl.values()));

        // for beautiful print.
//        System.out.println("### START ###");
//        System.out.println("### exp ###");
//        post.print(expected);
//        System.out.println("### rsl ###");
//        post.print(rsl);
    }


    @Test
    public void extraTest() {
        var init = new LinkedHashMap<String, Set<String>>();
        init.put("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        init.put("user2", Set.of("foo@gmail.com", "ups@pisem.net"));
        init.put("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));
        init.put("user4", Set.of("ups@pisem.net", "aaa@bbb.ru"));
        init.put("user5", Set.of("kappa@gmail.notCom", "cs16@old.fag"));
        init.put("user6", Set.of("cs16@old.fag", "kappa@gmail.notCom"));
        init.put("user7", Set.of("Pert_III@carj.rus.velikij", "kappa@gmail.notCom"));
        init.put("user8", Set.of("cs16@old.fag", "Einstein@mars.net"));
        init.put("user9", Set.of("Knjaz_Galicin@mars.net"));
        init.put("user10", Set.of("LastOfUs2@gargabe.lol", "Knjaz_Galicin@mars.net"));

        var rsl = post.sotUsers(init);
        var expected = new LinkedHashMap<String, Set<String>>();
        expected.put("user1", Set.of("aaa@bbb.ru", "ups@pisem.net", "xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        expected.put("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));
        expected.put("user5", Set.of("Pert_III@carj.rus.velikij", "cs16@old.fag", "Einstein@mars.net", "kappa@gmail.notCom"));
        expected.put("user9", Set.of("LastOfUs2@gargabe.lol", "Knjaz_Galicin@mars.net"));

        assertTrue(expected.values().containsAll(rsl.values()));

        // for beautiful print.
//        System.out.println("### START ###");
//        System.out.println("### exp ###");
//        post.print(expected);
//        System.out.println("### rsl ###");
//        post.print(rsl);
    }

}