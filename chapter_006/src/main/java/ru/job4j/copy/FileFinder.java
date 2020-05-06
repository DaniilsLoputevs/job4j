package ru.job4j.copy;

import daniils.IOHelper;
import ru.job4j.search.Search;

import java.io.File;

/** Задача: Поиск txt файла в Файловой Системе по "полному имени" || "Маске" || "регулярное выражение",
 *  и записать его содержимое по Пути после ключа: -o
 *
 *  -d c:/ -n *.txt -m -o log.txt
 *
 * -d - директория в которая начинать поиск.
 * -n - имя файл, маска, либо регулярное выражение.
 * -m - искать по маске; -f - полное совпадение имени; -r регулярное выражение.
 * -o - результат записать в файл.
 *
 * +++
 *+ В программе должна быть валидация ключей и подсказка.
 *+ В тестах строка аргументов иная.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 06.03.20.
 * Last upd:  12.03.20.
 * Last JavaDoc upd:  12.03.20.
 */
public class FileFinder {

    /** Поиск файла используя ключи{@code findKey} и скопировать его содержимое в файл{@code targetPath}
     *
     *** Из-за, лямбды можно задавать свои проверки на новые ключи.
     *** Сменой .contains() и .matches() - удалось добиться нужного результата, без написания доп. классов.
     *
     * @param rootDirPath - Корень где начинать поиск.
     * @param findParam - искать по этому совпадению. (шаблон для поиска в форме строки).
     * @param targetPath - путь куда скопировать содержимое.
     * @param findKey - используемый ключ. "-n", "-m", "-f", "-r"
     */
    public void copyToPath(String rootDirPath, String findParam, String targetPath, String findKey) {
        File sourceFile = null;

        if ("-n".equals(findKey)) {
            sourceFile = new Search().findByLambda(rootDirPath,
                    fileName -> fileName.contains(findParam));
        } else if ("-m".equals(findKey)) {
            sourceFile = new Search().findByLambda(rootDirPath,
                    fileName -> fileName.matches(findParam));
        } else if ("-f".equals(findKey)) {
            sourceFile = new Search().findByLambda(rootDirPath,
                    fileName -> fileName.contains(findParam));
        } else if ("-r".equals(findKey)) {
            sourceFile = new Search().findByLambda(rootDirPath,
                    fileName -> fileName.matches(findParam));
        }

        IOHelper.copyTextToTarget(sourceFile, targetPath, System.lineSeparator());
    }
}