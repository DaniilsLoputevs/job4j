package di.ways;

import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
public class ModelComponent {
    private InnerModel innerModel;

    public ModelComponent(InnerModel innerModel) {
        this.innerModel = innerModel;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ModelComponent.class.getSimpleName() + "[", "]")
                .add("innerModel=" + innerModel)
                .toString();
    }
}
