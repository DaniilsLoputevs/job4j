package di.ways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
public class ModelAutowired {

    @Autowired
    private InnerModel innerModel;


    @Override
    public String toString() {
        return new StringJoiner(", ", ModelAutowired.class.getSimpleName() + "[", "]")
                .add("innerModel=" + innerModel)
                .toString();
    }
}
