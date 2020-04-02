package ru.job4j.copy02;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFiles {
    private Pattern patternForSearch;
    private List<Path> tempList;

    public List<Path> start(Args argsMap) {
        patternForSearch = argsMap.getPattern();
        tempList = new ArrayList<>();

        try {
            Files.walkFileTree(
                    new File(argsMap.getDirectory()).toPath(),
                    new LocalFileVisitor()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempList;
    }

    class LocalFileVisitor implements FileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            Matcher matcher = patternForSearch.matcher(file.getFileName().toString());
            if (matcher.find()) {
                tempList.add(file);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            return FileVisitResult.CONTINUE;
        }
    }

}
