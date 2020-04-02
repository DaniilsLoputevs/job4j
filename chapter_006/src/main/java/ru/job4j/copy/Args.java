package ru.job4j.copy;

import java.io.File;

/** Задача: написать Класс Args симулируя его настоящие поведение.
 ** Нужно реализовать конструктор класса.
 *
 * Ожидаемый порядок ключей:
 * -d - директория в которая начинать поиск.
 * -n - имя файл, маска, либо регулярное выражение.
 * -m - искать по макс, либо -f - полное совпадение имени. -r регулярное выражение.
 * -o - результат записать в файл.
 *
 * Предположительные параметры:
 *  -d c:/ -n *.txt -m -o log.txt
 * Аргументы внутри:
 *  [0] -d      - Ключ Директория.
 *  [1] c:/     - Путь Директория.
 *  [2] -n      - Ключ Имя файла.
 *  [3] -m      - Ключ как понимать имя файла(см. ниже).
 *  [4] *.txt   - Имя файла || шаблон поиска.
 *  [5] -o      - Ключ Путь куда записать результат.
 *  [6] log.txt - Путь Куда куда записать результат.
 *
 * Устройство конструктора:
 ** Поочерёдная проверка всех Аргументов.
 *- Проверки:
 * Валидация аргумента:  Кол-во, аргументов. < 7
 * Валидация аргумента:  Корневая директория.
 * Валидация аргумента:  Имя || шаблон поиска.
 * Валидация аргумента:  Путь записи результата.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 06.03.20.
 * Last upd:  06.03.20.
 * Last JavaDoc upd:  06.03.20.
 */
public class Args {
    private String rootPath;
    private String findParamValue;
    private String targetPath;
    private String findParamKey;

    private int argIndex = 0;
    private String arg;

    public Args(String[] args) {
        while (argsCountCheck(args)) {
            if (!rootCheck(args)) {
                break;
            }
            if (!findByCheck(args)) {
                break;
            }
            if (!outputCheck(args)) {
                break;
            }
            // Этот {@code break; } - тут ОБЯЗАТЕЛЕН!!!
            break;
        }
    }

    private void nextArg(String[] args) {
        this.arg = args[this.argIndex++];
    }

    private boolean argsCountCheck(String[] args) {
        var result = true;
        if (args.length < 7) {
            System.out.println("args doesn't contains enough \"Arguments\"");
            result = false;
        }
        return result;
    }
    private boolean rootCheck(String[] args) {
        var result = false;
        nextArg(args); // arg = "-d" [0]
        if ("-d".matches(arg)) {
            nextArg(args); // arg = "c:/" [1]
            var fileForCheckPath = new File(arg);
            if (fileForCheckPath.exists() && fileForCheckPath.isDirectory()) {
                this.rootPath = arg;
                result = true;
            } else {
                System.out.println("file on this path isn't exit or isn't a directory");
            }
        } else {
            System.out.println("args doesn't contains -d \"Directory for archive\"");
        }
        return result;
    }
    private boolean findByCheck(String[] args) {
        var result = false;
        nextArg(args); // arg = "-n" [2]
        if ("-n".matches(arg)) {
            nextArg(args); // arg = "-m" [3]
            if ("-m".matches(arg) || "-f".matches(arg) || "-r".matches(arg)) {
                this.findParamKey = this.arg;
                nextArg(args); // arg = "*.txt" [4]
                this.findParamValue = this.arg;
                result = true;
            } else {
                System.out.println("args doesn't contains -m || -f || -r \" Key How To Search\"");
            }
        } else {
            System.out.println("args doesn't contains -n \" Name of Search File\"");
        }
        return result;
    }
    private boolean outputCheck(String[] args) {
        var result = false;
        nextArg(args); // arg = "-o" [5]
        if ("-o".matches(arg)) {
            nextArg(args); // arg = "log.txt" [6]
            if (new File(arg).isFile()) {
                this.targetPath = arg;
                result = true;
            } else {
                System.out.println("args contains wrong \" Output Path\" - It isn't a File");
                result = false;
            }
        } else {
            System.out.println("args doesn't contains -o \" Directory for Output\"");
        }
        return result;
    }

    public String rootPath() {
        return this.rootPath;
    }
    public String findParamValue() {
        return this.findParamValue;
    }
    public String target() {
        return this.targetPath;
    }
    public String findParamKey() {
        return this.findParamKey;
    }
}