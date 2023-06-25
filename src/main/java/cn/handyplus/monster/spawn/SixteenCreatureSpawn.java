package cn.handyplus.monster.spawn;

import cn.handyplus.monster.constant.EntityEquipmentTypeEnum;
import cn.handyplus.monster.util.MonsterEquipmentUtil;
import cn.handyplus.monster.util.MonsterLevelUtil;
import cn.handyplus.monster.util.MonsterPotionEffectUtil;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;

/**
 * 1.16版本
 *
 * @author handy
 */
public class SixteenCreatureSpawn {

    /**
     * 设置怪物出生的装备和药水效果
     *
     * @param event 事件
     */
    public static void setCreatureSpawn(CreatureSpawnEvent event) {
        setCreatureSpawn(event.getEntity());
    }

    public static void setCreatureSpawn(LivingEntity entity) {
        EntityType entityType = entity.getType();
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
        if (name.equals(EntityType.valueOf("DROWNED").getName())) {
            MonsterEquipmentUtil.lotteryEquipment(equipment, EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD);
            MonsterLevelUtil.setLevel(entity, "溺尸");
        }
        if (name.equals(EntityType.valueOf("PIGLIN").getName())) {
            MonsterEquipmentUtil.lotteryEquipment(equipment, EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD);
            MonsterLevelUtil.setLevel(entity, "猪灵");
        }
        if (name.equals(EntityType.valueOf("ZOMBIFIED_PIGLIN").getName())) {
            MonsterEquipmentUtil.lotteryEquipment(equipment, EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD);
            MonsterLevelUtil.setLevel(entity, "僵尸猪灵");
        }
        if (name.equals(EntityType.valueOf("PIGLIN_BRUTE").getName())) {
            MonsterEquipmentUtil.lotteryEquipment(equipment, null);
            MonsterLevelUtil.setLevel(entity, "猪灵蛮兵");
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
