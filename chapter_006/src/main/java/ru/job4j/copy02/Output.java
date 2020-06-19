package ru.job4j.copy02;

import daniils.IOHelper;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Output {
    public List<String> logSave(List<Path> list, String getTargetByArgsMap) {
        List<String> result = list.stream()
                .map(path -> path.getFileName() + " --- " + path.toFile().getPath())
                .collect(Collectors.toList());
        IOHelper.writeListToFile(getTargetByArgsMap, result, System.lineSeparator());
        return result;
    }

}
