package daniils.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Class for construct {@see java.util.Properties} from args{@see public static void main(String[] args)}
 * How to use:
 * <p>
 * This is pattern Builder and looks like Stream API code. Make core and add options that you need.
 * <p>
 * - example: {@code
 * Properties prop = new ArgsLambda.Builder()
 * .add("url", arg -> arg.contains("www."))
 * .print()
 * .loadAndRun(args);
 * }
 * * For more examples {@see class ArgsLambdaExamples}
 * <p>
 * API:
 * ### add(...) ### - add arg that you expect from console and add validate for value of this key.
 * - add({@param expectedFlag})                                                       -- add flag
 * - add(String {@param expectedKey}, Predicate<String> {@param valueValidate})                -- add normal-key
 * - add(String {@param expectedMultiKey}, List<Predicate<String>> {@param paramsValidates})   -- add multi-key
 * <p>
 * ### load(...) ### - load args from console.
 * - load(String[] {@param args}}                    -- It can be first or pre-last option.
 * - loadAndRun(String[] {@param args}}              -- {API load} && {API run} in code string.
 * <p>
 * ### run() ### - start conversion arguments to Properties. It is final option.
 * - run()
 * - runToMap() - run and return {@see java.util.Map}
 * <p>
 * ### update(...) ### - update {@param secondProperties} to {@param firstProperties}.
 * - update(Properties firstProperties, Properties secondProperties)
 * ***** Additional options *****
 * <p>
 * ### print(...) ### - turn on print all warnings. default: false
 * - print()
 * - print({@param Consumer<String> output} ) - print all warnings to this output.
 * <p>
 * ## continuable ## - continue processing if any param fail validate. Default: false
 * - continuable()
 * <p>
 * <p>
 * <p>
 * Args type:
 * flags - expect that this key will not have param.
 * ask Properties to get flag value, - it will return flag:
 * key: "flag" - value: "flag".
 * code:
 * String value = prop.get("-show");  // -show - show something(true/false default: false)
 * values == "-show"  // if {value} != null ==>> you have this flag, else args doesn't contains this flag.
 * <p>
 * <p>
 * normal-key - key with 1 param.
 * ask Properties to get key value, - it will return:
 * key: "key" - value: "key param".
 * code:
 * String value = prop.get("-t");  // -t - target path
 * values == "C://root_folder/folder/..."
 * <p>
 * <p>
 * multi-key - Key with 2 and more params.
 * ask Properties to get multi-key value, - it will return:
 * key: "multi-key" - value: "-{first param}-{second param}-{third param}".
 * code:
 * String value = prop.get("-s");  // -s - speed - download speed
 * values == "1-kb-s"
 * * You can parse this value in your program.
 */
public class ArgsLambda {
    /**
     * final Properties that it build and fill.
     */
    private final Properties properties = new Properties();

    /**
     * Map contains all expected normal-keys and their validate.
     */
    private final Map<String, Predicate<String>> keyMap = new HashMap<>();
    /**
     * Map contains all expected multi-keys and their validate.
     */
    private final Map<String, List<Predicate<String>>> multiKeyMap = new HashMap<>();
    /**
     * args from console.
     */
    private String[] args;

    /* Settings */

    /**
     * Print all warnings. Default: false
     */
    private boolean print = false;
    /**
     * If print warnings ==>> print to? Default: System.out
     */
    private Consumer<String> output = System.out::println;

    /**
     * continue processing if any param fail validate. Default: false
     */
    private boolean continuable = false;


    public static class Builder {
        private final ArgsLambda constructor;

        public Builder() {
            this.constructor = new ArgsLambda();
        }

        /**
         * add flag.
         *
         * @param flag flag.
         * @return this builder.
         */
        public Builder add(String flag) {
            constructor.properties.setProperty(flag, flag);
            return this;
        }

        /**
         * add normal key.
         *
         * @param key           key.
         * @param paramValidate validate for key's param.
         * @return this builder.
         */
        public Builder add(String key, Predicate<String> paramValidate) {
            constructor.keyMap.put(key, paramValidate);
            return this;
        }

        /**
         * add multi-key. You need to parse value for use. {@see class javaDoc}
         *
         * @param key             key.
         * @param paramsValidates validates for key's params.
         * @return this builder.
         */
        public Builder add(String key, List<Predicate<String>> paramsValidates) {
            constructor.multiKeyMap.put(key, paramsValidates);
            return this;
        }

        /**
         * Load args from console.
         *
         * @param args args from console.
         * @return this builder.
         */
        public Builder load(String[] args) {
            constructor.args = args;
            return this;
        }

