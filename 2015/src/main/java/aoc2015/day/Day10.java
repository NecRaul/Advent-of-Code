package aoc2015.day;

import java.util.List;

import aoc2015.interfaces.GenericPuzzle;

public class Day10 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        for (String line : lines) {
            char[] characterArray = line.toCharArray();
            int count = 0;
            while (count < 40) {
                StringBuilder result = new StringBuilder();
                char currentCharacter = characterArray[0];
                int currentLength = 0;
                for (int i = 0; i < characterArray.length; i++) {
                    if (currentCharacter == characterArray[i]) {
                        currentLength++;
                    } else {
                        result.append(currentLength);
                        result.append(currentCharacter);
                        currentLength = 1;
                        currentCharacter = characterArray[i];
                    }
                }
                result.append(currentLength);
                result.append(currentCharacter);
                characterArray = result.toString().toCharArray();
                count++;
            }
            return characterArray.length;
        }
        return 0;
    }

    public Object solvePart2(List<String> lines) {
        for (String line : lines) {
            char[] characterArray = line.toCharArray();
            int count = 0;
            while (count < 50) {
                StringBuilder result = new StringBuilder();
                char currentCharacter = characterArray[0];
                int currentLength = 0;
                for (int i = 0; i < characterArray.length; i++) {
                    if (currentCharacter == characterArray[i]) {
                        currentLength++;
                    } else {
                        result.append(currentLength);
                        result.append(currentCharacter);
                        currentLength = 1;
                        currentCharacter = characterArray[i];
                    }
                }
                result.append(currentLength);
                result.append(currentCharacter);
                characterArray = result.toString().toCharArray();
                count++;
            }
            return characterArray.length;
        }
        return 0;
    }
}
