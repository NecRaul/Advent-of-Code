package aoc2015.day;

import java.util.ArrayList;
import java.util.List;

import aoc2015.interfaces.GenericPuzzle;

public class Day24 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        List<Long> packages = new ArrayList<>();
        int groupCount = 3;
        for (String line : lines) {
            packages.add(Long.parseLong(line));
        }
        long sum = packages.stream().mapToLong(Long::longValue).sum();
        long average = sum / groupCount;
        List<Long> permutation = generatePermutation(packages, average);
        long result = permutation.stream().reduce((long) 1, (a, b) -> a * b);
        return result;
    }

    public Object solvePart2(List<String> lines) {
        List<Long> packages = new ArrayList<>();
        int groupCount = 4;
        for (String line : lines) {
            packages.add(Long.parseLong(line));
        }
        long sum = packages.stream().mapToLong(Long::longValue).sum();
        long average = sum / groupCount;
        List<Long> permutation = generatePermutation(packages, average);
        long result = permutation.stream().reduce((long) 1, (a, b) -> a * b);
        return result;
    }

    private List<Long> generatePermutation(List<Long> packages, long average) {
        List<Long> permutation = new ArrayList<>();
        return generatePermutationHelper(packages, average, new ArrayList<>(), permutation);
    }

    private List<Long> generatePermutationHelper(
            List<Long> packages, long remaining, List<Long> currentPermutation,
            List<Long> permutation) {
        if (remaining == 0) {
            if (permutation.isEmpty() || currentPermutation.size() < permutation.size()) {
                permutation.clear();
                permutation.addAll(currentPermutation);
            }
            return permutation;
        }
        for (int i = 0; i < packages.size(); i++) {
            List<Long> updatedPermutation = new ArrayList<>(currentPermutation);
            updatedPermutation.add(packages.get(i));

            List<Long> remainingPackages = new ArrayList<>(packages.subList(i + 1, packages.size()));
            generatePermutationHelper(
                    remainingPackages, remaining - packages.get(i), updatedPermutation,
                    permutation);
        }
        return permutation;
    }
}
