package com.github.javarar.lucky.ticket;

import java.util.List;
import java.util.stream.LongStream;

public class LuckyTicket {

    private final static int SERIAL_NUMBER_LENGTH = 8;

    public static void main(String[] args) {
        luckyTicketProbability(SERIAL_NUMBER_LENGTH);
    }

    public static void luckyTicketProbability(int serialNumberLength) {

        long allNumbers = (long) Math.pow(10, SERIAL_NUMBER_LENGTH)  - 1;
        List<Long> numbers = LongStream.rangeClosed(1L, allNumbers).boxed().toList();

        var luckyMscCount = numbers.stream()
                .map(s -> String.format("%0" + serialNumberLength + "d", s))
                .parallel()
                .filter(MscLuckyTicket::isLucky)
                .toList();

        var luckySpbCount = numbers.stream()
                .map(s -> String.format("%0" + serialNumberLength + "d", s))
                .parallel()
                .filter(SpbLuckyTicket::isLucky)
                .toList();

        System.out.printf("Вероятность выпадения счастливого Московского билета %.3f %n", luckyMscCount.size() / (double) allNumbers);
        System.out.println("Количество найденных билетов " + luckyMscCount.size());

        System.out.printf("Вероятность выпадения счастливого Ленинградского билета %.3f %n", luckySpbCount.size() / (double) allNumbers);
        System.out.println("Количество найденных билетов " + luckySpbCount.size());
    }
}