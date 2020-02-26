package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/** Задача: сделать бота с использованием Socket. Називаеться Серверный Оракул.
 * Суть поожа с задачей "Консольный бот", но с уклоном к Socket.
 *
 * Как работает socket:
 * Есть socket.getInputStream() - Входные данные. для сокета.
 * Есть socket.getOutputStream() - Выходные данные. для сокета.
 * Записываем их в Буфферы, а после используем по назначению.
 * ask = in.readLine();                 - исп. InputStream.
 * out.println("I don't understood.");  - исп. OutputStream.
 *
 */
public class ServerOracle {
    private Socket socket;

    public ServerOracle(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        var in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        var out = new PrintWriter(this.socket.getOutputStream(), true);
        String ask = null;
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);
            if ("hello".equals(ask)) {
                out.println("Hello, dear friend, I'm a oracle.");
                out.println();
            } else if (!("exit".equals(ask))) {
                out.println("I don't understood.");
                out.println();
            }
        } while (!("exit".equals(ask)));
    }


//    public static void main(String[] args) throws IOException {
//        try (final Socket socket = new ServerSocket(111).accept()) {
//            new ServerOracle(socket);
//        }
//    }

}
