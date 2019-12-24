package ru.job4j.tourist;

import java.util.List;
import java.util.stream.Collectors;

public class Profile {
    private Address address;

    public Profile(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    // 1. Создать класс Profile и метод List<Address> collect(List<Profile> profiles);
    // 2. При решении задания нужно использовать Stream API, метод map, метод collect(Collections.toList());
    // 3. Метод Stream.map - принимает элемент потока и возвращает другой элемент.

    public static List<Address> collect(List<Profile> profiles) {
        List<Address> result = profiles.stream().map(
                Profile::getAddress
        ).collect(Collectors.toList());
        return result.stream().distinct().collect(Collectors.toList());
    }
}