package ru.job4j.chat;

import ru.job4j.helpers.IOHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/** Задача: сделать консольный чат. ввод от пользователя, - ответ Случайная фраза из файла с Фразами.
 * + Поддержка Команд:
 * "стоп" - Бот перестаёт отвечать, пользователю.
 * "продолжить" - Бот снова отвечает, пользователю.
 * "закончить". - Завершить программу, сохранить лог сессии.(перезаписать старый)
 *
 * boolean run - продолжать работу основного цикла или нет? true || false)
 * boolean speak - отвечать боту на "фразы пользователя" или нет? (true || false)
 * List<String> phrase - Список фраз для ответа "Бота".
 * List<String> sessionLog - Лог сессии, записывается всё что сказано Обеими сторонами.
 * String logPath - путь лога чата. Сюда сохраняется лог сессии, после окончания.
 * Input input - Интерфейс для ввода, две реализации. 1) ConsoleInput - для ввода через консоль.
 *                                                    2) StubInput - для ввода List'ом в @Test'ах.
 * Consumer<String> output - Метод вывода ответа Пользователю. Есть класс Output, это буфер для приёма и послед.
 * вывода куда-угодно. (Пример, List для сортировки/сбора статистики ответов)
 *
 * @author Daniils Loputevs
 * @version 1.1
 * @since 18.02.20.
 * Last upd:  12.03.20.
 * Last JavaDoc upd:  12.03.20.
 */
public class Bot {
    private boolean run = true;
    private boolean speak = true;
    private List<String> phrase;
    private List<String> sessionLog = new LinkedList<>();
    private String logPath;
    private Input input;
    private Consumer<String> output;


    public Bot(String botAnswerPath, Input input, Consumer<String> output, String logPath) {
        this.phrase = IOHelper.readFileToList(botAnswerPath);
        this.input = input;
        this.output = output;
        this.logPath = logPath;
    }

    /** Стартовый метод, запускает сессию.
     * Каждую итерация просит ввести 1 строчку, как "ответ пользователя", иначе будет вечное ожидание(заснёт).
     * Обязательный выход через "подачу" команды "закончить".
     *** В такой ситуации Потенциально может быть NPE в тесте из StubInput.next() => return null;
     */
    public void startSpeech() {
        while (run) {
            initAndStartSpeech();
        }
    }

    /** Подготовка и запуск сессии.
     * Обновление "фразы пользователя", добавление её в лог, вызов обработки "фразы" ботом.
     */
    private void initAndStartSpeech() {
        String userPhrase = input.next();
        sessionLog.add("Какой-то парень : " + userPhrase + "\n");
        botExercise(userPhrase);
    }

    /** Обработка, Фразы пользователя Ботом и Ответ на неё.
     * реализация "команд" для Бота.
     * @param says Фраза пользователя. (input.next() )
     */
    private void botExercise(String says) {
        if ("стоп".equals(says)) {
            this.speak = false;
            botAnswer();
        } else if ("продолжить".equals(says)) {
            this.speak = true;
            botAnswer();
        } else if ("закончить".equals(says)) {
            saveLog();
            this.run = false;
        } else {
            botAnswer();
        }
    }

    /** Повторяющийся блок кода из botExercise()
     * Ответ Бота, запись ответа в Лог, запись ответа в output.
     */
    private void botAnswer() {
        if (speak) {
            var botSay = phrase.get(choosePhrase());
            botSay = "Бот Катя: " + botSay + "\n";
            output.accept(botSay);
            sessionLog.add(botSay);
        }
    }

    /** ----------------- Log methods ----------------- */

    public void saveLog() {
        IOHelper.writeListToFile(logPath, sessionLog, "");
    }
    public void clearLog() {
        IOHelper.clearFile(logPath);
    }
    public List getLogAsList() {
        return sessionLog.stream()
                .map(string -> string.substring(0, string.length() - 1))
                .collect(Collectors.toList());
    }


    /** Выбирает случайную фразу из файла с фразами.
     * Путём выбора случайного индекса строки в файле.
     * @return int - индекс строки.
     */
    private int choosePhrase() {
        int min = 0;
        int max = phrase.size() - 1;
        return (int) (Math.random() * (max - min + 1) + min);
    }


    /* ------------ Ручной тест, консолью ------------*/

//    public static void main(String[] args) {
//
//        var botAnswerPath = "./chapter_006/src/main/java/ru/job4j/chat/bot_answers.txt";
//        var input = new ConsoleInput();
//        Consumer<String> output = System.out::println;
//        var chatLog = "./src/main/java/ru/job4j/chat/chat_log.txt";
//
//        new Bot(botAnswerPath, input, output, chatLog).startSpeech();
//    }

}