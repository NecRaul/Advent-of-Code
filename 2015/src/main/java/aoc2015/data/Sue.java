package aoc2015.data;

public class Sue {
    public int number;
    public int children;
    public int cats;
    public int samoyeds;
    public int pomeranians;
    public int akitas;
    public int vizslas;
    public int goldfish;
    public int trees;
    public int cars;
    public int perfumes;

    public Sue(
            int number,
            int children,
            int cats,
            int samoyeds,
            int pomeranians,
            int akitas,
            int vizslas,
            int goldfish,
            int trees,
            int cars,
            int perfumes) {
        this.number = number;
        this.children = children;
        this.cats = cats;
        this.samoyeds = samoyeds;
        this.pomeranians = pomeranians;
        this.akitas = akitas;
        this.vizslas = vizslas;
        this.goldfish = goldfish;
        this.trees = trees;
        this.cars = cars;
        this.perfumes = perfumes;
    }

    public void printSue() {
        System.out.println(this.number);
    }
}
