package dev.necraul.aoc2015.day;

import java.util.List;

import dev.necraul.aoc2015.interfaces.GenericPuzzle;

public class Day25 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        long firstCode = 20151125;
        int row = 0;
        int column = 0;
        for (String line : lines) {
            String numbers = line.replaceAll("[^0-9]", "");
            row = Integer.parseInt(numbers.substring(0, 4));
            column = Integer.parseInt(numbers.substring(4, 8));
        }
        long codeCount = getCodeCount(row, column);
        long curCode = firstCode;
        for (int i = 1; i < codeCount; i++) {
            curCode = nextCode(curCode);
        }
        return curCode;
    }

    public Object solvePart2(List<String> lines) {
        return "It's ogre";
    }

    private long nextCode(long curCode) {
        return (curCode * 252533) % 33554393;
    }

    private long getCodeCount(int row, int column) {
        return (row + column - 2) * (row + column - 1) / 2 + column;
    }
}
