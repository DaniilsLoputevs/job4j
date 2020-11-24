package di;

public class Main {
    public static final Context GLOBAL_CONTEXT = init();

    private static Context init() {
        var context = new Context();
        context.reg(Store.class);
        context.reg(StartUI.class);
        context.reg(ConsoleInput.class);
        return context;
    }

    public static void main(String[] args) {

        StartUI ui = GLOBAL_CONTEXT.get(StartUI.class);
        ui.add("Petr Arsentev");
        ui.add("Ivan ivanov");
        ui.print();

        ConsoleInput input = GLOBAL_CONTEXT.get(ConsoleInput.class);
        input.add("Petr Arsentev");
        input.add("Ivan ivanov");
        input.add("Andrei Hincu");
        input.toOut(System.out::println);
    }

}
