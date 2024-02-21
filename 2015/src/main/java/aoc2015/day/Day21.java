package aoc2015.day;

import java.util.ArrayList;
import java.util.List;

import aoc2015.data.Character;
import aoc2015.data.Item;
import aoc2015.interfaces.GenericPuzzle;

public class Day21 implements GenericPuzzle {

    private List<Item> weapons = new ArrayList<>();
    private List<Item> armors = new ArrayList<>();
    private List<Item> rings = new ArrayList<>();
    private int cost = Integer.MAX_VALUE;

    public Object solvePart1(List<String> lines) {
        weapons = new ArrayList<>();
        armors = new ArrayList<>();
        rings = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            Integer cost = Integer.parseInt(parts[1]);
            Integer damage = Integer.parseInt(parts[2]);
            Integer armor = Integer.parseInt(parts[3]);
            Item item = new Item(cost, damage, armor);
            String type = parts[4];
            switch (type) {
                case "weapon":
                    weapons.add(item);
                    break;
                case "armor":
                    armors.add(item);
                    break;
                case "ring":
                    rings.add(item);
                    break;
                default:
                    break;
            }
        }
        // in order for me to beat the enemy
        // enemyTurns = myHP:100 / (enemyDamage:8 - myArmor)
        // myTurns = enemyHP:104 / (myDamage - enemyArmor:1)
        // myTurns <= enemyTurns - myTurns equal to or smaller than enemy
        // it's kinda useless to check for Math.max(1, damage - armor)
        int weaponCount = weapons.size();
        int armorCount = armors.size();
        int ringCount = rings.size();
        int minCost = Integer.MAX_VALUE;
        for (int ringTwoIndex = -1; ringTwoIndex < ringCount - 1; ringTwoIndex++) {
            for (int ringOneIndex = -1; ringOneIndex < ringCount - 1; ringOneIndex++) {
                for (int armorIndex = -1; armorIndex < armorCount - 1; armorIndex++) {
                    for (int weaponIndex = 0; weaponIndex < weaponCount - 1; weaponIndex++) {
                        Character enemyCharacter = new Character(104, 8, 1);
                        Character myCharacter = makeCharacter(weaponIndex, armorIndex, ringOneIndex, ringTwoIndex);
                        boolean myWin = determineWin(myCharacter, enemyCharacter);
                        if (myWin) {
                            if (cost < minCost) {
                                minCost = cost;
                            }
                        }
                    }
                }
            }
        }
        return minCost;
    }

    public Object solvePart2(List<String> lines) {
        weapons = new ArrayList<>();
        armors = new ArrayList<>();
        rings = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            Integer cost = Integer.parseInt(parts[1]);
            Integer damage = Integer.parseInt(parts[2]);
            Integer armor = Integer.parseInt(parts[3]);
            Item item = new Item(cost, damage, armor);
            String type = parts[4];
            switch (type) {
                case "weapon":
                    weapons.add(item);
                    break;
                case "armor":
                    armors.add(item);
                    break;
                case "ring":
                    rings.add(item);
                    break;
                default:
                    break;
            }
        }
        // the exact opposite of earlier so
        // myTurns < enemyTurns
        int weaponCount = weapons.size();
        int armorCount = armors.size();
        int ringCount = rings.size();
        int maxCost = Integer.MIN_VALUE;
        for (int ringTwoIndex = -1; ringTwoIndex < ringCount - 1; ringTwoIndex++) {
            for (int ringOneIndex = -1; ringOneIndex < ringCount - 1; ringOneIndex++) {
                for (int armorIndex = -1; armorIndex < armorCount - 1; armorIndex++) {
                    for (int weaponIndex = 0; weaponIndex < weaponCount - 1; weaponIndex++) {
                        Character enemyCharacter = new Character(104, 8, 1);
                        Character myCharacter = makeCharacter(weaponIndex, armorIndex, ringOneIndex, ringTwoIndex);
                        boolean myWin = determineWin(myCharacter, enemyCharacter);
                        if (!myWin) {
                            if (cost >= maxCost) {
                                maxCost = cost;
                            }
                        }
                    }
                }
            }
        }
        return maxCost;
    }

    private Character makeCharacter(int weaponIndex, int armorIndex, int ringOneIndex, int ringTwoIndex) {
        Item weapon = new Item(0, 0, 0);
        Item armor = new Item(0, 0, 0);
        Item ringOne = new Item(0, 0, 0);
        Item ringTwo = new Item(0, 0, 0);
        // you always buy a weapon but for consistency sake
        // i added another if != -1
        if (weaponIndex != -1) {
            weapon = weapons.get(weaponIndex);
        }
        if (armorIndex != -1) {
            armor = armors.get(armorIndex);
        }
        if (ringOneIndex != -1) {
            ringOne = rings.get(ringOneIndex);
        }
        if (ringTwoIndex != -1) {
            ringTwo = rings.get(ringTwoIndex);
        }
        cost = weapon.cost + armor.cost + ringOne.cost + ringTwo.cost;
        int hitPoints = 100;
        int offence = weapon.damage + ringOne.damage + ringTwo.damage;
        int defence = armor.armor + ringOne.armor + ringTwo.armor;
        Character myCharacter = new Character(hitPoints, offence, defence);
        return myCharacter;
    }

    private boolean determineWin(Character myCharacter, Character enemyCharacter) {
        int myTurns = Math.ceilDiv(enemyCharacter.hitPoints, Math.max(1, myCharacter.damage - enemyCharacter.armor));
        int enemyTurns = Math.ceilDiv(myCharacter.hitPoints, Math.max(1, enemyCharacter.damage - myCharacter.armor));
        return myTurns <= enemyTurns;
    }
}
