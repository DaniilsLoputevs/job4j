package ru.job4j.chat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.io.Helper;

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
    private String botAnswer = "./src/main/java/ru/job4j/chat/bot_answers.txt";
    private String log = "./src/main/java/ru/job4j/chat/chat_log.txt";
    private Bot bot;
    private Input input;
    private Output testOutput = new Output();

    private List<String> answerList;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void sSetUp() {
        answerList = List.of(
                "Я знаю тебя?\n",
                "Ты кто?\n",
                "Что, жабу съел?\n",
                "Почему молчишь?\n",
                "Почему молчишь? Ты тролишь меня?!\n",
                "Ты просто дурак...\n",
                "Есть трава?\n",
                "Есть трава? А если найду?\n",
                "Я дудь! Не хочешь вдудь?\n",
                "Шутка, я просто под прикьытием )).\n",
                "Кажеться... Я... Слышу голоса в голове... нет, жто я просто перепила вчера.\n"
        );
        input = new StubInput(new String[] {
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
        Helper.writeListToFile(botAnswer, answerList);

        bot = new Bot(botAnswer, input, testOutput::add, log);
        bot.clearLog();
    }


    @Test
    public void testByRealFolder() {
        bot.startSpeech();

        var realLog = (ArrayList) bot.getLogAsList();
        var expectedLog = Helper.readFileToList(log);

        assertThat(realLog, is(expectedLog));

        assertThat(realLog.contains(testOutput.poll()), is(true));
        assertThat(realLog.contains(testOutput.poll()), is(true));
        assertThat(realLog.contains(testOutput.poll()), is(true));
    }

    @Test
    public void testByTempFolder() throws IOException {
        var answers = tempFolder.newFile("bot_answers.txt");
        var chatLog = tempFolder.newFile("chat_log.txt");
        Helper.writeListToFile(answers.getPath(), answerList);
        var tempOutput = new Output();


        // создаём и запускаем чат
        var tempChat = new Bot(answers.getPath(), input, tempOutput::add, chatLog.getPath());
        tempChat.startSpeech();


        // Сравниваем Лого настоящие и внутри чата.
        ArrayList realLog = (ArrayList) tempChat.getLogAsList();
        var expectedLog = Helper.readFileToList(chatLog.getPath());


        assertThat(realLog, is(expectedLog));
        assertThat(realLog.contains(tempOutput.poll()), is(true));
        assertThat(realLog.contains(tempOutput.poll()), is(true));
        assertThat(realLog.contains(tempOutput.poll()), is(true));
    }


}