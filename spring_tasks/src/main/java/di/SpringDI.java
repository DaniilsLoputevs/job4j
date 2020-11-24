package di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.register(Store.class);
        appContext.register(StartUI.class);
        appContext.register(ConsoleInput.class);


        appContext.refresh();
        StartUI ui = appContext.getBean(StartUI.class);
        ui.add("Petr Arsentev");
        ui.add("Ivan ivanov");
        ui.print();

        ConsoleInput input = appContext.getBean(ConsoleInput.class);
        input.add("Petr Arsentev");
        input.add("Ivan ivanov");
        input.add("Andrei Hincu");
        input.toOut(System.out::println);
    }
}
