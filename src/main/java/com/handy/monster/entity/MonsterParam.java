package com.handy.monster.entity;

import com.handy.monster.constant.EntityEquipmentTypeEnum;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.inventory.EntityEquipment;


@Data
public class MonsterParam {
    private EntityEquipment equipment;
    private EntityEquipmentTypeEnum equipmentTypeEnum;
    private Material material;
    private Boolean isEnchantment;
    private Boolean dropChance;

}
