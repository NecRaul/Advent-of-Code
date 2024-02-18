package aoc2015.day;

import java.util.Arrays;
import java.util.List;

import aoc2015.interfaces.GenericPuzzle;

public class Day18 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        boolean[][] booleanArray = new boolean[102][102];
        boolean[][] updatedArray = new boolean[102][102];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                Character booleanCharacter = line.charAt(j);
                booleanArray[i + 1][j + 1] = (booleanCharacter == '#') ? true : false;
            }
        }
        updatedArray = Arrays.stream(booleanArray).map(boolean[]::clone).toArray(boolean[][]::new);
        for (int iter = 0; iter < 100; iter++) {
            for (int i = 1; i < booleanArray.length - 1; i++) {
                for (int j = 1; j < booleanArray.length - 1; j++) {
                    int count = 0;
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if ((k != 0 || l != 0) && booleanArray[i + k][j + l]) {
                                count++;
                            }
                        }
                    }
                    if (booleanArray[i][j] && !(count == 2 || count == 3)) {
                        updatedArray[i][j] = false;
                    } else if (!booleanArray[i][j] && count == 3) {
                        updatedArray[i][j] = true;
                    }
                }
            }
            booleanArray = Arrays.stream(updatedArray).map(boolean[]::clone).toArray(boolean[][]::new);
        }
        int count = 0;
        for (int i = 0; i < booleanArray.length; i++) {
            for (int j = 0; j < booleanArray.length; j++) {
                if (booleanArray[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public Object solvePart2(List<String> lines) {
        boolean[][] booleanArray = new boolean[102][102];
        boolean[][] updatedArray = new boolean[102][102];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                Character booleanCharacter = line.charAt(j);
                booleanArray[i + 1][j + 1] = (booleanCharacter == '#') ? true : false;
            }
        }
        booleanArray[1][1] = true;
        booleanArray[1][100] = true;
        booleanArray[100][1] = true;
        booleanArray[100][100] = true;
        updatedArray = Arrays.stream(booleanArray).map(boolean[]::clone).toArray(boolean[][]::new);
        for (int iter = 0; iter < 100; iter++) {
            for (int i = 1; i < booleanArray.length - 1; i++) {
                for (int j = 1; j < booleanArray.length - 1; j++) {
                    int count = 0;
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if ((k != 0 || l != 0) && booleanArray[i + k][j + l]) {
                                count++;
                            }
                        }
                    }
                    if (booleanArray[i][j] && !(count == 2 || count == 3)) {
                        updatedArray[i][j] = false;
                    } else if (!booleanArray[i][j] && count == 3) {
                        updatedArray[i][j] = true;
                    }
                }
            }
            updatedArray[1][1] = true;
            updatedArray[1][100] = true;
            updatedArray[100][1] = true;
            updatedArray[100][100] = true;
            booleanArray = Arrays.stream(updatedArray).map(boolean[]::clone).toArray(boolean[][]::new);
        }
        int count = 0;
        for (int i = 0; i < booleanArray.length; i++) {
            for (int j = 0; j < booleanArray.length; j++) {
                if (booleanArray[i][j]) {
                    count++;
                }

            }
        }
        return count;
    }
}
