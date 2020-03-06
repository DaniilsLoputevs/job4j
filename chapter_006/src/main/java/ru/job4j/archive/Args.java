package ru.job4j.archive;

import ru.job4j.helpers.StringHelper;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/** Задача: написать Класс Args симулируя его настоящие поведение.
 ** Нужно реализовать конструктор класса.
 *
 * Ожидаемый порядок ключей:
 * -d - directory - которую мы хотим архивировать.
 * -e - exclude - исключить файлы *.xml
 * -o - output - во что мы архивируем.
 *
 * Предположительные параметры:
 *  -d c:\project\job4j\ -e *.java -o project.zip
 * Аргументы внутри:
 *  [0] -d                  - Ключ Директория.
 *  [1] c:\project\job4j\   - Путь Директория.
 *  [2] -e                  - Ключ Игнорируем расширения.
 *  [3] *.java              - Названия расширения.
 *  [4] -o                  - Ключ Путь куда записать результат.
 *  [5] project.zip         - Путь Куда куда записать результат.
 *
 * Устройство конструктора:
 ** Поочерёдная проверка всех Аргументов.
 *- Проверки:
 * Валидация аргумента:  Кол-во, аргументов. <= 5
 * Валидация аргумента:  Корневая директория.
 * Валидация аргумента:  Игнорируемые расширения.
 * Валидация аргумента:  Путь создания архива.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 03.03.20.
 * Last upd:  05.03.20.
 * Last JavaDoc upd:  06.03.20.
 */
public class Args {
    private String dirPath;
    private Set<String> exts;
    private String targetPath;


    public Args(String[] args) {
        boolean continueValidate = true;
        if (args.length <= 5) {
            System.out.println("args doesn't contains enough \"Arguments\"");
            continueValidate = false;
        }

        int index = 0;
        var arg = args[index++]; // arg = "-d"


        while (continueValidate) {
            if ("-d".matches(arg)) {
                arg = args[index++]; // arg = "c:\project\job4j\"
                var fileForCheckPath = new File(arg);
                if (fileForCheckPath.exists() && fileForCheckPath.isDirectory()) {
                    this.dirPath = arg;
                } else {
                    System.out.println("file on this path isn't exit or isn't a directory");
                    break;
                }
            } else {
                System.out.println("args doesn't contains -d \"Directory for archive\"");
                break;
            }

            arg = args[index++]; // arg = "-e"
            if ("-e".matches(arg)) {
                arg = args[index++]; // arg = "*.java"
                this.exts = new HashSet<>();
                this.exts.addAll(StringHelper.linesToList(arg.split(",")));
            } else {
                System.out.println("args doesn't contains -e \"Extends for ignore\"");
                break;
            }

            arg = args[index++]; // arg = "-o"
            if ("-o".matches(arg)) {
                arg = args[index++]; // arg = "project.zip"
                if (!new File(arg).exists()) {
                    this.targetPath = arg;
                } else {
                    System.out.println("args contains wrong \" Output Path\" - this file already exist");
                }
            } else {
                System.out.println("args doesn't contains -o \" Directory for Output\"");
                break;
            }
            continueValidate = false;
        }
    }


    public String directory() {
        return dirPath;
    }
    public Set<String> excule() {
        return exts;
    }
    public String output() {
        return targetPath;
    }
}