package ru.job4j;

import java.io.*;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        this.file = f;
    }

    public synchronized File getFile() {
        return this.file;
    }

    public synchronized String getContent() throws IOException {
        var input = new InputStreamReader(new FileInputStream(file));
        var output = new StringBuilder();
        int data;
        while ((data = input.read()) > 0) {
            output.append((char) data);
        }
        return output.toString();
    }

    /**
     * * if (data < 0x80) - проверяет данные из файла не берутся нормальные символы по таблице ASCII (< 128)  .
     *
     * @return -
     * @throws IOException -
     */
    public synchronized String getContentWithoutUnicode() throws IOException {
        var input = new InputStreamReader(new FileInputStream(file));
        var output = new StringBuilder();
        int data;
        while ((data = input.read()) > 0) {
            if (data < 0x80) {
                output.append((char) data);
            }
        }
        return output.toString();
    }

    public synchronized void saveContent(String content) throws IOException {
        var output = new FileOutputStream(file);
        output.write(content.getBytes());
    }
}
