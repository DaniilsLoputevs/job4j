package daniils.api;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * NEW JAVADOC
 *
 *
 *
 * Class for processing "args from console"{@link Properties}.
 * HOW IT WORKS
 * example:
 * We have: String[] args = new String {"keyOne", "ArgOne", "KeyTwo", "ArgTwo"}
 * We need: check that ArgOne - pass logic check for THIS arg. IF pass >>> write this arg to pair with "keyOne".
 * and the same for "ArgTwo" and "keyOne".
 * code:
 * String[] args = new String {"-url", "www.example.com", "-command", "\\open"}
 * Properties prop = new ArgsLambda.Builder()
 * .add("-url", arg -> arg.contains("www."))
 * .add("-command", arg -> arg.contains("\\"))
 * .print()
 * .loadAndRun(args);
 * }
 * ##### For more code examples see {@see ArgsLambdaExamples} #####
 * ### Some terms that this javaDoc use ###
 * ### Args type ###
 * <p>
 * flags - expect that this key will not have param.
 * * this is for logical check: Do we have this option or not (TRUE || FALSE).
 * code:
 * If args HAVE flag:
 * property.get("flag"); ==> will return: "flag"
 * If args DON'T HAVE flag:
 * property.get("flag"); ==> will return: null
 * </p>
 * <p>
 * normal-key - key with 1 param.
 * code:
 * If arg PASS validate:
 * property.get("key"); ==> will return: "KeyParam"
 * If args DON'T PASS validate:
 * WARNINGS: N key fail validate with param: M - and stop processing next args.
 * * OR you can use option {API continuable();} and continue, but you will have:
 * property.get("key"); ==> will return: null
 * </p>
 * <p>
 * multi-key - Key with 2 and more params.
 * * IF YOU USE THIS KEY - please you option {API runToMap();}.
 * * It will be better way that parse your value.
 * If arg PASS validate:
 * property.get("multi-key"); ==> will return: "keyParam1-keyParam2-keyParam3".
 * OR
 * property.get("multi-key"); ==> will return: list.of("keyParam1", "keyParam2", "keyParam3").
 * If args DON'T PASS validate:
 * WARNINGS: N key fail validate with param: M - and stop processing next args.
 * * OR you can use option {API continuable();} and continue, but you will have:
 * property.get("key"); ==> will return: null
 * </p>
 * ### Short API description ###
 * ### add(...) ### - add pair of: arg that you expect and validate for param of this arg
 * - add(String expectedFlag)                                                -- add flag
 * - add(String expectedKey, Predicate<String> valueValidate)                -- add normal-key
 * - add(String expectedMultiKey, List<Predicate<String>> paramsValidates)   -- add multi-key
 * <p>
 * ### load(...) ### - load args for processing.
 * - load(String[] args}                    -- It can be first or pre-last option.
 * - loadAndRun(String[] args}              -- {API load} && {API run} in code string.
 * <p>
 * ### run() ### - start conversion arguments to Properties. It is final option.
 * - run()                               - return {@link Properties}.
 * - runToMap()                          - return Map. key = "key" - value = List<Key's param>.
 * <p>
 * ### update(...) ### - update {@param secondProperties} to {@param firstProperties}.
 * - update(Properties firstProperties, Properties secondProperties)
 * ***** Additional options *****
 * <p>
 * ### print(...) ### - turn on print all warnings. default: false
 * - print()                                  - change Setting: print = true;
 * - print({@param Consumer<String> output} ) - print all warnings to this output.
 * <p>
 * ## continuable ## - continue processing if any param fail validate. Default: false
 * - continuable()
 *
 *
 *
 * OLD JAVADOC - maybe it will help to somebody.
 *
 *
 *
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
 * - runToMap() - return Map. key = "key" - value = List<Key's param>
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
 * key: "multi-key" - value: "{first param}-{second param}-{third param}".
 * code:
 * String value = prop.get("-s");  // -s - speed - download speed
 * values == "1-kb-s"
 * * You need parse this value in your program.
 * * OR you can use {API runToMap()} to get separated params.
 *
 * @author Daniils Loputevs.
 * @version 2.0
 * @since version: 2 Date: 29.06.2020.
 */
public class ArgsLambda {

    public static Container build() {
        return new Container();
    }

    public static class Container {
        /**
         * final Properties that it build and fill.
         */
        private final Properties properties = new Properties();

