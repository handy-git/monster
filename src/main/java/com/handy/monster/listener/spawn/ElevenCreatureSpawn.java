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
 * 1.11-1.15版本
 *
 * @author handy
 * @date 2020/10/10 16:06
 */
public class ElevenCreatureSpawn {

    /**
     * 设置怪物出生的装备和药水效果
     *
     * @param event 事件
     */
    public static void setCreatureSpawn(CreatureSpawnEvent event) {
        LivingEntity entity = event.getEntity();
        EntityType entityType = event.getEntityType();
        EntityEquipment equipment = entity.getEquipment();

        String name = entityType.getName();
        if (name == null || "".equals(name)) {
            return;
        }

        if (name.equals(EntityType.valueOf("ZOMBIE").getName())) {
            MonsterEquipmentUtil.lotteryEquipment(equipment, EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD);
            MonsterLevelUtil.setLevel(entity, "僵尸");
        }
        if (name.equals(EntityType.valueOf("ZOMBIE_VILLAGER").getName())) {
            MonsterEquipmentUtil.lotteryEquipment(equipment, EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD);
            MonsterLevelUtil.setLevel(entity, "僵尸村民");
        }
        if (name.equals(EntityType.valueOf("HUSK").getName())) {
            MonsterEquipmentUtil.lotteryEquipment(equipment, EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD);
            MonsterLevelUtil.setLevel(entity, "尸壳");
        }
        if (name.equals(EntityType.valueOf("PIG_ZOMBIE").getName())) {
            MonsterEquipmentUtil.lotteryEquipment(equipment, EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD);
            MonsterLevelUtil.setLevel(entity, "猪人");
        }
        if (name.equals(EntityType.valueOf("SKELETON").getName())) {
            MonsterEquipmentUtil.lotteryEquipment(equipment, EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_BOW);
            MonsterLevelUtil.setLevel(entity, "骷髅");
        }
        if (name.equals(EntityType.valueOf("WITHER_SKELETON").getName())) {
            MonsterEquipmentUtil.lotteryEquipment(equipment, EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_BOW);
            MonsterLevelUtil.setLevel(entity, "凋零骷髅");
        }
        if (name.equals(EntityType.valueOf("STRAY").getName())) {
            MonsterEquipmentUtil.lotteryEquipment(equipment, EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_BOW);
            MonsterLevelUtil.setLevel(entity, "流浪者");
        }
        if (name.equals(EntityType.valueOf("SPIDER").getName())) {
            MonsterPotionEffectUtil.getPotionEffect(entity);
            MonsterLevelUtil.setLevel(entity, "蜘蛛");
        }
        if (name.equals(EntityType.valueOf("CAVE_SPIDER").getName())) {
            MonsterPotionEffectUtil.getPotionEffect(entity);
            MonsterLevelUtil.setLevel(entity, "洞穴蜘蛛");
        }
    }

}
