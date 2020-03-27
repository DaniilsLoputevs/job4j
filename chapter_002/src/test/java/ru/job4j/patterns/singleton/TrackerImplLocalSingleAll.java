package ru.job4j.patterns.singleton;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerImplLocalSingleAll {

    @Test // V1 - 1. enum.
    public void v1() {
        TrackerSingle trackerSingle = TrackerSingle.INSTANCE; // Первый раз
        TrackerSingle test = TrackerSingle.INSTANCE; // Второй раз
        assertThat(trackerSingle, is(test)); // Сравнение
    }
    @Test // V2 - static field. Lazy loading.
    public void v2() {
        TrackerSingleV2 trackerSingleV2 = TrackerSingleV2.getInstance(); // Первый раз
        TrackerSingleV2 test = TrackerSingleV2.getInstance(); // Второй раз
        assertThat(trackerSingleV2, is(test)); // Сравнение
    }
    @Test
    public void v3() {
        TrackerSingleV3 trackerSingleV3 = TrackerSingleV3.getInstance(); // Первый раз
        TrackerSingleV3 test = TrackerSingleV3.getInstance(); // Второй раз
        assertThat(trackerSingleV3, is(test)); // Сравнение
    }
    @Test
    public void v4() {
        TrackerSingleV4 trackerSingleV4 = TrackerSingleV4.getInstance(); // Первый раз
        TrackerSingleV4 test = TrackerSingleV4.getInstance(); // Второй раз
        assertThat(trackerSingleV4, is(test)); // Сравнение
    }


}
