package com.handy.monster.utils;

import com.handy.monster.constant.MonsterConstants;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
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
        World world = location.getWorld();
        if (world == null) {
            return;
        }
        AttributeInstance healthAttribute = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        AttributeInstance damageAttribute = entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        if (healthAttribute == null || damageAttribute == null) {
            return;
        }

        int level = (new Random()).nextInt(10);
        healthAttribute.setBaseValue(healthAttribute.getValue() + (double) level * MonsterConstants.levelHealth);
        damageAttribute.setBaseValue(damageAttribute.getValue() + (double) level * MonsterConstants.levelDamage);
        entity.setHealth(healthAttribute.getValue());
        entity.setCustomName(ChatColor.AQUA + "[" + level + "级]" + ChatColor.WHITE + name);
        entity.setCustomNameVisible(true);
    }

}