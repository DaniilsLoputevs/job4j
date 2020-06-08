package ru.job4j.exam;

import org.junit.Ignore;
import ru.job4j.exam.io.ConsoleInput;
import ru.job4j.exam.io.Output;
import ru.job4j.exam.io.ValidateInput;

public class FieldTest {


    @Ignore
    public void run() {
//        var t = new ConsoleInput();
        var t = new ValidateInput(new ConsoleInput());
        t.askTurnParam("", new Output(System.out::println));
//        t.convertAnswer("");


    }

    @Ignore
    public void scannerTest() {


//        var scanner = new Scanner(System.in);
//        System.out.println(scanner.nextLine());

    }
}