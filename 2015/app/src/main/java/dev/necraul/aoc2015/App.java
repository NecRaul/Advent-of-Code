package dev.necraul.aoc2015;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import dev.necraul.aoc2015.day.*;
import dev.necraul.aoc2015.interfaces.GenericPuzzle;
import dev.necraul.aoc2015.util.ReadInput;

public class App {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java App [day]");
            return;
        }

        new Day01();
        new Day02();
        new Day03();
        new Day04();
        new Day05();
        new Day06();
        new Day07();
        new Day08();
        new Day09();
        new Day10();
        new Day11();
        new Day12();
        new Day13();
        new Day14();
        new Day15();
        new Day16();
        new Day17();
        new Day18();
        new Day19();
        new Day20();
        new Day21();
        new Day22();
        new Day23();
        new Day24();
        new Day25();

        try {
            int dayNumber = Integer.parseInt(args[0]);
            String inputFilePath = "input/Day" + String.format("%02d", dayNumber) + ".txt";
            String className = "dev.necraul.aoc2015.day.Day" + String.format("%02d", dayNumber);
            Class<?> dayClass = Class.forName(className);
            GenericPuzzle day = (GenericPuzzle) dayClass.getDeclaredConstructor().newInstance();
            List<String> lines = new ReadInput().readLines(inputFilePath);
            Object part1Result = day.solvePart1(lines);
            Object part2Result = day.solvePart2(lines);
            System.out.println("Day " + dayNumber + " - Part 1: " + part1Result);
            System.out.println("Day " + dayNumber + " - Part 2: " + part2Result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid day format: " + args[0]);
        } catch (IOException e) {
            System.out.println("Error reading input for Day " + args[0] + ": " + e.getMessage());
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            throw new RuntimeException(
                    "Error instantiating Day" + String.format("%02d", args[0]) + " class: " + e.getMessage(), e);
        }
    }
}