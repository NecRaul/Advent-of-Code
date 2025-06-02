package dev.necraul.aoc2015.day;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import dev.necraul.aoc2015.interfaces.GenericPuzzle;

public class Day04 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        int number = 0;
        for (String line : lines) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                while (true) {
                    String text = line + number;
                    md.update(text.getBytes());
                    byte[] digest = md.digest();
                    if (digest[0] == 0 && digest[1] == 0 && ((digest[2] & 0xFF) < 0x10)) {
                        break;
                    }
                    number++;
                }
            } catch (NoSuchAlgorithmException e) {
                System.err.println("MD5 algorithm not available.");
            }
        }
        return number;
    }

    public Object solvePart2(List<String> lines) {
        int number = 0;
        for (String line : lines) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                while (true) {
                    String text = line + number;
                    md.update(text.getBytes());
                    byte[] digest = md.digest();
                    if (digest[0] == 0 && digest[1] == 0 && digest[2] == 0) {
                        break;
                    }
                    number++;
                }
            } catch (NoSuchAlgorithmException e) {
                System.err.println("MD5 algorithm not available.");
            }
        }
        return number;
    }
}
