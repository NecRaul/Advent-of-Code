package dev.necraul.aoc2015.day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import dev.necraul.aoc2015.interfaces.GenericPuzzle;

public class Day13 implements GenericPuzzle {

    private HashMap<String, HashMap<String, Integer>> happinessMap = new HashMap<>();
    private HashSet<String> peopleSet = new HashSet<>();
    private HashSet<Integer> happinessSet = new HashSet<>();

    public Object solvePart1(List<String> lines) {
        parseLines(lines, false);
        happinessSet = new HashSet<>();
        generatePermutation(new ArrayList<>(peopleSet), 0);
        return happinessSet.stream().max(Integer::compareTo).orElseThrow();
    }

    public Object solvePart2(List<String> lines) {
        parseLines(lines, true);
        happinessSet = new HashSet<>();
        generatePermutation(new ArrayList<>(peopleSet), 0);
        return happinessSet.stream().max(Integer::compareTo).orElseThrow();
    }

    private void parseLines(List<String> lines, boolean extra) {
        for (String line : lines) {
            String[] splits = line.split(" ");
            String name = splits[0];
            String next = splits[10].substring(0, splits[10].length() - 1);
            int happiness = Integer.parseInt(splits[3]) * (splits[2].equals("lose") ? -1 : 1);
            happinessMap.computeIfAbsent(name, k -> new HashMap<>()).put(next, happiness);
            if (extra) {
                peopleSet.add("NecRaul");
                happinessMap.computeIfAbsent("NecRaul", k -> new HashMap<>()).put(name, 0);
                happinessMap.computeIfAbsent(name, k -> new HashMap<>()).put("NecRaul", 0);
            }
            peopleSet.add(name);
        }
    }

    private int calculateHappiness(ArrayList<String> people) {
        int happiness = 0;
        for (int i = 0; i < people.size(); i++) {
            String name = people.get(i);
            String next = people.get((i + 1) % people.size());
            happiness += happinessMap.get(name).get(next) + happinessMap.get(next).get(name);
        }
        return happiness;
    }

    private void generatePermutation(ArrayList<String> people, int index) {
        int happiness = calculateHappiness(people);
        happinessSet.add(happiness);
        for (int i = index; i < people.size(); i++) {
            String temp = people.get(index);
            people.set(index, people.get(i));
            people.set(i, temp);
            generatePermutation(people, index + 1);
            temp = people.get(index);
            people.set(index, people.get(i));
            people.set(i, temp);
        }
    }
}
