package dev.necraul.aoc2015.data;

public class Node {
    public String start;
    public String end;
    public int distance;

    public Node(String start, String end, String distance) {
        this.start = start;
        this.end = end;
        this.distance = Integer.parseInt(distance);
    }
}
