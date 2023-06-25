package cn.handyplus.monster.util;

import cn.handyplus.lib.constants.VersionCheckEnum;
import cn.handyplus.lib.core.CollUtil;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.Random;
import java.util.Set;

/**
 * @author handy
 */
public class MonsterLevelUtil {

    /**
     * 设置等级
     *
     * @param entity 实体
     * @param name   名称
     */
    public static void setLevel(LivingEntity entity, String name) {
        setLevel(entity, name, -1);
    }

    /**
     * 设置等级
     *
     * @param entity 实体
     * @param name   名称
     * @param level  等级
     */
    public static void setLevel(LivingEntity entity, String name, int level) {
        // 初始化属性
        AttributeInstance healthAttribute = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        AttributeInstance damageAttribute = entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        if (healthAttribute == null || damageAttribute == null) {
            return;
        }
        double maxHealth = 0.0;
        double damage = 0.0;
        // 初始化
        if (level == -1) {
            int randomLevel = ConfigUtil.config.getInt("randomLevel");
            level = new Random().nextInt(randomLevel);
            level = level == 0 ? 1 : level;
            maxHealth = healthAttribute.getValue();
            damage = damageAttribute.getValue();
            String healthStr = "health:" + maxHealth;
            String damageStr = "damage:" + damage;
            entity.addScoreboardTag(healthStr);
            entity.addScoreboardTag(damageStr);
        } else {
            Set<String> scoreboardTags = entity.getScoreboardTags();
            if (!scoreboardTags.isEmpty()) {
                for (String scoreboardTag : scoreboardTags) {
                    if (scoreboardTag.contains("health")) {
                        String[] split = scoreboardTag.split(":");
                        maxHealth = Double.parseDouble(split[1]);
                    }
                    if (scoreboardTag.contains("damage")) {
                        String[] split = scoreboardTag.split(":");
                        damage = Double.parseDouble(split[1]);
                    }
                }
            }
        }
        // 设置属性
        double levelHealth = ConfigUtil.config.getDouble("levelHealth");
        double levelDamage = ConfigUtil.config.getDouble("levelDamage");
        healthAttribute.setBaseValue(maxHealth + (double) level * levelHealth);
        damageAttribute.setBaseValue(damage + (double) level * levelDamage);
        entity.setHealth(healthAttribute.getValue());
        entity.setCustomName(ChatColor.AQUA + "[" + level + "级]" + ChatColor.WHITE + name);
        // 设置是否在客户端上显示实体的自定义名称.
        entity.setCustomNameVisible(true);
        // 设置生物实体是否会在远离玩家时消失.
        entity.setRemoveWhenFarAway(true);
        // 添加等级药水效果
        Collection<PotionEffect> activePotionEffects = entity.getActivePotionEffects();
        if (CollUtil.isNotEmpty(activePotionEffects)) {
            for (PotionEffect potionEffect : activePotionEffects) {
                entity.removePotionEffect(potionEffect.getType());
            }
        }
        PotionEffect potionEffect;
        if (VersionCheckEnum.getEnum().getVersionId() < VersionCheckEnum.V_1_13.getVersionId()) {
            potionEffect = new PotionEffect(PotionEffectType.NIGHT_VISION, 1728000, level, false);
        } else {
            potionEffect = new PotionEffect(PotionEffectType.NIGHT_VISION, 1728000, level, false, false, false);
        }
        entity.addPotionEffect(potionEffect);
    }

    /**
     * 获取等级
     *
     * @param entity 怪物
     */
    public static int getLevel(LivingEntity entity) {
        PotionEffect potionEffect = entity.getPotionEffect(PotionEffectType.NIGHT_VISION);
        if (potionEffect == null || !PotionEffectType.NIGHT_VISION.equals(potionEffect.getType())) {
            return -1;
        }
        return potionEffect.getAmplifier();
    }

}