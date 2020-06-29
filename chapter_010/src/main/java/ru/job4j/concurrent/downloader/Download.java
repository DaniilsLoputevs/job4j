package ru.job4j.concurrent.downloader;

import daniils.api.ArgsLambda;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class Download {
    public static void main(String[] args) {
        Properties config = new ArgsLambda.Builder()
                .add("-url", arg -> arg.contains("https://"))
                .add("-s", List.of(
                        arg -> arg.matches("[0-9]"),
                        arg -> arg.matches("kb") || arg.matches("b")))
                .loadAndRun(args);


        String file = config.getProperty("-url");
        try (var in = new BufferedInputStream(new URL(file).openStream());
             var fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
//            int speed = speedConvert(config.getProperty("-s"));
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

//    private static int speedConvert(String speed) {
//        return null;
//    }
}
