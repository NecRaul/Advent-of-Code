package aoc2015.day;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aoc2015.interfaces.GenericPuzzle;

public class Day12 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            Pattern number = Pattern.compile("-?\\d+");
            Matcher matcher = number.matcher(line);
            while (matcher.find()) {
                sum += Integer.parseInt(matcher.group());
            }
        }
        return sum;
    }

    public Object solvePart2(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            Pattern number = Pattern.compile("-?\\d+");
            Pattern combo = Pattern.compile("(\\{[-\\w\\,\\:\\\"]*\\})|(\\[[-\\w\\,\\\"]*\\])");
            Matcher matcher = combo.matcher(line);
            while (matcher.find()) {
                int result = 0;
                if (!(matcher.group().matches("\\{[-\\w\\d\\,\\:\\\"]*\\}")
                        && matcher.group().matches(".*:\"red\".*"))) {
                    Matcher matcher2 = number.matcher(matcher.group());
                    while (matcher2.find()) // sum up array or object
                        result += Integer.parseInt(matcher2.group());
                }
                line = line.substring(0, matcher.start()) + result + line.substring(matcher.end());
                matcher = combo.matcher(line);
                sum = result;
            }
        }
        return sum;
    }
}
