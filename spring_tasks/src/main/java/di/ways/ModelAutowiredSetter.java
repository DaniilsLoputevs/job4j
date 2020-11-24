package di.ways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
public class ModelAutowiredSetter {

    private InnerModel innerModel;

    @Autowired
    public void setInnerModel(InnerModel innerModel) {
        this.innerModel = innerModel;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ModelAutowiredSetter.class.getSimpleName() + "[", "]")
                .add("innerModel=" + innerModel)
                .toString();
    }
}
