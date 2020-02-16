package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> fileLines;
        try (BufferedReader load = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))
        ) {
            fileLines = load.lines().collect(Collectors.toCollection(LinkedList::new));
            var workTime = true;
            for (String line: fileLines) {
                if (workTime && line.startsWith("4") || line.startsWith("5")) {
                    writer.write(line.substring(line.indexOf(" ") + 1));
                    writer.write(" - ");
                    workTime = false;
                } else if (!workTime && line.startsWith("2") || line.startsWith("3")) {
                    writer.write(line.substring(line.indexOf(" ") + 1));
                    writer.write("\n");
                    workTime = true;
                }
            }
        } catch (IOException e) {
            System.out.println("IOException - something wrong!");
            e.printStackTrace();
        }
    }

    public boolean compareInfoFromFileWithList(String sourcePath, List list) {
        var result = false;

        List<String> fileLines = new LinkedList<>();
        try (BufferedReader load = new BufferedReader(new FileReader(sourcePath))) {
            fileLines = load.lines().collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            System.out.println("IOException - something wrong!");
            e.printStackTrace();
        }
        result = fileLines.containsAll(list);
        return result;
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}