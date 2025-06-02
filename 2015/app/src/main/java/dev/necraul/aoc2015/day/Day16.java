package dev.necraul.aoc2015.day;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dev.necraul.aoc2015.data.Sue;
import dev.necraul.aoc2015.interfaces.GenericPuzzle;

public class Day16 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        List<Sue> sues = new ArrayList<>();
        for (String line : lines) {
            int number = 0;
            int children = 0;
            int cats = 0;
            int samoyeds = 0;
            int pomeranians = 0;
            int akitas = 0;
            int vizslas = 0;
            int goldfish = 0;
            int trees = 0;
            int cars = 0;
            int perfumes = 0;
            String regex = "\\d+";
            Matcher matcher = Pattern.compile(regex).matcher(line);
            if (matcher.find()) {
                number = Integer.parseInt(matcher.group());
            }
            line = line.replaceFirst("^[^ ]+ [^ ]+ ", "");
            String[] parts = line.split(",");
            for (String part : parts) {
                String[] pairs = part.split(": ");
                switch (pairs[0].trim()) {
                    case "children":
                        children = Integer.parseInt(pairs[1]);
                        break;
                    case "cats":
                        cats = Integer.parseInt(pairs[1]);
                        break;
                    case "samoyeds":
                        samoyeds = Integer.parseInt(pairs[1]);
                        break;
                    case "pomeranians":
                        pomeranians = Integer.parseInt(pairs[1]);
                        break;
                    case "akitas":
                        akitas = Integer.parseInt(pairs[1]);
                        break;
                    case "vizslas":
                        vizslas = Integer.parseInt(pairs[1]);
                        break;
                    case "goldfish":
                        goldfish = Integer.parseInt(pairs[1]);
                        break;
                    case "trees":
                        trees = Integer.parseInt(pairs[1]);
                        break;
                    case "cars":
                        cars = Integer.parseInt(pairs[1]);
                        break;
                    case "perfumes":
                        perfumes = Integer.parseInt(pairs[1]);
                        break;
                    default:
                        break;
                }
            }
            if (children != 3 && children != 0) {
                continue;
            }
            if (cats != 7 && cats != 0) {
                continue;
            }
            if (samoyeds != 2 && samoyeds != 0) {
                continue;
            }
            if (pomeranians != 3 && pomeranians != 0) {
                continue;
            }
            if (akitas != 0) {
                continue;
            }
            if (vizslas != 0) {
                continue;
            }
            if (goldfish != 5 && goldfish != 0) {
                continue;
            }
            if (trees != 3 && trees != 0) {
                continue;
            }
            if (cars != 2 && cars != 0) {
                continue;
            }
            if (perfumes != 1 && perfumes != 0) {
                continue;
            }
            Sue sue = new Sue(number, children, cats, samoyeds, pomeranians, akitas, vizslas, goldfish, trees, cars,
                    perfumes);
            sues.add(sue);
        }
        return sues.getLast().number;
    }

    public Object solvePart2(List<String> lines) {
        List<Sue> sues = new ArrayList<>();
        for (String line : lines) {
            int number = 0;
            int children = 0;
            int cats = 0;
            int samoyeds = 0;
            int pomeranians = 0;
            int akitas = 0;
            int vizslas = 0;
            int goldfish = 0;
            int trees = 0;
            int cars = 0;
            int perfumes = 0;
            String regex = "\\d+";
            Matcher matcher = Pattern.compile(regex).matcher(line);
            if (matcher.find()) {
                number = Integer.parseInt(matcher.group());
            }
            line = line.replaceFirst("^[^ ]+ [^ ]+ ", "");
            String[] parts = line.split(",");
            for (String part : parts) {
                String[] pairs = part.split(": ");
                switch (pairs[0].trim()) {
                    case "children":
                        children = Integer.parseInt(pairs[1]);
                        break;
                    case "cats":
                        cats = Integer.parseInt(pairs[1]);
                        break;
                    case "samoyeds":
                        samoyeds = Integer.parseInt(pairs[1]);
                        break;
                    case "pomeranians":
                        pomeranians = Integer.parseInt(pairs[1]);
                        break;
                    case "akitas":
                        akitas = Integer.parseInt(pairs[1]);
                        break;
                    case "vizslas":
                        vizslas = Integer.parseInt(pairs[1]);
                        break;
                    case "goldfish":
                        goldfish = Integer.parseInt(pairs[1]);
                        break;
                    case "trees":
                        trees = Integer.parseInt(pairs[1]);
                        break;
                    case "cars":
                        cars = Integer.parseInt(pairs[1]);
                        break;
                    case "perfumes":
                        perfumes = Integer.parseInt(pairs[1]);
                        break;
                    default:
                        break;
                }
            }
            if (children != 3 && children != 0) {
                continue;
            }
            if (cats <= 7 && cats != 0) {
                continue;
            }
            if (samoyeds != 2 && samoyeds != 0) {
                continue;
            }
            if (pomeranians >= 3 && pomeranians != 0) {
                continue;
            }
            if (akitas != 0) {
                continue;
            }
            if (vizslas != 0) {
                continue;
            }
            if (goldfish >= 5 && goldfish != 0) {
                continue;
            }
            if (trees <= 3 && trees != 0) {
                continue;
            }
            if (cars != 2 && cars != 0) {
                continue;
            }
            if (perfumes != 1 && perfumes != 0) {
                continue;
            }
            Sue sue = new Sue(number, children, cats, samoyeds, pomeranians, akitas, vizslas, goldfish, trees, cars,
                    perfumes);
            sues.add(sue);
        }
        return sues.get(1).number;
    }
}
