package ru.job4j.archive;

import ru.job4j.helpers.StringHelper;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * -d - directory - которую мы хотим архивировать
 * -e - exclude - исключить файлы *.xml
 * -o - output - во что мы архивируем.
 *
 *  -d c:\project\job4j\ -e *.java -o project.zip
 *  -d
 *  c:\project\job4j\
 *  -e
 *  *.java
 *  -o
 *  project.zip
 *
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
        var arg = args[index++];


        while (continueValidate) {
            if ("-d".matches(arg)) {
                arg = args[index++];
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

            arg = args[index++];
            if ("-e".matches(arg)) {
                arg = args[index++];
                String[] tmpExts = arg.split(",");
                this.exts = new HashSet<>();
                this.exts.addAll(StringHelper.linesToList(tmpExts));
            } else {
                System.out.println("args doesn't contains -e \"Extends for ignore\"");
                break;
            }

            arg = args[index++];
            if ("-o".matches(arg)) {
                arg = args[index++];
                this.targetPath =  arg;
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
