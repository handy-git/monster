package com.handy.monster.entity;

import com.handy.monster.constant.EntityEquipmentTypeEnum;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.inventory.EntityEquipment;

/**
 * 怪物参数
 *
 * @author hs
 * @date 2020/4/4 20:10
 */
@Data
public class MonsterParam {
    /**
     * 装备
     */
    private EntityEquipment equipment;
    /**
     * 装备类型
     */
    private EntityEquipmentTypeEnum equipmentTypeEnum;
    /**
     * 材质
     */
    private Material material;
    /**
     * 是否附魔
     */
    private Boolean isEnchantment;
    /**
     * 是否掉落
     */
    private Boolean dropChance;
}
