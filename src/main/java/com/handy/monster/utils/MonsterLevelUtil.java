package com.handy.monster.utils;

import com.handy.monster.constant.MonsterConstants;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

import java.util.Random;

/**
 * @author hs
 * @date 2020/4/4 20:10
 */
public class MonsterLevelUtil {

    /**
     * 设置等级
     *
     * @param entity   实体
     * @param location 坐标
     * @param name     名称
     */
    public static void setLevel(LivingEntity entity, Location location, String name) {
        if (MonsterConstants.worlds != null && MonsterConstants.worlds.contains(location.getWorld().getName())) {
            int level = (new Random()).nextInt(10);
            entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + (double) level * MonsterConstants.levelHealth);
            entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() + (double) level * MonsterConstants.levelDamage);
            entity.setHealth(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            entity.setCustomName(ChatColor.AQUA + "[" + level + "级]" + ChatColor.WHITE + name);
            entity.setCustomNameVisible(true);
        }

    }
}