package aoc2015.day;

import java.util.List;

import aoc2015.interfaces.GenericPuzzle;

public class Day02 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        int area = 0;
        for (String line : lines) {
            String[] dimensions = line.split("x");
            int length = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);
            int height = Integer.parseInt(dimensions[2]);
            int area1 = length * width;
            int area2 = width * height;
            int area3 = height * length;
            area += 2 * (area1 + area2 + area3) + Math.min(Math.min(area1, area2), area3);
        }
        return area;
    }

    public Object solvePart2(List<String> lines) {
        int perimeter = 0;
        int ribbon = 0;
        for (String line : lines) {
            String[] dimensions = line.split("x");
            int length = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);
            int height = Integer.parseInt(dimensions[2]);
            int smallest = Math.min(Math.min(length, width), height);
            int largest = Math.max(Math.max(length, width), height);
            int middle = length + width + height - largest - smallest;
            perimeter += 2 * (smallest + middle);
            ribbon += length * width * height;
        }
        return perimeter + ribbon;
    }
}
