//package ru.job4j.exam;
//
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
///** Задание после Экзамена - Решено 15.02.2020
// *
// * A1;B1;C1
// * A2;B2;C2
// * ...
// *
// * Найти множество уникальных строчек и разбить его на непересекающиеся группы по следующему критерию:
// * Если две строчки имеют совпадения непустых значений в одной или более колонках, они принадлежат одной группе.
// * Например, строчки
// * 111;123;222
// * 200;123;100
// * 300;;100
// *
// * все принадлежат одной группе, так как первые две строчки имеют одинаковое значение 123 во второй колонке,
// * а две последние одинаковое значение 100 в третьей колонке
// * в) Вывести полученные группы в файл в следующем формате:
// * Группа 1
// * строчка1
// * строчка2
// * строчка3
// * ...
// * Группа 2
// * строчка1
// * строчка2
// * строчка3
// *
// * В начале вывода указать получившиееся число групп с более чем одним элементом.
// * Сверху расположить группы с наибольшим числом элементов.
// */
//public class LinksThree {
//
//    /** Груперует строки в Группы по совпадениям в столбике строк
//     * @param inputStrings - Входнный массив строк.
//     * @return Лист с группами строк.
//     */
//    public List<Group> grouping(String[] inputStrings) {
//        /** Собирать 2 map и и пороходить contains по ключами и значениям(отдельно).
//         * map<String, String[]> stringGroup --- string - keys
//         * map<String, LinkedList<String>> resultGroup --- keys - LinkedList<String>
//         *
//         */
//        // Собраная Map<String, String[]> - Not Tested
//        // 111;222;333 - 111, 222, 333
//        // 444;555;666 - 444, 555, 666
//        Map<String, String[]> stringGroup = List.of(inputStrings).stream()
//                .filter(string -> string.length() > 0)
//                .flatMap(string -> Stream.of(Map.of(string, string.split(";"))))
//                .findFirst().get();
//
//
//
//
////        List<Group> resultGroup = new LinkedList<>(List.of(new Group()));
//        LinkedList<HashMap> resultGroup = new LinkedList<>(List.of(new HashMap()));
//
//        // Собирать List<Map>
//        // Собирать Map<String, LinkedList<String>>
//        // 111 - 111;222;333
//        // 444 - 444;555;666
//        //
//        var tempList = Stream.of(stringGroup)
//                .flatMap(stringMap -> {
//                    for (HashMap map : resultGroup) {
////                        for ()
////                        if (map.containsKey())
//
//                    }
//
//                    return null;
//                });
//
//
//
////        var firstGroup = new Group();
////
////        List.of(inputStrings).stream()
////                .filter(string -> string.length() > 0)
////                .flatMap(string -> {
////                            var result = new LinkedList<Group>();
////                            result.add(new Group());
//////                    result.add(s.split(";"));
////
////
////
////                            return null;
////                        }
////                );
//
//        var result = new LinkedList<Group>();
//        result.add(new Group());
//
//        for (String string : inputStrings) {
//            String[] stringKeys = string.split(";");
//
//            if (stringKeys.length > 0) {
//                var findPlace = false;
//
//                for (Group group : result) {
//                    if (group.add(string, stringKeys)) {
//                        findPlace = true;
//                        break;
//                    }
//                }
//                if (!findPlace) {
//                    var temp = new Group();
//                    temp.add(string, stringKeys);
//                    result.add(temp);
//                }
//            }
//        }
//        result.sort(new GroupComparator());
//        return result;
//    }
//
//
//    /** Модель группы для объяденения строк.
//     * Модель расчитана на 3 столбика в строчке.
//     * String[] groupKeys - Ключи которые определяються совпадениями по столбикам и далее не перезаписываются.
//     * Индекс и этом масиввиве значит номер столбика. Одно совпадение по столбику, ключ записан и не меняеться.
//     */
//    static class Group {
//        private LinkedList<String> container = new LinkedList<>();
//        private String[] groupKeys = new String[] {"???", "???", "???"};
////        private HashSet<String> groupKeys = new HashSet<>(Set.of("???", "???", "???"));
//
//        /** Добавить елемент в группу.
//         * @param string - Строчка для добавления.
//         * @param keys - Ключи из строчки.
//         * @return true - Добавили, false - нет, это пойдёт в другую группу.
//         */
//        public boolean add(String string, String[] keys) {
//            var result = false;
//            if (container.size() < 1) {
//                container.add(string);
//                result = true;
//            } else if (canAddByColumn(0, keys[0])
//                    || canAddByColumn(1, keys[1])
//                    || canAddByColumn(2, keys[2])) {
//                container.add(string);
//                result = true;
//            }
//            return result;
//        }
//
//        /** Проверка по столбику, пожем ли: добавить строчку.
//         * Сравнивает ключи в столбика, если нет ключа, то проходим по всей группе.
//         * @param column - Индекс столбика.
//         * @param potentialKey - Возможно новый ключ.
//         * @return Можем добавить или нет.
//         */
//        private boolean canAddByColumn(int column, String potentialKey) {
//            var keyEmpty = this.groupKeys[column].equals("???");
//            return (!keyEmpty)
//                    ? this.groupKeys[column].equals(potentialKey)
//                    : findKeyInColumnAndSetIfEquals(column, potentialKey);
//        }
//
//        /** Сравнивает ключ по во группе в пределе столбика.
//         * Если нашёл совпадение, то:  groupKeys[column] = potentialKey;
//         * @param column  - Мндекс столбика.
//         * @param potentialKey - Возможно новый ключ.
//         * @return Есть совпадающий ключ или нет.
//         */
//        private boolean findKeyInColumnAndSetIfEquals(int column, String potentialKey) {
//            var result = false;
//            for (String string : container) {
//                if (string != null) {
//                    var temp = string.split(";")[column];
//                    if (temp.equals(potentialKey)) {
//                        groupKeys[column] = potentialKey;
//                        result = true;
//                        break;
//                    }
//                }
//            }
//            return result;
//        }
//
//        public int getSize() {
//            return container.size();
//        }
//
//
//        /** ---------- Constructors ---------- */
//        public Group() { }
//
//        public Group(LinkedList<String> container, String[] groupKeys) {
//            this.container = container;
//            this.groupKeys = groupKeys;
//        }
//
//        /** ---------- equals && hashCode && toString ---------- */
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Group group = (Group) o;
//            return Objects.equals(container, group.container) &&
//                    Arrays.equals(groupKeys, group.groupKeys);
//        }
//
//        @Override
//        public int hashCode() {
//            int result = Objects.hash(container);
//            result = 31 * result + Arrays.hashCode(groupKeys);
//            return result;
//        }
//
//        @Override
//        public String toString() {
//            return "Group{" +
//                    "container=" + container +
//                    ", groupKeys=" + Arrays.toString(groupKeys) +
//                    '}';
//        }
//    }
//
//    private class GroupComparator implements Comparator<Group> {
//
//        @Override
//        public int compare(Group o1, Group o2) {
//            var result = (o1.getSize() > o2.getSize()) ? -1 : 0;
//            result = (o1.getSize() < o2.getSize()) ? 1 : 0;
//            return result;
//        }
//    }
//}