package com.handy.monster.util;

import com.handy.lib.constants.BaseConstants;
import com.handy.lib.util.LotteryUtil;
import com.handy.monster.Monster;
import com.handy.monster.constant.MonsterConstants;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author hs
 * @date 2020/4/4 20:10
 */
public class ConfigUtil {
    public static FileConfiguration config;


    public static void getConfig() {
        if (!Monster.getInstance().getDataFolder().exists()) {
            Monster.getInstance().getDataFolder().mkdir();
        }

        File configFile = new File(Monster.getInstance().getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            Monster.getInstance().saveDefaultConfig();
        }

        Monster.getInstance().reloadConfig();
        config = Monster.getInstance().getConfig();
        loadConfig();
        BaseConstants.DEBUG = config.getBoolean("debug");
    }

    private static void loadConfig() {
        // 合金
        List<Double> netheriteLotteryList = new ArrayList<>();
        netheriteLotteryList.add(config.getDouble("netherite"));
        netheriteLotteryList.add(1.0D - config.getDouble("netherite"));
        MonsterConstants.netheriteLotteryList = new LotteryUtil(netheriteLotteryList);

        // 钻石
        List<Double> diamondLotteryList = new ArrayList<>();
        diamondLotteryList.add(config.getDouble("Diamond"));
        diamondLotteryList.add(1.0D - config.getDouble("Diamond"));
        MonsterConstants.diamondLotteryList = new LotteryUtil(diamondLotteryList);

        // 铁
        List<Double> ironLotteryList = new ArrayList<>();
        ironLotteryList.add(config.getDouble("Iron"));
        ironLotteryList.add(1.0D - config.getDouble("Iron"));
        MonsterConstants.ironLotteryList = new LotteryUtil(ironLotteryList);

        // 金
        List<Double> goldenLotteryList = new ArrayList<>();
        goldenLotteryList.add(config.getDouble("Golden"));
        goldenLotteryList.add(1.0D - config.getDouble("Golden"));
        MonsterConstants.goldenLotteryList = new LotteryUtil(goldenLotteryList);

        // 锁链
        List<Double> chainMailLotteryList = new ArrayList<>();
        chainMailLotteryList.add(config.getDouble("ChainMail"));
        chainMailLotteryList.add(1.0D - config.getDouble("ChainMail"));
        MonsterConstants.chainMailLotteryList = new LotteryUtil(chainMailLotteryList);

        // 皮革
        List<Double> leatherLotteryList = new ArrayList<>();
        leatherLotteryList.add(config.getDouble("Leather"));
        leatherLotteryList.add(1.0D - config.getDouble("Leather"));
        MonsterConstants.leatherLotteryList = new LotteryUtil(leatherLotteryList);

        // 合金掉率
        List<Double> netheriteDropChance = new ArrayList<>();
        netheriteDropChance.add(config.getDouble("netheriteDropChance"));
        netheriteDropChance.add(1.0D - config.getDouble("netheriteDropChance"));
        MonsterConstants.netheriteDropChance = new LotteryUtil(netheriteDropChance);

        // 钻石掉率
        List<Double> diamondDropChance = new ArrayList<>();
        diamondDropChance.add(config.getDouble("DiamondDropChance"));
        diamondDropChance.add(1.0D - config.getDouble("DiamondDropChance"));
        MonsterConstants.diamondDropChance = new LotteryUtil(diamondDropChance);

        // 铁掉率
        List<Double> ironDropChance = new ArrayList<>();
        ironDropChance.add(config.getDouble("IronDropChance"));
        ironDropChance.add(1.0D - config.getDouble("IronDropChance"));
        MonsterConstants.ironDropChance = new LotteryUtil(ironDropChance);

        // 金掉率
        List<Double> goldenDropChance = new ArrayList<>();
        goldenDropChance.add(config.getDouble("GoldenDropChance"));
        goldenDropChance.add(1.0D - config.getDouble("GoldenDropChance"));
        MonsterConstants.goldenDropChance = new LotteryUtil(goldenDropChance);

        // 锁链掉率
        List<Double> chainMailDropChance = new ArrayList<>();
        chainMailDropChance.add(config.getDouble("ChainMailDropChance"));
        chainMailDropChance.add(1.0D - config.getDouble("ChainMailDropChance"));
        MonsterConstants.chainMailDropChance = new LotteryUtil(chainMailDropChance);

        // 皮革掉率
        List<Double> leatherDropChance = new ArrayList<>();
        leatherDropChance.add(config.getDouble("LeatherDropChance"));
        leatherDropChance.add(1.0D - config.getDouble("LeatherDropChance"));
        MonsterConstants.leatherDropChance = new LotteryUtil(leatherDropChance);

        // 附魔概率
        List<Double> enchantment = new ArrayList<>();
        enchantment.add(config.getDouble("Enchantment"));
        enchantment.add(1.0D - config.getDouble("Enchantment"));
        MonsterConstants.enchantment = new LotteryUtil(enchantment);
        MonsterConstants.enchantmentNumber = config.getInt("EnchantmentNumber");

        MonsterConstants.levelHealth = config.getDouble("levelHealth");
        MonsterConstants.levelDamage = config.getDouble("levelDamage");

        MonsterConstants.levelEliteHealth = config.getDouble("levelEliteHealth");
        MonsterConstants.levelEliteDamage = config.getDouble("levelEliteDamage");
    }

    /**
     * 获取随机位置
     *
     * @param player   玩家
     * @param minBound 最小范围
     * @param maxBound 最大范围
     * @return Location
     */
    public static Location getRandomLocation(Player player, int minBound, int maxBound, int time) {
        Location location = player.getLocation();
        World world = player.getWorld();
        int x = (int) location.getX();
        int z = (int) location.getZ();
        int newX = ThreadLocalRandom.current().nextInt(x + minBound, x + maxBound);
        int newZ = ThreadLocalRandom.current().nextInt(z + minBound, z + maxBound);
        int newY = world.getHighestBlockYAt(newX, newZ);

        Location checkLoc = new Location(world, (double) newX, (double) (newY - 1), (double) newZ);
        Block block = world.getBlockAt(checkLoc);
        Location loc = new Location(world, (double) newX, (double) (newY + 1), (double) newZ);
        // 过滤方块类型
        List<Material> materials = Arrays.asList(Material.WATER, Material.LAVA);
        if (materials.contains(block.getType())) {
            return time >= 10 ? loc : getRandomLocation(player, minBound, maxBound, time + 1);
        }
        return loc;
    }

}