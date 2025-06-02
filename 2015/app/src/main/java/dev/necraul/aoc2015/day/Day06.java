package dev.necraul.aoc2015.day;

import java.util.List;

import dev.necraul.aoc2015.interfaces.GenericPuzzle;

public class Day06 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        int lights = 0;
        boolean[][] coordinates = new boolean[1000][1000];
        for (String line : lines) {
            String[] parts = line.split("\\D+", 5);
            int startX = Integer.parseInt(parts[1]);
            int startY = Integer.parseInt(parts[2]);
            int endX = Integer.parseInt(parts[3]);
            int endY = Integer.parseInt(parts[4]);
            if (line.contains("on")) {
                for (int i = startX; i <= endX; i++) {
                    for (int j = startY; j <= endY; j++) {
                        coordinates[i][j] = true;
                    }
                }
            } else if (line.contains("off")) {
                for (int i = startX; i <= endX; i++) {
                    for (int j = startY; j <= endY; j++) {
                        coordinates[i][j] = false;
                    }
                }
            } else if (line.contains("toggle")) {
                for (int i = startX; i <= endX; i++) {
                    for (int j = startY; j <= endY; j++) {
                        coordinates[i][j] = !coordinates[i][j];
                    }
                }
            }
        }
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (coordinates[i][j]) {
                    lights++;
                }
            }
        }
        return lights;
    }

    public Object solvePart2(List<String> lines) {
        int brightness = 0;
        int[][] coordinates = new int[1000][1000];
        for (String line : lines) {
            String[] parts = line.split("\\D+", 5);
            int startX = Integer.parseInt(parts[1]);
            int startY = Integer.parseInt(parts[2]);
            int endX = Integer.parseInt(parts[3]);
            int endY = Integer.parseInt(parts[4]);
            if (line.contains("on")) {
                for (int i = startX; i <= endX; i++) {
                    for (int j = startY; j <= endY; j++) {
                        coordinates[i][j]++;
                    }
                }
            } else if (line.contains("off")) {
                for (int i = startX; i <= endX; i++) {
                    for (int j = startY; j <= endY; j++) {
                        if (coordinates[i][j] > 0)
                            coordinates[i][j]--;
                    }
                }
            } else if (line.contains("toggle")) {
                for (int i = startX; i <= endX; i++) {
                    for (int j = startY; j <= endY; j++) {
                        coordinates[i][j] += 2;
                    }
                }
            }
        }
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                brightness += coordinates[i][j];
            }
        }
        return brightness;
    }
}
