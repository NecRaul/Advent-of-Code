package aoc2015.day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import aoc2015.data.Node;
import aoc2015.interfaces.GenericPuzzle;

public class Day09 implements GenericPuzzle {

    private HashSet<String> citySet = new HashSet<String>();
    private HashSet<Integer> distances = new HashSet<Integer>();
    private List<Node> nodes = new ArrayList<>();

    public Object solvePart1(List<String> lines) {
        parseLines(lines);
        ArrayList<String> cities = new ArrayList<>(citySet);
        generatePermunation(cities, 0);
        return distances.stream().min(Integer::compareTo).orElseThrow();
    }

    public Object solvePart2(List<String> lines) {
        parseLines(lines);
        ArrayList<String> cities = new ArrayList<>(citySet);
        generatePermunation(cities, 0);
        return distances.stream().max(Integer::compareTo).orElseThrow();
    }

    private void parseLines(List<String> lines) {
        for (String line : lines) {
            String[] splits = line.split(" ");
            Node node = new Node(splits[0], splits[2], splits[4]);
            nodes.add(node);
            citySet.add(node.start);
            node = new Node(splits[2], splits[0], splits[4]);
            nodes.add(node);
            citySet.add(node.start);
        }
    }

    private int calculateDistance(ArrayList<String> cities) {
        int distance = 0;
        for (int i = 0; i < cities.size() - 1; i++) {
            String currentCity = cities.get(i);
            String nextCity = cities.get(i + 1);
            distance += nodes.stream().filter(node -> node.start.equals(currentCity) && node.end.equals(nextCity))
                    .findFirst().map(node -> node.distance).orElse(0);
        }
        return distance;
    }

    private void generatePermunation(ArrayList<String> cities, int index) {
        int distance = calculateDistance(cities);
        distances.add(distance);
        int size = cities.size();
        if (index == size - 1) {
            return;
        }
        for (int i = index; i < size; i++) {
            String temp = cities.get(index);
            cities.set(index, cities.get(i));
            cities.set(i, temp);
            generatePermunation(cities, index + 1);
            temp = cities.get(index);
            cities.set(index, cities.get(i));
            cities.set(i, temp);
        }
    }
}
