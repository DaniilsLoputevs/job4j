package daniils.api;

import org.junit.Test;

import java.util.List;
import java.util.function.Function;

public class CollectorsHelperTest {

    @Test
    public void runSimple() {
        var list = List.of(new Data("1", "aaa"),
                new Data("2", "bbb"),
                new Data("3", "ccc"),
                new Data("4", "ddd"),
                new Data("5", "eee")
        );
        var map = CollectorsHelper.toMap(list, data -> data.id);
        System.out.println(map);
    }
    @Test
    public void runHard() {
        var list = List.of(new Data("1", "aaa"),
                new Data("2", "bbb"),
                new Data("3", "ccc"),
                new Data("4", "ddd"),
                new Data("5", "eee")
        );
        int i = 0;
        var function = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer counter) {
                return counter++;
            }
        };
        var map = CollectorsHelper.toMap(list, data -> function.apply(i), data -> data.name);
        System.out.println(map);
    }
    static class Data {
        String id;
        String name;

        public Data(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Data{"
                    + "id='" + id + '\''
                    + ", name='" + name + '\''
                    + '}';
        }
    }

}