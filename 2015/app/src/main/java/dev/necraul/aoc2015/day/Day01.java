package dev.necraul.aoc2015.day;

import java.util.List;

import dev.necraul.aoc2015.interfaces.GenericPuzzle;

public class Day01 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        int floorNumber = 0;
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '(') {
                    floorNumber++;
                } else if (line.charAt(i) == ')') {
                    floorNumber--;
                }
            }
        }
        return floorNumber;
    }

    public Object solvePart2(List<String> lines) {
        int floorNumber = 0;
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '(') {
                    floorNumber++;
                } else if (line.charAt(i) == ')') {
                    floorNumber--;
                }
                if (floorNumber == -1) {
                    return i + 1;
                }
            }
        }
        return floorNumber;
    }
}
