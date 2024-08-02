package com.github.javarar.lucky.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class LuckyTicketTest {

    @DisplayName("Задание 7. Счастливый билет")
    @ParameterizedTest
    @MethodSource("cases")
    public void luckyTicketProbabilityTest(int serialNumberLength) {

        var moscow = new MscLuckyTicket().isLucky(String.valueOf(serialNumberLength));
        System.out.println("moscow tickets probability " + moscow);
        var spb = new SpbLuckyTicket().isLucky(String.valueOf(serialNumberLength));
        System.out.println("spb tickets probability " + spb);
    }

    private static Stream<Arguments> cases() {
        return Stream.of(111111, 323323, 139423, 757232, 3243, 5454).map(Arguments::of);
    }
}