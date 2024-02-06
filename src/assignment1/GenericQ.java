package assignment1;

import java.util.Arrays;

public class GenericQ {

    public static void main(String[] args) {
        Integer[] integers = {10, 14, 15, 514, 10, 15, 14, 99, 0, 99,1};
        String[] strings = {"ThaiTran", "ThaiTran", "marc", "abc", "marc", "Theo", "Theo", "hello", "world", "dev", "abc", "wworld"};


        System.out.println(Arrays.toString(getUnique(integers)));
        System.out.println(Arrays.toString(getUnique(strings)));

        System.out.println(getAverage(integers));

        System.out.println(getLargest(strings));
    }


    private static <T> T[] getUnique(T[] array) {
        return (T[]) Arrays.stream(array).distinct().toArray();
    }

    private static <T extends Number> double getAverage(T[] numbers) {
        double total = 0.0;
        double time = 0;
        for (T i : numbers) {
            total += i.doubleValue();
            time++;
        }
        return total / time;
    }

    private static <T extends Comparable<T>> T getLargest(T[] array) {
        return Arrays.stream(array).max(T::compareTo).get();
    }

}
