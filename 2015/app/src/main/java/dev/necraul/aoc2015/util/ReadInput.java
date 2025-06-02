package dev.necraul.aoc2015.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadInput {
    public List<String> readLines(String filePath) throws IOException {
        Path path = Path.of(filePath);
        return Files.readAllLines(path);
    }
}