        /**
         * {API load} && {API run} in code string.
         *
         * @param args from console.
         * @return {@code java.util.Properties}
         */
        public Properties loadAndRun(String[] args) {
            constructor.args = args;
            return run();
        }

        /**
         * Start conversion arguments to Properties. It is final option.
         *
         * @return {@code java.util.Properties}.
         */
        public Properties run() {
            if (!this.constructor.keyMap.isEmpty()) {
                keyRun();
            }
            if (!this.constructor.multiKeyMap.isEmpty()) {
                multiKeyRun();
            }
            return this.constructor.properties;
        }

        /**
         * Start conversion arguments to Properties. It is final option.
         *
         * @return {@code java.util.Map<String, String>}.
         */
        public Map<String, String> runToMap() {
            return (Map) run();
        }

        /**
         * Merge {@param secondProperties} to {@param firstProperties}.
         * Don't override properties. Only add new properties.
         * * add all properties pair that doesn't contains or value = null
         *
         * @param firstProperties  -
         * @param secondProperties -
         * @return updated {@param firstProperties}.
         */
        public Builder update(Properties firstProperties, Properties secondProperties) {
            for (var tempEntry : secondProperties.entrySet()) {
                var keyParam = firstProperties.getProperty((String) tempEntry.getKey());
                if (keyParam == null) {
                    firstProperties.setProperty(
                            (String) tempEntry.getKey(), (String) tempEntry.getValue());
                }
            }
            return this;
        }

        /**
         * print all warnings to console.
         *
         * @return this builder.
         */
        public Builder print() {
            constructor.print = true;
            return this;
        }

        /**
         * print all warnings to this output.
         *
         * @param output -
         * @return this builder.
         */
        public Builder print(Consumer<String> output) {
            constructor.print = true;
            constructor.output = output;
            return this;
        }

        /**
         * Use this option if you want to continue validate then one or more params fail validate.
         *
         * @return this builder.
         */
        public Builder continuable() {
            constructor.continuable = true;
            return this;
        }

        /* ####### private thing ####### */

        private void keyRun() {
            for (int i = 0; i < constructor.args.length; i++) {
                var currentArg = constructor.args[i];
                var paramValidate = constructor.keyMap.remove(currentArg);

                check:
                if (paramValidate != null) {
                    if (i < constructor.args.length - 1) {
                        var expectedKeyParam = constructor.args[i + 1];

                        if (paramValidate.test(expectedKeyParam)) {
                            constructor.properties.setProperty(currentArg, expectedKeyParam);
                        } else {
                            if (constructor.print) {
                                constructor.output.accept(showFailValidateMsg(currentArg, expectedKeyParam));
                            }
                            if (!constructor.continuable) {
                                break check;
                            }
                        }
                    } else {
                        if (constructor.print) {
                            constructor.output.accept(showArgsWithoutParams(currentArg));
                        }
                        if (!constructor.continuable) {
                            break check;
                        }
                    }
                }
            }
        }

        private void multiKeyRun() {
            for (int i = 0; i < constructor.args.length; i++) {
                var currentArg = constructor.args[i];
                List<Predicate<String>> validateList = constructor.multiKeyMap.remove(currentArg);

                check:
                if (validateList != null) {
                    if (i < constructor.args.length - validateList.size()) {
                        var resultParamValue = new StringBuilder();
                        int paramIndex = 1;

                        for (Predicate<String> paramPredicate : validateList) {
                            var expectedKeyParam = constructor.args[i + paramIndex++];

                            if (paramPredicate.test(expectedKeyParam)) {

                                if (paramIndex == 2) {
                                    resultParamValue.append(expectedKeyParam);
                                } else {
                                    resultParamValue.append("-").append(expectedKeyParam);
                                }
                            } else {
                                if (constructor.print) {
                                    constructor.output.accept(showFailValidateMsg(currentArg, expectedKeyParam));
                                }
                                if (!constructor.continuable) {
                                    break check;
                                }
                            }
                        }
                        constructor.properties.setProperty(currentArg, resultParamValue.toString());
                    } else {
                        if (constructor.print) {
                            constructor.output.accept(showArgsWithoutParams(currentArg));
                        }
                        if (!constructor.continuable) {
                            break check;
                        }
                    }
                }
            }
        }

        private String showFailValidateMsg(String key, String param) {
            return "WARNING - ArgsLambda: This key and value fail validate:" + System.lineSeparator()
                    + "key:   " + key + System.lineSeparator()
                    + "value: " + param;
        }

        private String showArgsWithoutParams(String key) {
            return "WARNING - ArgsLambda: this args don't have all "
                    + "requested parameters for this key:" + System.lineSeparator()
                    + "key:   " + key + System.lineSeparator();
        }

    }
}