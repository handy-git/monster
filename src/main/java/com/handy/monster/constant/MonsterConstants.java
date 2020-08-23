package com.handy.monster.constant;

import com.handy.monster.utils.LotteryUtil;

import java.util.List;
import java.util.regex.Pattern;

public class MonsterConstants {
    public static LotteryUtil diamondLotteryList;
    public static LotteryUtil diamondDropChance;
    public static LotteryUtil ironLotteryList;
    public static LotteryUtil ironDropChance;
    public static LotteryUtil goldenLotteryList;
    public static LotteryUtil goldenDropChance;
    public static LotteryUtil chainMailLotteryList;
    public static LotteryUtil chainMailDropChance;
    public static LotteryUtil leatherLotteryList;
    public static LotteryUtil leatherDropChance;
    public static LotteryUtil enchantment;
    public static Integer enchantmentNumber = 0;
    public static Boolean isUse = true;
    public static Boolean spawner = true;
    public static List<String> worlds;
    public static LotteryUtil levelProbability;
    public static LotteryUtil levelElite;
    public static Double levelHealth = 0.0D;
    public static Double levelDamage = 0.0D;
    public static Double levelEliteHealth = 1.0D;
    public static Double levelEliteDamage = 1.0D;
    public static final Pattern NUMERIC = Pattern.compile("^-?\\d+(\\.\\d+)?$");
    public static final Pattern NUMBER = Pattern.compile("[^0-9]");

    public MonsterConstants() {
    }
}
