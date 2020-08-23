package com.handy.monster.utils;

import com.handy.monster.constant.MonsterConstants;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;

public class BaseUtil {


    public static Boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    public static Boolean isNumeric(String str) {
        Matcher isNum = MonsterConstants.NUMERIC.matcher(str);
        return isNum.matches();
    }

    public static Location getLocation(String world, Double x, Double y, Double z) {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

    public static int isNumber(String str) {
        Matcher matcher = MonsterConstants.NUMBER.matcher(str);
        return "".equals(matcher.replaceAll("")) ? -1 : Integer.parseInt(matcher.replaceAll("").trim());
    }

    public static String getCustomName(String str) {
        return str.substring(str.indexOf("]") + 1);
    }
}

