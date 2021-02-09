package com.handy.monster.listener.spawn;

import com.handy.monster.constant.EntityEquipmentTypeEnum;
import com.handy.monster.utils.MonsterEquipmentUtil;
import com.handy.monster.utils.MonsterLevelUtil;
import com.handy.monster.utils.MonsterPotionEffectUtil;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;

/**
 * 1.9-1.10版本
 *
 * @author handy
 * @date 2020/10/10 16:07
 */
public class NineCreatureSpawn {

    /**
     * 设置怪物出生的装备和药水效果
     *
     * @param event 事件
     */
    public static void setCreatureSpawn(CreatureSpawnEvent event) {
        LivingEntity entity = event.getEntity();
        EntityType entityType = event.getEntityType();
        EntityEquipment equipment = entity.getEquipment();

        switch (entityType) {
            case ZOMBIE:
                if (equipment != null) {
                    MonsterEquipmentUtil.lotteryEquipment(equipment, EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD);
                    MonsterLevelUtil.setLevel(entity, event.getLocation(), "僵尸");
                }
                break;
            /*case PIG_ZOMBIE:
                if (equipment != null) {
                    MonsterEquipmentUtil.lotteryEquipment(equipment, EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD);
                    MonsterLevelUtil.setLevel(entity, event.getLocation(), "猪人");
                }
                break;*/
            case SKELETON:
                if (equipment != null) {
                    MonsterEquipmentUtil.lotteryEquipment(equipment, EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_BOW);
                    MonsterLevelUtil.setLevel(entity, event.getLocation(), "骷髅");
                }
                break;
            case SPIDER:
                MonsterPotionEffectUtil.getPotionEffect(entity);
                MonsterLevelUtil.setLevel(entity, event.getLocation(), "蜘蛛");
                break;
            case CAVE_SPIDER:
                MonsterPotionEffectUtil.getPotionEffect(entity);
                MonsterLevelUtil.setLevel(entity, event.getLocation(), "洞穴蜘蛛");
            default:
                break;
        }
    }

}
