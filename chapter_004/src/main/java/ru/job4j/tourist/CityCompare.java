package ru.job4j.tourist;

import java.util.Comparator;

public class CityCompare implements Comparator<Address> {
    @Override
    public int compare(Address o1, Address o2) {
        String leftCity = o1.getCity();
        String rightCity = o2.getCity();
        int result = 0;
        int minLength = Math.min(leftCity.length(), rightCity.length());

        for (int i = 0; i < minLength; i++) {
            result = leftCity.charAt(i) - rightCity.charAt(i);

            if (result != 0) {
                break;
            }

        }
        return result;

    }
}