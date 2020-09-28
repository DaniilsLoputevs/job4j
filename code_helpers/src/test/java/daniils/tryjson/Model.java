package daniils.tryjson;

import java.util.List;

public class Model {
    private String id;
    private String name;
    private List<Integer> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Model{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", list=" + list
                + '}';
    }
}
