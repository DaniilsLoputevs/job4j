package ru.job4j.exam.links;

import java.util.*;

public class LinksNew {
    /**
     * String      - key    - groupName.
     * Set<String> - value  - groupElements. (incremented counter)
     */
    HashMap<String, Set<String>> result = new HashMap<>();
    /**
     * String - key    - sub-string.
     * String - value  - group name. (incremented counter)
     */
    HashMap<String, String> keyGroupContains = new HashMap<>();


    /**
     * Смотреть Исходник!
     * <p>
     * Для всех строк:
     * Сплитим все сроки по RegExp ";". (получаем под-строки).
     * Для всех под-строк:
     * Проверяем наличие в Map<groupKey, groupName>.
     * Если да >>>
     * берём имя группы по значения клююча.
     * указываем что есть такая под-строка и Map. (var check == true).
     * выходим из цикла.
     * <p>
     * Если (check == true) >>>
     * - кладём все под-строки вместе с их groupName -> Map в коротой лежат все groupKey {@code Map<groupKey, groupName>}.
     * т.к. под-строка == groupKey.
     * - вытаскиваем все элементы по значению groupName из {@code Map<groupName, Set<groupElements>>}.
     * - дополняем эти значения новыми и обновляем список.
     * Если (check == false) >>>
     * - кладём все под-строки в {@code Map<groupName, Set<groupElements>>} как элементы НОВОЙ группы.
     * - кладём все под-строки вместе с их groupName -> Map в коротой лежат все groupKey {@code Map<groupKey, groupName>}.
     * т.к. под-строка == groupKey.
     * - обновляем {@code Map<groupName, Set<groupElements>>} кидаем все под-строки как Set<groupElements>, с их GroupName.
     * - увеличиваем счётчик на +1 для groupName.
     *
     * @param inputStrings All input Strings.
     * @return {@code Map<groupName, Set<groupElements>>} - Groups and group's elements.
     */
    public Map<String, Set<String>> grouping(String[] inputStrings) {
        int groupNum = 1;

        for (var line : inputStrings) {
            List<String> subStrings = Arrays.asList(line.split(";"));
            var groupName = "group" + groupNum;
            boolean check = false;

            for (var keyForGroup : subStrings) {
                if (keyGroupContains.containsKey(keyForGroup)) {
                    groupName = keyGroupContains.get(keyForGroup);
                    check = true;
                }
            }
            var finalGroupName = groupName;
            if (check) {
                subStrings.forEach(e -> keyGroupContains.put(e, finalGroupName));
                Set<String> temp = new HashSet<>(result.get(finalGroupName));
                temp.addAll(subStrings);
                result.put(finalGroupName, temp);
            } else {
                subStrings.forEach(e -> keyGroupContains.put(e, finalGroupName));
                result.put(groupName, new HashSet<>(subStrings));
                groupNum++;
            }
        }
        return result;
    }

}