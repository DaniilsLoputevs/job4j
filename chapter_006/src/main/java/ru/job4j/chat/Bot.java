package ru.job4j.chat;

import ru.job4j.io.Helper;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/** Задача: сделать консольный чат. ввод от пользователя, - овет Случайная фраза из файла с Фразами.
 * + Поддержка Команд:
 * "стоп" - Бот перестаёт отвечать, пользователю.
 * "продолжить" - Бот снова отвечает, пользователю.
 * "закончить". - Завершить программу, сохранить лог сессии.(перезаписать старый)
 *
 * List<String> phrase - Список фраз для ответа Пользователю.
 * List<String> sessionLog - Лог сессии, записсываеться всё что сказано Обеими сторонами.
 * String logPath - путь лога чата. Сюда созраняется лог сессии, после окончания.
 * Input input - Интерфэйс для ввода, две реализации. 1) ConsoleInput - для ввода через консоль.
 *                                                    2) StubInput - для ввода List'ом в @Test'ах.
 * Consumer<String> output - Метод вывода ответа Пользователю. Есть класс Output, это буффер для приёма и послед.
 * ввывода куда-угодно. (Пример, List для сортировки/сббора статистики ответов)
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 20.02.20.
 */
public class Bot {
    private boolean speak = true;
    private List<String> phrase;
    private List<String> sessionLog = new LinkedList<>();
    private String logPath;
    private Input input;
    private Consumer<String> output;


    public Bot(String botAnswerPath, Input input, Consumer<String> output, String logPath) {
        this.phrase = Helper.readFileToList(botAnswerPath);
        this.input = input;
        this.output = output;
        this.logPath = logPath;
    }

    /**
     * Стартовый метод.
     * При вызове, запускает сессию и вызывает рекурсию.
     * Каждаю итерация просит ввести 1 строчку, как "ответ пользователя", иначе встанет.
     *** В такой ситуации Потенциально может быть NPE в тесте из StubInput.next() => return null;
     */
    public void startSpeech() {
        String userPhrase = input.next();
        sessionLog.add("Вася с 5-ой : " + userPhrase + "\n");
        botExercise(userPhrase);
    }

    /**
     * Обработка Ботом, Фразы пользователя и Ответ на неё.
     * реализация "команд" для Бота.
     * @param says Фраза пользователя. (input.next() )
     */
    private void botExercise(String says) {
        if (says.equals("стоп")) {
            this.speak = false;
            botAnswer();
        } else if (says.equals("продолжить")) {
            this.speak = true;
            botAnswer();
        } else if (says.equals("закончить")) {
            saveLog();
        } else {
            botAnswer();
        }
    }

    /** Повторяющийся блок кода из botExercise()
     * Ответ Бота, запись ответа в Лог, запись ответа в output, замкнуть рекурсию(снова вызвать startSpeech() )
     */
    private void botAnswer() {
        if (speak) {
            var botSay = phrase.get(choosePhrase());
            botSay = "Ночьная бабочка: " + botSay + "\n";
            output.accept(botSay);
            sessionLog.add(botSay);
            startSpeech();
        } else {
            startSpeech();
        }
    }

    /** ----------------- Log methods ----------------- */

    public void saveLog() {
        Helper.writeListToFile(logPath, sessionLog);
    }
    public void clearLog() {
        Helper.clearFile(logPath);
    }
    public List getLogAsList() {
        return sessionLog.stream()
                .map(string -> string.substring(0, string.length() - 1))
                .collect(Collectors.toList());
    }


    /**
     * Выбрает случайную фразу из файла с фразами.
     * Путём выбора случайного индекса строки в файле.
     * @return int - индекс строки.
     */
    private int choosePhrase() {
        int min = 0;
        int max = phrase.size() - 1;
        return (int) (Math.random() * (max - min + 1) + min);
    }


    /** ------------ Ручной тест, консолью ------------*/

    public static void main(String[] args) {

        var botAnswerPath = "./chapter_006/src/main/java/ru/job4j/chat/bot_answers.txt";
        var input = new ConsoleInput();
        Consumer<String> output = System.out::println;
        var chatLog = "./src/main/java/ru/job4j/chat/chat_log.txt";

        new Bot(botAnswerPath, input, output, chatLog).startSpeech();
    }
}