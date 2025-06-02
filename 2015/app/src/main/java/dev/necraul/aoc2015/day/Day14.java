package dev.necraul.aoc2015.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.necraul.aoc2015.interfaces.GenericPuzzle;

public class Day14 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        List<Integer> flightDistances = new ArrayList<>();
        for (String line : lines) {
            int flightSeconds = 2503;
            int flightDistance = 0;
            String[] splits = line.split(" ");
            int speed = Integer.parseInt(splits[3]);
            int movementSeconds = Integer.parseInt(splits[6]);
            int restSeconds = Integer.parseInt(splits[13]);
            int flightIterationsFloor = Math.floorDiv(flightSeconds, (movementSeconds + restSeconds));
            int secondsLeft = flightSeconds - flightIterationsFloor * (movementSeconds + restSeconds);
            // distance until the last sprint
            flightDistance += speed * movementSeconds * flightIterationsFloor;
            // distance of the last sprint based on how much time left
            flightDistance += speed * ((secondsLeft > movementSeconds) ? movementSeconds : secondsLeft);

            flightDistances.add(flightDistance);
        }
        return flightDistances.stream().mapToInt(Integer::intValue).max().orElseThrow();
    }

    public Object solvePart2(List<String> lines) {
        int flightSeconds = 2503;
        int reindeerCount = lines.size();
        int[] flightDistances = new int[reindeerCount];
        int[] speed = new int[reindeerCount];
        int[] movementSeconds = new int[reindeerCount];
        int[] restSeconds = new int[reindeerCount];
        int[] movementSecondsLeft = new int[reindeerCount];
        int[] restSecondsLeft = new int[reindeerCount];
        int[] scores = new int[reindeerCount];
        boolean[] isResting = new boolean[reindeerCount];
        for (int i = 0; i < reindeerCount; i++) {
            String line = lines.get(i);
            String[] splits = line.split(" ");
            speed[i] = Integer.parseInt(splits[3]);
            movementSeconds[i] = Integer.parseInt(splits[6]);
            movementSecondsLeft[i] = Integer.parseInt(splits[6]);
            restSeconds[i] = Integer.parseInt(splits[13]);
            restSecondsLeft[i] = Integer.parseInt(splits[13]);
        }
        // this while loop calculates the current position, movement and
        // resting time left, and whether or not the reindeer is resting
        while (flightSeconds > 0) {
            for (int i = 0; i < reindeerCount; i++) {
                if (movementSecondsLeft[i] > 0 && !isResting[i]) {
                    flightDistances[i] += speed[i];
                    movementSecondsLeft[i]--;
                } else if (movementSecondsLeft[i] == 0) {
                    movementSecondsLeft[i] = movementSeconds[i];
                    isResting[i] = true;
                }
                if (restSecondsLeft[i] > 1 && isResting[i]) {
                    restSecondsLeft[i]--;
                } else if (restSecondsLeft[i] == 1) {
                    restSecondsLeft[i] = restSeconds[i];
                    isResting[i] = false;
                }
            }
            // indices of reindeers with the longest flight distance
            // is returned and each of them are rewarded one point
            List<Integer> indices = findReindeersWithMaxDistance(flightDistances);
            for (int j = 0; j < indices.size(); j++) {
                scores[indices.get(j)]++;
            }
            flightSeconds--;
        }
        return Arrays.stream(scores).max().orElseThrow();
    }

    private List<Integer> findReindeersWithMaxDistance(int[] array) {
        List<Integer> indices = new ArrayList<>();
        int max = Arrays.stream(array).max().orElseThrow();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == max) {
                indices.add(i);
            }
        }
        return indices;
    }
}
