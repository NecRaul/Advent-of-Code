package dev.necraul.aoc2015.day;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dev.necraul.aoc2015.interfaces.GenericPuzzle;

public class Day20 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        int number = Integer.parseInt(lines.getFirst());
        int gift = 0;
        for (int i = 1; i <= number / 10; i++) {
            Set<Integer> divisors = findAllDivisors(i);
            gift += 10 * divisors.stream().mapToInt(Integer::intValue).sum();
            if (gift >= number) {
                return i;
            }
            gift = 0;
        }
        return gift;
    }

    public Object solvePart2(List<String> lines) {
        int number = Integer.parseInt(lines.getFirst());
        int gift = 0;
        for (int i = 1; i <= number / 11; i++) {
            int houseNumber = i;
            Set<Integer> divisors = findAllDivisors(i);
            gift += 11 * divisors.stream().filter(d -> houseNumber / d <= 50).mapToInt(Integer::intValue).sum();
            if (gift >= number) {
                return i;
            }
            gift = 0;
        }
        return gift;
    }

    private Set<Integer> findAllDivisors(int number) {
        Set<Integer> divisors = new HashSet<>();
        Set<Integer> copyOfDivisors = new HashSet<>();
        for (int i = 1; i <= Math.sqrt(number) + 1; i++) {
            if (number % i == 0) {
                copyOfDivisors.add(i);
            }
        }
        for (int divisor : copyOfDivisors) {
            if (number != divisor * divisor) {
                divisors.add(divisor);
                divisors.add(number / divisor);
            } else {
                divisors.add(divisor);
            }
        }
        return divisors;
    }
}
