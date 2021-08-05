package di.ways;

import di.ways.extra.PlatformHolder;
import di.ways.extra.PlatformHolderAutoQualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDi {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("di.ways");
        context.refresh();


        var modelComponent = context.getBean(ModelComponent.class);
        var modelAutowired = context.getBean(ModelAutowired.class);
        var modelAutowiredSetter = context.getBean(ModelAutowiredSetter.class);

        System.out.println("modelComponent: " + modelComponent);
        System.out.println("modelAutowired: " + modelAutowired);
        System.out.println("modelAutowiredSetter: " + modelAutowiredSetter);

        /* EXTRA */

        // Qualifier
        var platHolder = context.getBean(PlatformHolder.class);

        System.out.println("platHolder: " + platHolder.getMainPlatformName());
        /* output depending on configuration Qualifier
        * OUT:
        * platHolder: PlatformDefault
        * OR
        * platHolder: PlatformNew
        * */


        // auto name-qualifier by: varName == beanName
        var platHolderAutoQualifier = context.getBean(PlatformHolderAutoQualifier.class);

        System.out.println("platHolder: " + platHolderAutoQualifier.getMainPlatformName());

        // see more on: https://www.baeldung.com/spring-autowire

    }
}
