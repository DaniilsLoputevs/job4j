package di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    // first task
//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
//        appContext.scan("di");
//
//        appContext.refresh();
//        StartUI ui = appContext.getBean(StartUI.class);
//        ui.add("Petr Arsentev");
//        ui.add("Ivan ivanov");
//        ui.print();
//
//        ConsoleInput input = appContext.getBean(ConsoleInput.class);
//        input.add("Petr Arsentev");
//        input.add("Ivan ivanov");
//        input.add("Andrei Hincu");
//        input.toOut(System.out::println);
//    }

    // second task
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("di");
        context.refresh();

        Store store = context.getBean(Store.class);
        store.add("Petr Arsentev");

        Store another = context.getBean(Store.class);
        another.getAll().forEach(System.out::println);
        /* 
        * scope mode:
        * 1. singleton - one object on JVM. (default configuration in Spring)
        * 2. prototype - create new object each time then program ask the context.
        *
        * * For web-project:
        *
        * 3. session - exists while session exists.
        * 4. request - exists while request exists.
        * 5. application - exists while application exists.
        * 6. websocket - exists while websocket have active session.
        * */
        /*
        * See more about:
        * @Scope :: for Methods
        * @Scope :: custom scope
        * */

    }
}
