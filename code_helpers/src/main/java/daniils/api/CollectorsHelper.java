package daniils.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CollectorsHelper {
    public static <K, V> Map<K, V> toMap(List<V> values, Function<V, K> keyFromNextValue) {
        Map<K, V> rsl = new HashMap<>();
        values.forEach(value -> rsl.put(keyFromNextValue.apply(value), value));
        return rsl;
    }

    /**
     *
     * @param values - init data, that going to map.
     * @param nextKey - how to get next key.
     * @param nextVal - how to get next key.
     * @param <K> - key class Type
     * @param <V> - val class Type
     * @param <D> - List generic class Type - data type.
     * @return Map<K, V>
     */
    public static <K, V, D> Map<K, V> toMap(List<D> values,
                                            Function<D, K> nextKey,
                                            Function<D, V> nextVal ) {
        Map<K, V> rsl = new HashMap<>();
        values.forEach(data -> rsl.put(nextKey.apply(data), nextVal.apply(data)));
        return rsl;
    }
}
