package dev.necraul.aoc2015.day;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dev.necraul.aoc2015.interfaces.GenericPuzzle;

public class Day03 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        int x = 0;
        int y = 0;
        Set<String> coordinates = new HashSet<>();
        coordinates.add(x + "," + y);
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                switch (line.charAt(i)) {
                    case '^':
                        y++;
                        break;
                    case 'v':
                        y--;
                        break;
                    case '>':
                        x++;
                        break;
                    case '<':
                        x--;
                        break;
                    default:
                        break;
                }
                coordinates.add(x + "," + y);
            }
        }
        return coordinates.size();
    }

    public Object solvePart2(List<String> lines) {
        int x = 0;
        int y = 0;
        int santaX = 0;
        int santaY = 0;
        int roboSantaX = 0;
        int roboSantaY = 0;
        Set<String> coordinates = new HashSet<>();
        coordinates.add(x + "," + y);
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                if (i % 2 == 0) {
                    x = santaX;
                    y = santaY;
                } else {
                    x = roboSantaX;
                    y = roboSantaY;
                }
                switch (line.charAt(i)) {
                    case '^':
                        y++;
                        break;
                    case 'v':
                        y--;
                        break;
                    case '>':
                        x++;
                        break;
                    case '<':
                        x--;
                        break;
                    default:
                        break;
                }
                if (i % 2 == 0) {
                    santaX = x;
                    santaY = y;
                    coordinates.add(santaX + "," + santaY);
                } else {
                    roboSantaX = x;
                    roboSantaY = y;
                    coordinates.add(roboSantaX + "," + roboSantaY);
                }
            }
        }
        return coordinates.size();
    }
}
