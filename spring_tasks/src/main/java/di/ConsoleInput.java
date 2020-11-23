package di;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsoleInput {

    private final List<String> inputBuffer = new ArrayList<>();

    public void add(String value) {
        inputBuffer.add(value);
    }

    public List<String> getContent() {
        return inputBuffer;
    }
    public void toOut(Consumer<String> out) {
        inputBuffer.forEach(out);
    }
}
