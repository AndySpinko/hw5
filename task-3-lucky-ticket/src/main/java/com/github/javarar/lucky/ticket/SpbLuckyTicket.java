package com.github.javarar.lucky.ticket;

public class SpbLuckyTicket {

    static boolean isLucky(String number) {
            var half = number.length() / 2;
            var firstHalf = sum(number.substring(0, half));
            var secondHalf = sum(number.substring(half));
            return firstHalf == secondHalf;
        }

    private static int sum(String number) {
        var num = Integer.parseInt(number);
        var sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
