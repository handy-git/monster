package cn.handy.monster.utils;

import cn.handy.monster.constant.MonsterConstants;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

import java.util.Random;

public class MonsterLevelUtil {
    public MonsterLevelUtil() {
    }

    public static void setLevel(LivingEntity entity, Location location, String name) {
        if (MonsterConstants.worlds != null && MonsterConstants.worlds.contains(location.getWorld().getName())) {
            int level = (new Random()).nextInt(10);
            entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + (double)level * MonsterConstants.levelHealth);
            entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() + (double)level * MonsterConstants.levelDamage);
            entity.setHealth(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            entity.setCustomName(ChatColor.AQUA + "[" + level + "级]" + ChatColor.WHITE + name);
            entity.setCustomNameVisible(true);
        }

    }
}