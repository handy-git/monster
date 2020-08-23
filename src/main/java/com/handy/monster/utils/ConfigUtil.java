package com.handy.monster.utils;

import com.handy.monster.Monster;
import com.handy.monster.constant.MonsterConstants;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigUtil {
    public static FileConfiguration config;


    public static void getConfig() {
        if (!Monster.plugin.getDataFolder().exists()) {
            Monster.plugin.getDataFolder().mkdir();
        }

        File configFile = new File(Monster.plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            Monster.plugin.saveDefaultConfig();
        }

        Monster.plugin.reloadConfig();
        config = Monster.plugin.getConfig();
        loadConfig();
    }

    private static void loadConfig() {
        List<Double> diamondLotteryList = new ArrayList();
        diamondLotteryList.add(config.getDouble("Diamond"));
        diamondLotteryList.add(1.0D - config.getDouble("Diamond"));
        MonsterConstants.diamondLotteryList = new LotteryUtil(diamondLotteryList);
        List<Double> ironLotteryList = new ArrayList();
        ironLotteryList.add(config.getDouble("Iron"));
        ironLotteryList.add(1.0D - config.getDouble("Iron"));
        MonsterConstants.ironLotteryList = new LotteryUtil(ironLotteryList);
        List<Double> goldenLotteryList = new ArrayList();
        goldenLotteryList.add(config.getDouble("Golden"));
        goldenLotteryList.add(1.0D - config.getDouble("Golden"));
        MonsterConstants.goldenLotteryList = new LotteryUtil(goldenLotteryList);
        List<Double> chainMailLotteryList = new ArrayList();
        chainMailLotteryList.add(config.getDouble("ChainMail"));
        chainMailLotteryList.add(1.0D - config.getDouble("ChainMail"));
        MonsterConstants.chainMailLotteryList = new LotteryUtil(chainMailLotteryList);
        List<Double> leatherLotteryList = new ArrayList();
        leatherLotteryList.add(config.getDouble("Leather"));
        leatherLotteryList.add(1.0D - config.getDouble("Leather"));
        MonsterConstants.leatherLotteryList = new LotteryUtil(leatherLotteryList);
        List<Double> diamondDropChance = new ArrayList();
        diamondDropChance.add(config.getDouble("DiamondDropChance"));
        diamondDropChance.add(1.0D - config.getDouble("DiamondDropChance"));
        MonsterConstants.diamondDropChance = new LotteryUtil(diamondDropChance);
        List<Double> ironDropChance = new ArrayList();
        ironDropChance.add(config.getDouble("IronDropChance"));
        ironDropChance.add(1.0D - config.getDouble("IronDropChance"));
        MonsterConstants.ironDropChance = new LotteryUtil(ironDropChance);
        List<Double> goldenDropChance = new ArrayList();
        goldenDropChance.add(config.getDouble("GoldenDropChance"));
        goldenDropChance.add(1.0D - config.getDouble("GoldenDropChance"));
        MonsterConstants.goldenDropChance = new LotteryUtil(goldenDropChance);
        List<Double> chainMailDropChance = new ArrayList();
        chainMailDropChance.add(config.getDouble("ChainMailDropChance"));
        chainMailDropChance.add(1.0D - config.getDouble("ChainMailDropChance"));
        MonsterConstants.chainMailDropChance = new LotteryUtil(chainMailDropChance);
        List<Double> leatherDropChance = new ArrayList();
        leatherDropChance.add(config.getDouble("LeatherDropChance"));
        leatherDropChance.add(1.0D - config.getDouble("LeatherDropChance"));
        MonsterConstants.leatherDropChance = new LotteryUtil(leatherDropChance);
        List<Double> enchantment = new ArrayList();
        enchantment.add(config.getDouble("Enchantment"));
        enchantment.add(1.0D - config.getDouble("Enchantment"));
        MonsterConstants.enchantment = new LotteryUtil(enchantment);
        MonsterConstants.enchantmentNumber = config.getInt("EnchantmentNumber");
        MonsterConstants.spawner = config.getBoolean("spawner");
        MonsterConstants.worlds = config.getStringList("worlds");
        List<Double> levelProbabilityList = new ArrayList();
        levelProbabilityList.add(config.getDouble("levelProbability"));
        levelProbabilityList.add(1.0D - config.getDouble("levelProbability"));
        MonsterConstants.levelProbability = new LotteryUtil(levelProbabilityList);
        List<Double> levelEliteList = new ArrayList();
        levelEliteList.add(config.getDouble("levelElite"));
        levelEliteList.add(1.0D - config.getDouble("levelElite"));
        MonsterConstants.levelElite = new LotteryUtil(levelEliteList);
        MonsterConstants.levelHealth = config.getDouble("levelHealth");
        MonsterConstants.levelDamage = config.getDouble("levelDamage");
        MonsterConstants.levelEliteHealth = config.getDouble("levelEliteHealth");
        MonsterConstants.levelEliteDamage = config.getDouble("levelEliteDamage");
    }
}
