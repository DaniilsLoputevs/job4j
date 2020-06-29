package ru.job4j.concurrent.downloader;

import daniils.api.ArgsLambda;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * expected args:
 * -url {url value} -t {target path} -n {name} -s {num} {kof}
 * num = "100"
 * kof = "b"
 */
public class Download {
    public static void logic(String[] args) {
        var config = new Config().processingArgs(args);

        String url = config.get("-url").get(0);
        String target = config.get("-t").get(0);
        String name = config.get("-n").get(0);
        long speed = speedConvert(config.get("-s"));

        try (var in = new BufferedInputStream(new URL(url).openStream());
             var fileOutputStream = new FileOutputStream(target + '/' + name)) {

            byte[] dataBuffer = new byte[(int) speed];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, (int) speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static long speedConvert(List<String> speed) {
        long realSpeed = Integer.parseInt(speed.get(0));
        var speedInString = speed.get(1);
        if (speedInString.equalsIgnoreCase("kb")) {
            realSpeed *= 1000;
        } else if (speedInString.equalsIgnoreCase("mb")) {
            realSpeed *= 1000000;
        }
        return realSpeed;
    }

    public static void main(String[] args) {
        logic(args);
    }

    /**
     * -url {url value} -t {target path} -n {name} -s {num} {kof}
     */
    private static class Config {

        public Map<String, List<String>> processingArgs(String[] args) {
            return ArgsLambda.build()
                    .add("-url", arg -> arg.contains("https://"))
                    .add("-t", ars -> true)
                    .add("-n", ars -> true)
                    .add("-s", List.of(
                            arg -> arg.matches("^\\d*$"),
                            arg -> arg.equalsIgnoreCase("b") || arg.equalsIgnoreCase("kb")
                                    || arg.matches("mb")))
                    .load(args)
                    .print()
                    .runToMap();
        }
    }
}