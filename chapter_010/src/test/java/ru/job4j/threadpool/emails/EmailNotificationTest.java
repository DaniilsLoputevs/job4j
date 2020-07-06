package ru.job4j.threadpool.emails;

import daniils.StringHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class EmailNotificationTest {
    private ByteArrayOutputStream newOutput;
    private PrintStream defaultOutput;

    @Before
    public void changeOutput() {
        newOutput = new ByteArrayOutputStream();
        defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
    }

    @After
    public void returnOutput() {
        System.setOut(defaultOutput);
    }

    @Test
    public void iterateTwoThreads() {
        System.out.println("### Start Program ###");
        var emailService = new EmailNotification();
        var one = new User("El", "japanSaya@jmail.jp");
        var two = new User("Kira", "el@gmail.com");
        emailService.emailTo(one);
        emailService.emailTo(two);
        emailService.close();

        while (!emailService.isFinished()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("### Finish Program ###");

        assertTrue(newOutput.toString().contains(consoleLog()[0]));
        assertTrue(newOutput.toString().contains(consoleLog()[1]));
    }

    private String[] consoleLog() {
        var rsl = new String[2];
        var tempOne = List.of(
                "Notification El to email japanSaya@jmail.jp.",
                "Add a new event to El",
                "japanSaya@jmail.jp"
        );
        var tempTwo = List.of(
                "Notification Kira to email el@gmail.com.",
                "Add a new event to Kira",
                "el@gmail.com"
        );
        rsl[0] = StringHelper.separateAndMerge(tempOne);
        rsl[1] = StringHelper.separateAndMerge(tempTwo);
        return rsl;
    }
}