        /**
         * Map contains all expected multi-keys and their validate.
         */
        private final Map<String, List<Predicate<String>>> multiKeyMap = new HashMap<>();

        /**
         * args from console.
         */
        private String[] args;

        /* ####### Settings ####### */

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

        /**
         * add flag.
         *
         * @param flag flag.
         * @return this builder.
         */
        public Container add(String flag) {
            this.properties.setProperty(flag, flag);
            return this;
        }

        /**
         * add normal key.
         *
         * @param key           key.
         * @param paramValidate validate for key's param.
         * @return this builder.
         */
        public Container add(String key, Predicate<String> paramValidate) {
            this.multiKeyMap.put(key, List.of(paramValidate));
            return this;
        }

        /**
         * add multi-key. You need to parse value for use. {@see class javaDoc}
         * OR you can you option {@code runToMap()} - to get
         *
         * @param key             key.
         * @param paramsValidates validates for key's params.
         * @return this builder.
         */
        public Container add(String key, List<Predicate<String>> paramsValidates) {
            this.multiKeyMap.put(key, paramsValidates);
            return this;
        }

        /**
         * Load args from console.
         *
         * @param args args from console.
         * @return this builder.
         */
        public Container load(String[] args) {
            this.args = args;
            return this;
        }

        /**
         * {API load} && {API run} in one string.
         *
         * @param args from console.
         * @return {@code java.util.Properties}
         */
        public Properties loadAndRun(String[] args) {
            this.args = args;
            return run();
        }

        /**
         * Start conversion arguments to Properties. It is final option.
         *
         * @return {@code java.util.Properties}.
         */
        public Properties run() {
            for (int i = 0; i < this.args.length; i++) {
                var currentArg = this.args[i];
                List<Predicate<String>> validateList = this.multiKeyMap.remove(currentArg);

                check:
                if (validateList != null) {
                    if (i < this.args.length - validateList.size()) {
                        var resultParamValue = new StringBuilder();
                        int paramIndex = 1;

                        for (var paramPredicate : validateList) {
                            var expectedKeyParam = this.args[i + paramIndex++];

                            if (paramPredicate.test(expectedKeyParam)) {

                                if (paramIndex == 2) {
                                    resultParamValue.append(expectedKeyParam);
                                } else {
                                    resultParamValue.append("-").append(expectedKeyParam);
                                }
                            } else {
                                if (this.print) {
                                    this.output.accept(showFailValidateMsg(currentArg, expectedKeyParam));
                                }
                                if (!this.continuable) {
                                    break check;
                                }
                            }
                        }
                        this.properties.setProperty(currentArg, resultParamValue.toString());
                    } else {
                        if (this.print) {
                            this.output.accept(showArgsWithoutParams(currentArg));
                        }
                        if (!this.continuable) {
                            break check;
                        }
                    }
                }
            }
            return this.properties;
        }

        /**
         * Start processing arguments and return Map. key = "key" - value = List<Key's param>
         * key    - key
         * value  - List<key's param>
         *
         * @return Map<String, List < String>>
         */
        public Map<String, List<String>> runToMap() {
            run();
            Map<String, List<String>> map = new HashMap<>();
            for (var entry : properties.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    var params = (String) entry.getValue();

                    List<String> tempList = Arrays.asList(params.split("-"));
                    map.put((String) entry.getKey(), tempList);
                }
            }
            return map;
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
        public Container update(Properties firstProperties, Properties secondProperties) {
            for (var tempEntry : secondProperties.entrySet()) {
                var keyParam = firstProperties.getProperty((String) tempEntry.getKey());
                if (keyParam == null) {
                    firstProperties.setProperty(
                            (String) tempEntry.getKey(), (String) tempEntry.getValue());
                }
            }
            return this;
        }

        /* ####### Settings ####### */

        /**
         * Change setting: print all warnings to output.
         * * Default output is: usual console console.
         *
         * @return this builder.
         */
        public Container print() {
            this.print = true;
            return this;
        }

        /**
         * Change setting: print all warnings to output.
         * Change setting: set new output for print {@param output}.
         *
         * @param output -
         * @return this builder.
         */
        public Container print(Consumer<String> output) {
            this.print = true;
            this.output = output;
            return this;
        }

        /**
         * Change setting: continue validate then one or more params fail validate.
         *
         * @return this builder.
         */
        public Container continuable() {
            this.continuable = true;
            return this;
        }

        /* ####### WARNINGS ####### */

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