package aoc2015.day;

import java.util.HashMap;
import java.util.List;

import aoc2015.interfaces.GenericPuzzle;

public class Day07 implements GenericPuzzle {

    private HashMap<String, String[]> instructions = new HashMap<>();
    private int valueA;

    public Object solvePart1(List<String> lines) {
        for (String line : lines) {
            String[] instructionArray = line.split(" ");
            String key = instructionArray[instructionArray.length - 1];
            instructions.put(key, instructionArray);
        }
        int value = evaluateKey("a");
        valueA = value;
        return value;
    }

    public Object solvePart2(List<String> lines) {
        instructions.clear();
        for (String line : lines) {
            String[] instructionArray = line.split(" ");
            String key = instructionArray[instructionArray.length - 1];
            instructions.put(key, instructionArray);
        }
        instructions.put("b", new String[] { String.valueOf(valueA), "->", "a" });
        int value = evaluateKey("a");
        return value;
    }

    private int evaluateKey(String key) {
        String[] instruction = instructions.get(key);
        int value = 0;
        if (instruction[1].equals("->")) {
            value = assign(instruction);
        } else if (instruction[1].equals("AND")) {
            value = and(instruction);
        } else if (instruction[1].equals("OR")) {
            value = or(instruction);
        } else if (instruction[1].equals("LSHIFT")) {
            value = lshift(instruction);
        } else if (instruction[1].equals("RSHIFT")) {
            value = rshift(instruction);
        } else if (instruction[0].equals("NOT")) {
            value = not(instruction);
        }
        instructions.put(key, new String[] { String.valueOf(value), "->", key });
        return value;
    }

    private int eval(String x) {
        if (Character.isDigit(x.charAt(0))) {
            return Integer.parseInt(x);
        } else {
            return evaluateKey(x);
        }
    }

    private int assign(String[] x) {
        return eval(x[0]);
    }

    private int and(String[] x) {
        return eval(x[0]) & eval(x[2]);
    }

    private int or(String[] x) {
        return eval(x[0]) | eval(x[2]);
    }

    private int lshift(String[] x) {
        return eval(x[0]) << eval(x[2]);
    }

    private int rshift(String[] x) {
        return eval(x[0]) >> eval(x[2]);
    }

    private int not(String[] x) {
        return ~eval(x[1]);
    }
}
