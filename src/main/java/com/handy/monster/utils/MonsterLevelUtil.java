package com.handy.monster.utils;

import com.handy.monster.constant.MonsterConstants;
import org.bukkit.ChatColor;
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
     * @param entity 实体
     * @param name   名称
     */
    public static void setLevel(LivingEntity entity, String name) {
        AttributeInstance healthAttribute = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        AttributeInstance damageAttribute = entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeInstance armorAttribute = entity.getAttribute(Attribute.GENERIC_ARMOR);
        AttributeInstance attackSpeedAttribute = entity.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        AttributeInstance knockBackResistanceAttribute = entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        AttributeInstance movementSpeedAttribute = entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);

        int level = (new Random()).nextInt(10);
        healthAttribute.setBaseValue(healthAttribute.getValue() + (double) level * MonsterConstants.levelHealth);
        damageAttribute.setBaseValue(damageAttribute.getValue() + (double) level * MonsterConstants.levelDamage);
        armorAttribute.setBaseValue(armorAttribute.getValue() + (double) level * MonsterConstants.levelArmor);
        attackSpeedAttribute.setBaseValue(attackSpeedAttribute.getValue() + (double) level * MonsterConstants.levelAttackSpeed);
        knockBackResistanceAttribute.setBaseValue(knockBackResistanceAttribute.getValue() + (double) level * MonsterConstants.levelKnockBackResistance);
        movementSpeedAttribute.setBaseValue(movementSpeedAttribute.getValue() + (double) level * MonsterConstants.levelMovementSpeed);

        entity.setHealth(healthAttribute.getValue());
        entity.setCustomName(ChatColor.AQUA + "[" + level + "级]" + ChatColor.WHITE + name);
        entity.setCustomNameVisible(true);
        entity.setRemoveWhenFarAway(true);
    }

}