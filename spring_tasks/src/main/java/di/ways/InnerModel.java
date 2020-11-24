package di.ways;

import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
public class InnerModel {
    private int id;
    private String name;

    public InnerModel() {
    }

    public InnerModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", InnerModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
