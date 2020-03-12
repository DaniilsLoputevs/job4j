package ru.job4j.chat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.helpers.IOHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * 1) Тест в обычной папку, 2) Во времянной папке.
 * Устройство теста:
 * - Инит. всех составляющих + Задаём фразы Бота в файл + Задаём фразы Пользователя. + чистим Лог после проглой сессии.
 * - стартуем чат, он проходит все Заданые Фразы и завершается.
 * - сверяем логи сессии м файл лога.
 * - проверяем output, пуллом и сверкой с логом сессии.
 *
 * 2) Так же, но все файлы времянные. + создание альтернативного чата.
 */
public class BotTest {
    private String botAnswerPath = "./src/main/java/ru/job4j/chat/bot_answers.txt";
    private String localLogPath = "./src/main/java/ru/job4j/chat/chat_log.txt";
    private List<String> answersList;
    private Input input;
    private Output output;

    private String tempAnswersPath;
    private String tempLogPath;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void sSetUp() {
        this.answersList = List.of(
                "Я знаю тебя?",
                "Ты кто?",
                "Что, жабу съел?",
                "Почему молчишь?",
                "Почему молчишь? Ты тролишь меня?!",
                "Ты просто дурак...",
                "Есть трава?",
                "Есть трава? А если найду?",
                "Я дудь! Не хочешь вдудь?",
                "Шутка, я просто под прикьытием )).",
                "Кажеться... Я... Слышу голоса в голове... нет, жто я просто перепила вчера."
        );
        this.input = new StubInput(new String[] {
                "Привет!",
                "Как тебя зовут?",
                "Скажи, ты Скайнет?",
                "стоп",
                "Интересно, ты умеешь мысли читать?",
                "Правда, я это скорее всего не узнаю...",
                "продолжить",
                "Детка, конфетку?",
                "Ты в каком году родилась?",
                "У тебя есть паспорт?",
                "закончить",
        });
        IOHelper.writeListToFile(botAnswerPath, answersList, "");
        this.output = new Output();
    }


    @Test
    public void testByRealFolder() {
        baseTestModel(botAnswerPath, localLogPath);
    }

    @Test
    public void testByTempFolder() {
        initTempFolder();
        baseTestModel(tempAnswersPath, tempLogPath);
    }

    private void baseTestModel(String answersPath, String logPath) {
        // создаём и запускаем чат
        var bot = new Bot(answersPath, input, output::add, logPath);
        bot.startSpeech();

        // инит Логов: настоящий и сессионый.
        var realLog = (ArrayList) bot.getLogAsList();
        var expectedLog = IOHelper.readFileToList(logPath);

        // Провера
        assertThat(realLog, is(expectedLog));
        assertThat(realLog.contains(output.poll()), is(true));
        assertThat(realLog.contains(output.poll()), is(true));
        assertThat(realLog.contains(output.poll()), is(true));
    }

    private void initTempFolder() {
        try {
            this.tempAnswersPath = tempFolder.newFile("bot_answers.txt").getPath();
            this.tempLogPath = tempFolder.newFile("chat_log.txt").getPath();
            IOHelper.writeListToFile(tempAnswersPath, answersList, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}