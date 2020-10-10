package com.handy.monster.utils;

import com.handy.monster.constant.MonsterConstants;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;

/**
 * @author hs
 * @date 2020/4/4 20:10
 */
public class BaseUtil {

    /**
     * 是否玩家
     *
     * @param sender 发送者
     * @return true 是
     */
    public static Boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }


    /**
     * 是否数字
     *
     * @param str 字符串
     * @return true 是
     */
    public static Boolean isNumeric(String str) {
        Matcher isNum = MonsterConstants.NUMERIC.matcher(str);
        return isNum.matches();
    }

    /**
     * 获取位置
     *
     * @param world 世界
     * @param x     x
     * @param y     y
     * @param z     z
     * @return 位置
     */
    public static Location getLocation(String world, Double x, Double y, Double z) {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

    /**
     * 转换为数字
     *
     * @param str 字符串
     * @return 数字
     */
    public static int isNumber(String str) {
        Matcher matcher = MonsterConstants.NUMBER.matcher(str);
        return "".equals(matcher.replaceAll("")) ? -1 : Integer.parseInt(matcher.replaceAll("").trim());
    }

    /**
     * 获取自定义名称
     *
     * @param str 字符串
     * @return 字符串
     */
    public static String getCustomName(String str) {
        return str.substring(str.indexOf("]") + 1);
    }

}

