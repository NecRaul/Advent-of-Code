package aoc2015.day;

import java.util.Arrays;
import java.util.List;

import aoc2015.interfaces.GenericPuzzle;

public class Day17 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        int cap = 150;
        Integer[] bottles = new Integer[lines.size()];
        for (int i = 0; i < bottles.length; i++) {
            bottles[i] = Integer.parseInt(lines.get(i));
        }
        int answer = recurseOne(cap, bottles);
        return answer;
    }

    public Object solvePart2(List<String> lines) {
        int cap = 150;
        Integer[] bottles = new Integer[lines.size()];
        for (int i = 0; i < bottles.length; i++) {
            bottles[i] = Integer.parseInt(lines.get(i));
        }
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            int answer = recurseTwo(cap, bottles, 0, i);
            if (answer != 0) {
                return answer;
            }
        }
        return 0;
    }

    private int recurseOne(int cap, Integer bottles[]) {
        if (cap == 0) {
            return 1;
        }
        if (cap < 0 || bottles.length == 0) {
            return 0;
        }
        int first = bottles[0];
        Integer[] rest = Arrays.copyOfRange(bottles, 1, bottles.length);
        return recurseOne(cap - first, rest) + recurseOne(cap, rest);
    }

    private int recurseTwo(int cap, Integer bottles[], int usedContainers, int minContainers) {
        if (cap == 0 && usedContainers == minContainers) {
            return 1;
        }
        if (cap < 0 || bottles.length == 0) {
            return 0;
        }
        int first = bottles[0];
        Integer[] rest = Arrays.copyOfRange(bottles, 1, bottles.length);
        return recurseTwo(cap - first, rest, usedContainers + 1, minContainers)
                + recurseTwo(cap, rest, usedContainers, minContainers);
    }
}
