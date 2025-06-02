package dev.necraul.aoc2015.day;

import java.util.ArrayList;
import java.util.List;

import dev.necraul.aoc2015.data.Ingredient;
import dev.necraul.aoc2015.interfaces.GenericPuzzle;

public class Day15 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (String line : lines) {
            String[] splits = line.split(" ");
            String capacity = splits[2].substring(0, splits[2].length() - 1);
            String durability = splits[4].substring(0, splits[4].length() - 1);
            String flavor = splits[6].substring(0, splits[6].length() - 1);
            String texture = splits[8].substring(0, splits[8].length() - 1);
            String calories = splits[10];
            Ingredient ingredient = new Ingredient(capacity, durability, flavor, texture, calories);
            ingredients.add(ingredient);
        }
        Ingredient ing0 = ingredients.get(0);
        Ingredient ing1 = ingredients.get(1);
        Ingredient ing2 = ingredients.get(2);
        Ingredient ing3 = ingredients.get(3);
        int max = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <= 100 - i; j++) {
                if (j <= 0) {
                    continue;
                }
                for (int k = 0; k <= 100 - j - i; k++) {
                    if (k <= 0) {
                        continue;
                    }
                    int l = 100 - k - j - i;
                    int capacity = Math.max(0,
                            i * ing0.capacity + j * ing1.capacity + k * ing2.capacity + l * ing3.capacity);
                    int durability = Math.max(0,
                            i * ing0.durability + j * ing1.durability + k * ing2.durability + l * ing3.durability);
                    int flavor = Math.max(0, i * ing0.flavor + j * ing1.flavor + k * ing2.flavor + l * ing3.flavor);
                    int texture = Math.max(0,
                            i * ing0.texture + j * ing1.texture + k * ing2.texture + l * ing3.texture);
                    int current = capacity * durability * flavor * texture;
                    if (current > max) {
                        max = current;
                    }
                }
            }
        }
        return max;
    }

    public Object solvePart2(List<String> lines) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (String line : lines) {
            String[] splits = line.split(" ");
            String capacity = splits[2].substring(0, splits[2].length() - 1);
            String durability = splits[4].substring(0, splits[4].length() - 1);
            String flavor = splits[6].substring(0, splits[6].length() - 1);
            String texture = splits[8].substring(0, splits[8].length() - 1);
            String calories = splits[10];
            Ingredient ingredient = new Ingredient(capacity, durability, flavor, texture, calories);
            ingredients.add(ingredient);
        }
        Ingredient ing0 = ingredients.get(0);
        Ingredient ing1 = ingredients.get(1);
        Ingredient ing2 = ingredients.get(2);
        Ingredient ing3 = ingredients.get(3);
        int max = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <= 100 - i; j++) {
                if (j <= 0) {
                    continue;
                }
                for (int k = 0; k <= 100 - j - i; k++) {
                    if (k <= 0) {
                        continue;
                    }
                    int l = 100 - k - j - i;
                    int calories = i * ing0.calories + j * ing1.calories + k * ing2.calories
                            + l * ing3.calories;
                    if (calories != 500) {
                        continue;
                    }
                    int capacity = Math.max(0,
                            i * ing0.capacity + j * ing1.capacity + k * ing2.capacity + l * ing3.capacity);
                    int durability = Math.max(0,
                            i * ing0.durability + j * ing1.durability + k * ing2.durability + l * ing3.durability);
                    int flavor = Math.max(0, i * ing0.flavor + j * ing1.flavor + k * ing2.flavor + l * ing3.flavor);
                    int texture = Math.max(0,
                            i * ing0.texture + j * ing1.texture + k * ing2.texture + l * ing3.texture);
                    int current = capacity * durability * flavor * texture;
                    if (current > max) {
                        max = current;
                    }
                }
            }
        }
        return max;
    }
}
