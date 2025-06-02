package dev.necraul.aoc2015.day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import dev.necraul.aoc2015.interfaces.GenericPuzzle;

public class Day22 implements GenericPuzzle {

    public Object solvePart1(List<String> lines) {
        List<Integer> enemyStats = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(": ");
            enemyStats.add(Integer.parseInt(parts[1]));
        }
        int enemyHP = enemyStats.get(0);
        int enemyDamage = enemyStats.get(1);
        boolean hardMode = false;
        return playGame(enemyDamage, enemyHP, hardMode);
    }

    public Object solvePart2(List<String> lines) {
        List<Integer> enemyStats = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(": ");
            enemyStats.add(Integer.parseInt(parts[1]));
        }
        int enemyHP = enemyStats.get(0);
        int enemyDamage = enemyStats.get(1);
        boolean hardMode = true;
        return playGame(enemyDamage, enemyHP, hardMode);
    }

    public int playGame(int enemyDamageArg, int enemyHPArg, boolean hardMode) {
        Set<Integer> manaSet = new HashSet<>();
        for (int i = 0; i < 1000000; i++) {
            int mana = 500;
            int playerHP = 50;
            int enemyDamage = enemyDamageArg;
            int enemyHP = enemyHPArg;
            boolean shieldEffect = false;
            boolean poisonEffect = false;
            boolean rechargeEffect = false;
            int shieldTurns = 0;
            int poisonTurns = 0;
            int rechargeTurns = 0;
            int usedMana = 0;

            for (int j = 0; j < 1000000; j++) {
                if (hardMode) {
                    playerHP--; // hard mode
                    if (playerHP <= 0) {
                        usedMana = Integer.MAX_VALUE;
                        break;
                    }
                }
                if (shieldEffect) {
                    shieldTurns--;
                }
                if (poisonEffect) {
                    enemyHP -= 3;
                    poisonTurns--;
                }
                if (rechargeEffect) {
                    mana += 101;
                    rechargeTurns--;
                }
                if (shieldTurns <= 0) {
                    shieldEffect = false;
                }
                if (poisonTurns <= 0) {
                    poisonEffect = false;
                }
                if (rechargeTurns <= 0) {
                    rechargeEffect = false;
                }
                // win condition
                if (enemyHP <= 0) {
                    break;
                }
                // next move
                int random = new Random().nextInt(5);
                for (int k = 0; k < 1000000; k++) {
                    if (random == 2 && shieldEffect) {
                        random = new Random().nextInt(5);
                    } else if (random == 3 && poisonEffect) {
                        random = new Random().nextInt(5);
                    } else if (random == 4 && rechargeEffect) {
                        random = new Random().nextInt(5);
                    } else {
                        break;
                    }
                }
                switch (random) {
                    case 0: // Magic Missile
                        mana -= 53;
                        usedMana += 53;
                        enemyHP -= 4;
                        break;
                    case 1: // Drain
                        mana -= 73;
                        usedMana += 73;
                        enemyHP -= 2;
                        playerHP += 2;
                        break;
                    case 2: // Shield
                        mana -= 113;
                        usedMana += 113;
                        shieldEffect = true;
                        shieldTurns = 6;
                        break;
                    case 3: // Poison
                        mana -= 173;
                        usedMana += 173;
                        poisonEffect = true;
                        poisonTurns = 6;
                        break;
                    case 4: // Recharge
                        mana -= 229;
                        usedMana += 229;
                        rechargeEffect = true;
                        rechargeTurns = 5;
                    default:
                        break;
                }
                // if mana runs out after using a spell you lose
                if (mana <= 0) {
                    usedMana = Integer.MAX_VALUE;
                    break;
                }
                if (poisonEffect) {
                    enemyHP -= 3;
                    poisonTurns--;
                }
                if (rechargeEffect) {
                    mana += 101;
                    rechargeTurns--;
                }
                if (enemyHP <= 0) {
                    break;
                }
                if (shieldEffect) {
                    playerHP -= 1;
                    shieldTurns--;
                } else {
                    playerHP -= enemyDamage;
                }
                if (playerHP <= 0) {
                    usedMana = Integer.MAX_VALUE;
                    break;
                }
                if (shieldTurns <= 0) {
                    shieldEffect = false;
                }
                if (poisonTurns <= 0) {
                    poisonEffect = false;
                }
                if (rechargeTurns <= 0) {
                    rechargeEffect = false;
                }
            }
            manaSet.add(usedMana);
        }
        return manaSet.stream().mapToInt(Integer::intValue).min().orElseThrow();
    }
}