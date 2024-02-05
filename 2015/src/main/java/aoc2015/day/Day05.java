package aoc2015.day;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aoc2015.interfaces.GenericPuzzle;

public class Day05 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        int goodLines = 0;
        for (String line : lines) {
            String pattern = "^(?!.*(?:ab|cd|pq|xy))(?=(?:[^aeiou]*[aeiou]){3,})(?=.*([a-zA-Z])\\1)";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(line);
            if (matcher.find()) {
                goodLines++;
            }
        }
        return goodLines;
    }

    public Object solvePart2(List<String> lines) {
        int goodLines = 0;
        for (String line : lines) {
            String pattern = "(?=.*([a-zA-Z]{2}).*\\1)(?=.*(.).\\2)";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(line);
            if (matcher.find()) {
                goodLines++;
            }
        }
        return goodLines;
    }

    /* Solutions without Regex - Part 1 */
    // public Object solvePart1(List<String> lines) {
    // int goodLines = 0;
    // for (String line : lines) {
    // if (line.contains("ab") || line.contains("cd") || line.contains("pq") ||
    // line.contains("xy")) {
    // continue;
    // }
    // int vowels = 0;
    // for (int j = 0; j < line.length(); j++) {
    // switch (line.charAt(j)) {
    // case 'a':
    // case 'e':
    // case 'i':
    // case 'o':
    // case 'u':
    // vowels++;
    // break;
    // default:
    // break;
    // }
    // }
    // if (vowels < 3) {
    // continue;
    // }
    // for (int j = 0; j < line.length() - 1; j++) {
    // if (line.charAt(j) == line.charAt(j + 1)) {
    // goodLines++;
    // break;
    // }
    // }
    // }
    // return goodLines;
    // }

    /* Solutions without Regex - Part 2 */
    // public Object solvePart2(List<String> lines) {
    // int goodLines = 0;
    // for (String line : lines) {
    // boolean check1 = false;
    // boolean check2 = false;
    // for (int j = 0; j < line.length() - 2; j++) {
    // String pair = line.substring(j, j + 2);
    // String restOfString = line.substring(j + 2);
    // if (restOfString.contains(pair)) {
    // check1 = true;
    // }
    // }
    // for (int j = 0; j < line.length() - 2; j++) {
    // if (line.charAt(j) == line.charAt(j + 2)) {
    // check2 = true;
    // break;
    // }
    // }
    // if (check1 && check2) {
    // goodLines++;
    // }
    // }
    // return goodLines;
    // }
}
