package daniils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class OutputHelper {
    private Consumer<Object> outputC;
    private Function<Object, Object> outputF;
    private Predicate<Object> outputP;
    private Supplier<Object> outputS;

    public OutputHelper(Consumer<Object> outputC) {
        this.outputC = outputC;
    }
    public OutputHelper(Function<Object, Object> outputF) {
        this.outputF = outputF;
    }
    public OutputHelper(Predicate<Object> outputP) {
        this.outputP = outputP;
    }
    public OutputHelper(Supplier<Object> outputS) {
        this.outputS = outputS;
    }

    public Object accept(Object string) {
        if (outputC != null) {
            outputC.accept(string);
        } else if (outputF != null) {
            outputF.apply(string);
        } else if (outputP != null) {
            outputP.test(string);
        } else {
            return outputS.get();
        }
        return null;
    }
}
