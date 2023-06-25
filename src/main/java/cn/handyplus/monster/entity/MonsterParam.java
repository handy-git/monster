package cn.handyplus.monster.entity;

import cn.handyplus.monster.constant.EntityEquipmentTypeEnum;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.inventory.EntityEquipment;

/**
 * 怪物参数
 *
 * @author handy
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
