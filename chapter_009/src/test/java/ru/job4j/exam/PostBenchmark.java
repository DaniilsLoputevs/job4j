package ru.job4j.exam;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class PostBenchmark {
    private static final HashMap<String, Set<String>> DATA = new HashMap<>();
    private static final Post POST = new Post();

    @Param({"100", "1000"})
    public int size;

    private static void initBenchmark() {
        DATA.put("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        DATA.put("user2", Set.of("foo@gmail.com", "ups@pisem.net"));
        DATA.put("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));
        DATA.put("user4", Set.of("ups@pisem.net", "aaa@bbb.ru"));
        DATA.put("user5", Set.of("kappa@gmail.notCom", "cs16@old.fag"));
        DATA.put("user6", Set.of("cs16@old.fag", "kappa@gmail.notCom"));
        DATA.put("user7", Set.of("Pert_III@carj.rus.velikij", "kappa@gmail.notCom"));
        DATA.put("user8", Set.of("cs16@old.fag", "Einstein@mars.net"));
        DATA.put("user9", Set.of("Knjaz_Galicin@mars.net"));
        DATA.put("user10", Set.of("LastOfUs2@gargabe.lol", "Knjaz_Galicin@mars.net"));
    }

    @Benchmark
    public static void benchMark() {
        POST.sotUsers(DATA);
    }

    public static void main(String[] args) throws RunnerException {
        initBenchmark();
        Options opt = new OptionsBuilder()
                .include(PostTest.class.getSimpleName())
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupIterations(3)
                .measurementIterations(5) //                    1k      10k
                .param("size", "100", "200", "300", "1000", "10000")
                .forks(1)
                .mode(Mode.AverageTime)
                .build();

        new Runner(opt).run();
    }
}
