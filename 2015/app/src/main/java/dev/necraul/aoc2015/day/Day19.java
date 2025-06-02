package dev.necraul.aoc2015.day;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import dev.necraul.aoc2015.interfaces.GenericPuzzle;

public class Day19 implements GenericPuzzle {

    private String input = "ORnPBPMgArCaCaCaSiThCaCaSiThCaCaPBSiRnFArRnFArCaCaSiThCaCaSiThCaCaCaCaCaCaSiRnFYFArSiRnMgArCaSiRnPTiTiBFYPBFArSiRnCaSiRnTiRnFArSiAlArPTiBPTiRnCaSiAlArCaPTiTiBPMgYFArPTiRnFArSiRnCaCaFArRnCaFArCaSiRnSiRnMgArFYCaSiRnMgArCaCaSiThPRnFArPBCaSiRnMgArCaCaSiThCaSiRnTiMgArFArSiThSiThCaCaSiRnMgArCaCaSiRnFArTiBPTiRnCaSiAlArCaPTiRnFArPBPBCaCaSiThCaPBSiThPRnFArSiThCaSiThCaSiThCaPTiBSiRnFYFArCaCaPRnFArPBCaCaPBSiRnTiRnFArCaPRnFArSiRnCaCaCaSiThCaRnCaFArYCaSiRnFArBCaCaCaSiThFArPBFArCaSiRnFArRnCaCaCaFArSiRnFArTiRnPMgArF";

    public Object solvePart1(List<String> lines) {
        String key = lines.getFirst().split(" => ")[0];
        List<String> values = new ArrayList<>();
        Map<String, List<String>> combinations = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(" => ");
            if (!parts[0].equals(key)) {
                combinations.put(key, values);
                key = parts[0];
                values = new ArrayList<>();
            }
            values.add(parts[1]);
        }
        combinations.put(key, values);
        Set<String> distinctMolecules = new HashSet<>();
        Set<Map.Entry<String, List<String>>> sets = combinations.entrySet();
        for (Map.Entry<String, List<String>> set : sets) {
            int keyLength = set.getKey().length();
            for (int i = 0; i < input.length(); i++) {
                // off by one lmao
                if (i + keyLength > input.length()) {
                    continue;
                }
                String inputSubString = input.substring(i, i + keyLength);
                if (inputSubString.equals(set.getKey())) {
                    for (String value : set.getValue()) {
                        String start = input.substring(0, i);
                        String end = input.substring(i + keyLength);
                        String molecule = start + value + end;
                        distinctMolecules.add(molecule);
                    }
                }
            }
        }
        return distinctMolecules.size();
    }

    public Object solvePart2(List<String> lines) {
        Map<String, String> combinations = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(" => ");
            combinations.put(parts[1], parts[0]);
        }
        List<Map.Entry<String, String>> setList = combinations.entrySet().stream().collect(Collectors.toList());
        int count = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(input);
        while (!queue.isEmpty()) {
            String molecule = queue.poll();
            for (Map.Entry<String, String> set : setList) {
                if (!set.getValue().equals("e") && molecule.contains(set.getKey())) {
                    queue.add(molecule.replaceFirst(set.getKey(), set.getValue()));
                    count++;
                    break;
                }
            }
            // e => HF
            // e => NAl
            // e => OMg
            // 3 because longest possible one is 3
            // if it's longer than three, shuffle
            if (queue.isEmpty() && molecule.length() > 3) {
                queue.add(input);
                Collections.shuffle(setList);
                count = 0;
            }
        }
        return count + 1;
    }
}
