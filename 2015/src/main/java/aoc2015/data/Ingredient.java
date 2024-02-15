package aoc2015.data;

public class Ingredient {

    public int capacity;
    public int durability;
    public int flavor;
    public int texture;
    public int calories;

    public Ingredient(String capacity, String durability, String flavor, String texture, String calories) {
        this.capacity = Integer.parseInt(capacity);
        this.durability = Integer.parseInt(durability);
        this.flavor = Integer.parseInt(flavor);
        this.texture = Integer.parseInt(texture);
        this.calories = Integer.parseInt(calories);
    }
}
