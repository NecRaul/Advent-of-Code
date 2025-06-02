package dev.necraul.aoc2015.day;

import java.util.List;

import dev.necraul.aoc2015.interfaces.GenericPuzzle;

public class Day08 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        int overallLength = 0;
        int shortLength = 0;
        for (String line : lines) {
            overallLength += line.length();
            for (int i = 1; i < line.length() - 1; i++) {
                char currentChar = line.charAt(i);
                if (currentChar == '\\') {
                    char nextChar = line.charAt(i + 1);
                    if (nextChar == '\\' || nextChar == '\"') {
                        shortLength++;
                        i++;
                    } else if (nextChar == 'x') {
                        shortLength++;
                        if (checkForHex(line.charAt(i + 2)) && checkForHex(line.charAt(i + 3))) {
                            i += 3;
                        }
                    }
                } else {
                    shortLength++;
                }
            }
        }
        return overallLength - shortLength;
    }

    public Object solvePart2(List<String> lines) {
        int longLength = 2 * lines.size();
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                char currentChar = line.charAt(i);
                switch (currentChar) {
                    case '\\':
                    case '\"':
                        longLength++;
                        break;
                    default:
                        break;
                }
            }
        }
        return longLength;
    }

    private boolean checkForHex(char hex) {
        switch (hex) {
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
                return true;
            default:
                break;
        }
        if (Character.isDigit(hex)) {
            return true;
        }
        return false;
    }
}
