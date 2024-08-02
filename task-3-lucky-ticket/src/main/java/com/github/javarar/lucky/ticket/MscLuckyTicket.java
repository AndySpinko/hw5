package com.github.javarar.lucky.ticket;

import java.util.function.Predicate;

public class MscLuckyTicket {

    static boolean isLucky(String number) {
        var array = number.split("");

        final Predicate<Integer> evenPredicate = i -> (i & 1) == 0; // четное
        final Predicate<Integer> oddPredicate = i -> (i & 1) != 0; // нечетное

        int length = array.length;
        int[] result = new int[length / 2];
        int[] result2 = new int[length / 2];
        int splitedIndex = 0;

        extract(array, evenPredicate, length, result, splitedIndex);
        extract(array, oddPredicate, length, result2, splitedIndex);

        return sum(result) == sum(result2);
    }

    private static int[] extract(String[] array, Predicate<Integer> predicate, int length, int[] result, int splitedIndex) {
        for (int index = 0; index < length; index++) {
            if (predicate.test(index)) {
                result[splitedIndex] = Integer.parseInt(array[index]);
                splitedIndex++;

                if (splitedIndex == length / 2) {
                    break;
                }
            }
        }
        return result;
    }

    private static int sum(int[] result) {
        int sum = 0;
        for (int i = 0; i < result.length; i++) {
            sum = sum + result[i];
        }
        return sum;
    }
}
