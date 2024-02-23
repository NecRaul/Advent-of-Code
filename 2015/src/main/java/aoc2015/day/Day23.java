package aoc2015.day;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import aoc2015.interfaces.GenericPuzzle;

public class Day23 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        return getRegisterB(lines, 0, 0);
    }

    public Object solvePart2(List<String> lines) {
        return getRegisterB(lines, 1, 0);
    }

    private int getRegisterB(List<String> lines, int a, int b) {
        Map<String, Integer> registers = new HashMap<>();
        registers.put("a", a);
        registers.put("b", b);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).replace(",", "");
            String[] parts = line.split(" ");
            if (parts[0].equals("hlf")) {
                registers.put(parts[1], registers.get(parts[1]) / 2);
            } else if (parts[0].equals("tpl")) {
                registers.put(parts[1], registers.get(parts[1]) * 3);
            } else if (parts[0].equals("inc")) {
                registers.put(parts[1], registers.get(parts[1]) + 1);
            } else if (parts[0].equals("jmp")) {
                int num = Integer.parseInt(parts[1]);
                i += num - 1;
            } else if (parts[0].equals("jie")) {
                if (registers.get(parts[1]) % 2 == 0) {
                    i += Integer.parseInt(parts[2]) - 1;
                }
            } else if (parts[0].equals("jio")) {
                if (registers.get(parts[1]) == 1) {
                    i += Integer.parseInt(parts[2]) - 1;
                }
            }
        }
        return registers.get("b");
    }
}
