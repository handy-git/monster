package cn.handyplus.monster.util;

import cn.handyplus.lib.util.LegacyUtil;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

/**
 * @author handy
 */
public class MonsterPotionEffectUtil {

    /**
     * 药水效果
     *
     * @param entity 实体
     */
    public static void getPotionEffect(LivingEntity entity) {
        Random random = new Random();
        int ranNum = random.nextInt(10);
        PotionEffect potionEffect;
        switch (ranNum) {
            case 0:

                potionEffect = new PotionEffect(LegacyUtil.getIncreaseDamage(), 6000, 2);
                entity.addPotionEffect(potionEffect);
                break;
            case 1:
                potionEffect = new PotionEffect(PotionEffectType.INVISIBILITY, 6000, 2);
                entity.addPotionEffect(potionEffect);
                break;
            case 2:
                potionEffect = new PotionEffect(PotionEffectType.SPEED, 6000, 2);
                entity.addPotionEffect(potionEffect);
                break;
            case 3:
                potionEffect = new PotionEffect(PotionEffectType.REGENERATION, 6000, 2);
                entity.addPotionEffect(potionEffect);
                break;
            case 4:
                potionEffect = new PotionEffect(PotionEffectType.SPEED, 6000, 2);
                entity.addPotionEffect(potionEffect);
                potionEffect = new PotionEffect(PotionEffectType.REGENERATION, 6000, 2);
                entity.addPotionEffect(potionEffect);
                break;
            case 5:
                potionEffect = new PotionEffect(LegacyUtil.getIncreaseDamage(), 6000, 2);
                entity.addPotionEffect(potionEffect);
                potionEffect = new PotionEffect(PotionEffectType.INVISIBILITY, 6000, 2);
                entity.addPotionEffect(potionEffect);
                break;
            case 6:
                potionEffect = new PotionEffect(LegacyUtil.getIncreaseDamage(), 6000, 2);
                entity.addPotionEffect(potionEffect);
                potionEffect = new PotionEffect(PotionEffectType.INVISIBILITY, 6000, 2);
                entity.addPotionEffect(potionEffect);
                potionEffect = new PotionEffect(PotionEffectType.SPEED, 6000, 2);
                entity.addPotionEffect(potionEffect);
                potionEffect = new PotionEffect(PotionEffectType.REGENERATION, 6000, 2);
                entity.addPotionEffect(potionEffect);
                break;
            case 7:
                potionEffect = new PotionEffect(PotionEffectType.INVISIBILITY, 6000, 2);
                entity.addPotionEffect(potionEffect);
                potionEffect = new PotionEffect(PotionEffectType.SPEED, 6000, 2);
                entity.addPotionEffect(potionEffect);
                break;
            case 8:
                potionEffect = new PotionEffect(LegacyUtil.getIncreaseDamage(), 6000, 2);
                entity.addPotionEffect(potionEffect);
                potionEffect = new PotionEffect(PotionEffectType.REGENERATION, 6000, 2);
                entity.addPotionEffect(potionEffect);
                break;
            case 9:
                potionEffect = new PotionEffect(PotionEffectType.REGENERATION, 6000, 2);
                entity.addPotionEffect(potionEffect);
                potionEffect = new PotionEffect(LegacyUtil.getIncreaseDamage(), 6000, 2);
                entity.addPotionEffect(potionEffect);
                potionEffect = new PotionEffect(PotionEffectType.INVISIBILITY, 6000, 2);
                entity.addPotionEffect(potionEffect);
            default:
                break;
        }
    }

}
