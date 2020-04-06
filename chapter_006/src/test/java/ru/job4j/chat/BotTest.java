package ru.job4j.chat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.helpers.IOHelper;
import ru.job4j.helpers.StringHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Устройство теста:
 * - Инит. всех составляющих + Задаём фразы Бота в файл + Задаём фразы Пользователя.
 * - стартуем чат, он проходит все Заданые Фразы и завершается.
 * - сверяем логи сессии м файл лога.
 * - проверяем output, пуллом и сверкой с логом сессии.
 */
public class BotTest {
    private Bot chatBot;
    private Input input;
    private Output output;

    private String logPath;

    private String botReplicasPath;
    private List<String> botReplicasText;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void sSetUp() {
        this.botReplicasText = List.of(StringHelper.separateLines(
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
        ));
        this.input = new StubInput(new String[]{
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
        initTempFiles();

        this.output = new Output();
        this.chatBot = new Bot(botReplicasPath, input, output::add, logPath);
    }

    @Test
    public void testByTempFolder() {
        chatBot.startSpeech();

        // инит Логов: настоящий и сессионый.
        List<String> realLog = chatBot.getLogAsList();
        List<String> expectedLog = StringHelper.separateLines(
                IOHelper.readFileToList(logPath, ArrayList::new));

        // Провера
        assertEquals(expectedLog, realLog);
        assertTrue(realLog.contains(output.poll()));
        assertTrue(realLog.contains(output.poll()));
        assertTrue(realLog.contains(output.poll()));
        assertTrue(realLog.contains(output.poll()));
    }

    private void initTempFiles() {
        try {
            this.botReplicasPath = tempFolder.newFile("bot_replicas_path.txt").getPath();
            this.logPath = tempFolder.newFile("chat_log.txt").getPath();
            IOHelper.writeListToFile(botReplicasPath, botReplicasText, "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}