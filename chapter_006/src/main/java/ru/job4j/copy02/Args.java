package ru.job4j.copy02;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Args {
    private Pattern pattern;
    private Map<String, String> argsConfig = new HashMap<>();

    public void load(String[] args) {
        String key = "";
        for (var e : args) {
            if (e.matches("^-.+$")) {
                key = e;
            }
            argsConfig.put(key, e);
        }
        makePattern();
    }

    private void makePattern() {
        var temp = argsConfig.get("-n");
        int x = 0;
        if (argsConfig.get("-m") != null) {
            if (temp.startsWith("*")) {
                temp = "." + temp;
            }
            if (temp.endsWith("*")) {
                temp = temp.substring(0, temp.length() - 1) + ".*";
            }
            pattern = Pattern.compile(temp);
            x++;
        }
        if (argsConfig.get("-f") != null) {
            pattern = Pattern.compile("^" + temp + "$");
            x++;
        }
        if (argsConfig.get("-r") != null) {
            pattern = Pattern.compile(temp);
            x++;
        }
        if (x > 1 || x == 0) {
            pattern = Pattern.compile(".*");
        }
    }

    public String getDirectory() {
        String temp = argsConfig.get("-d");
        if (temp == null) {
            temp = "c:/";
        }
        return temp;
    }

    public String getTarget() {
        String temp = argsConfig.get("-o");
        if (temp == null) {
            temp = "result.txt";
        }
        return temp;
    }

    public String get(String key) {
        String temp = argsConfig.get(key);
        if (temp == null) {
            temp = "Doesn't contains this key";
        }
        return temp;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
