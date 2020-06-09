package ru.job4j.gc;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;

public class MemoryUsage {
    static class User {
        String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize");
        }

        @Override
        public String toString() {
            return "User{" + "name='" + name + '\'' + '}';
        }
    }

    public static void main(String[] args) {
        // для перегрузки памяти, нужно запускать с VM option: -Xmx4m
        System.out.println("### START ###");
        info();

        memOf(new Object());
        memOf(null);
        memOf(new User("test"));
        class Empty {
        }
        memOf(new Empty());

        User user = null;
        for (int i = 0; i < 10000; i++) {
            user = new User("test");
        }
//        System.out.println(user);
//        user = null;
//        System.gc();


        System.out.println();
        info();
        System.out.println("### FINISH ###");
    }

    /**
     * method throw Warnings - com.carrotsearch.sizeof.RamUsageEstimator.sizeOf():
     * WARNING: An illegal reflective access operation has occurred
     * WARNING: Illegal reflective access by com.carrotsearch.sizeof.RamUsageEstimator (file:/C:/Users/Admin
     * /.m2/repository/com/carrotsearch/java-sizeof/0.0.5/java-sizeof-0.0.5.jar) to field java.lang.String.value
     * WARNING: Please consider reporting this to the maintainers of com.carrotsearch.sizeof.RamUsageEstimator
     * WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
     * WARNING: All illegal access operations will be denied in a future release
     *
     * @param object obj for size.
     */
    public static void memOf(Object object) {
        if (object != null) {
            System.out.println("Mem of: " + object.toString() + " is: " + sizeOf(object) + " bytes");
        } else {
            System.out.println("Mem of: null = 0 bytes");
        }
    }

    public static void info() {
        int mb = 1024 * 1024;

        // Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] #####");

        // Print used memory
        System.out.println("Used memory: "
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        // Print free memory
        System.out.println("Free memory: " + runtime.freeMemory() / mb);

        // Print total available memory
        System.out.println("Total available memory: " + runtime.totalMemory() / mb);

        // Print Maximum available memory
        System.out.println("Max memory: " + runtime.maxMemory() / mb);

        // Print new line
        System.out.println();
    }
}
