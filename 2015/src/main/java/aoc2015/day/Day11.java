package aoc2015.day;

import java.util.List;

import aoc2015.interfaces.GenericPuzzle;
import aoc2015.data.Alphabet;

public class Day11 implements GenericPuzzle {

    private String firstPassword = "";
    private String secondPassword = "";

    public Object solvePart1(List<String> lines) {
        for (String line : lines) {
            int[] passwordEnum = line.chars().map(c -> Alphabet.valueOf(String.valueOf((char) c)).ordinal()).toArray();
            while (!checkValidity(passwordEnum)) {
                int i = passwordEnum.length - 1;
                while (i >= 0 && passwordEnum[i] == 26) {
                    passwordEnum[i--] = 1;
                }
                if (i >= 0) {
                    passwordEnum[i]++;
                }
            }
            firstPassword = constructPassword(passwordEnum);
        }
        return firstPassword;
    }

    public Object solvePart2(List<String> lines) {
        for (String line : lines) {
            line = firstPassword;
            int[] passwordEnum = line.chars().map(c -> Alphabet.valueOf(String.valueOf((char) c)).ordinal()).toArray();
            while (!checkValidity(passwordEnum)) {
                int i = passwordEnum.length - 1;
                while (i >= 0 && passwordEnum[i] == 26) {
                    passwordEnum[i--] = 1;
                }
                if (i >= 0) {
                    passwordEnum[i]++;
                }
            }
            secondPassword = constructPassword(passwordEnum);
        }
        return secondPassword;
    }

    private String constructPassword(int[] passwordEnum) {
        StringBuilder result = new StringBuilder();
        var alphabet = Alphabet.values();
        for (int digit : passwordEnum) {
            result.append(alphabet[digit]);
        }
        return result.toString();
    }

    private boolean checkValidity(int[] passwordEnum) {
        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = true;
        for (int i = 0; i < passwordEnum.length - 2; i++) {
            if (passwordEnum[i] + 1 == passwordEnum[i + 1] && passwordEnum[i + 1] + 1 == passwordEnum[i + 2]) {
                check1 = true;
            }
        }
        for (int i = 0, pairCount = 0; i < passwordEnum.length - 1; i++) {
            if (passwordEnum[i] == passwordEnum[i + 1]) {
                pairCount++;
                i++;
                if (pairCount >= 2) {
                    check2 = true;
                }
            }
        }
        for (int i = 0; i < passwordEnum.length; i++) {
            if (passwordEnum[i] == 9 || passwordEnum[i] == 12 || passwordEnum[i] == 15) {
                check3 = false;
            }
        }
        if (check1 && check2 && check3 && !firstPassword.equals(constructPassword(passwordEnum))) {
            return true;
        }
        return false;
    }
}
