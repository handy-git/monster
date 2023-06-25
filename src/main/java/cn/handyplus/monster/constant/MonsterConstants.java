package cn.handyplus.monster.constant;

import cn.handyplus.lib.util.LotteryUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author handy
 */
@Getter
@AllArgsConstructor
public class MonsterConstants {
    public static LotteryUtil netheriteLotteryList;
    public static LotteryUtil netheriteDropChance;
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

    public static Double levelHealth = 0.0D;
    public static Double levelDamage = 0.0D;

    public static Double levelEliteHealth = 1.0D;
    public static Double levelEliteDamage = 1.0D;

}
