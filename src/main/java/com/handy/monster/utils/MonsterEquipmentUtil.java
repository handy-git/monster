package com.handy.monster.utils;

import com.handy.monster.constant.EntityEquipmentTypeEnum;
import com.handy.monster.constant.MonsterConstants;
import com.handy.monster.entity.MonsterParam;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class MonsterEquipmentUtil {
    public MonsterEquipmentUtil() {
    }

    private static void setEquipment(MonsterParam param) {
        if (param.getMaterial() != null) {
            ItemStack itemStack = new ItemStack(param.getMaterial());
            if (param.getIsEnchantment() != null && param.getIsEnchantment()) {
                Enchantment[] enchantments = Enchantment.values();

                for(int i = 0; i < MonsterConstants.enchantmentNumber; ++i) {
                    int random = (new Random()).nextInt(enchantments.length);
                    Enchantment enchantment = enchantments[random];
                    boolean canEnchantItem = enchantment.canEnchantItem(itemStack);
                    if (canEnchantItem) {
                        Map<Enchantment, Integer> enchantmentsMap = itemStack.getEnchantments();
                        Boolean conflictsWith = false;

                        Enchantment enchantmentMap;
                        for(Iterator var9 = enchantmentsMap.keySet().iterator(); var9.hasNext(); conflictsWith = enchantment.conflictsWith(enchantmentMap)) {
                            enchantmentMap = (Enchantment)var9.next();
                        }

                        if (!conflictsWith) {
                            int level = (new Random()).nextInt(enchantment.getMaxLevel());
                            itemStack.addEnchantment(enchantment, level != 0 ? level : enchantment.getStartLevel());
                        }
                    }
                }
            }

            switch(param.getEquipmentTypeEnum()) {
                case EntityEquipmentTypeEnum.BOOTS:
                    param.getEquipment().setBoots(itemStack);
                    break;
                case EntityEquipmentTypeEnum.CHEST_PLATE:
                    param.getEquipment().setChestplate(itemStack);
                    break;
                case EntityEquipmentTypeEnum.HELMET:
                    param.getEquipment().setHelmet(itemStack);
                    break;
                case EntityEquipmentTypeEnum.LEGGINGS:
                    param.getEquipment().setLeggings(itemStack);
                    break;
                case EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD:
                    param.getEquipment().setItemInMainHand(itemStack);
                    break;
                case EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_BOW:
                    param.getEquipment().setItemInMainHand(itemStack);
            }

            if (param.getDropChance() != null && param.getDropChance()) {
                param.getEquipment().setBootsDropChance(1.0F);
            } else {
                param.getEquipment().setBootsDropChance(0.0F);
            }

        }
    }

    private static MonsterParam getMonsterParam(EntityEquipment equipment, EntityEquipmentTypeEnum entityEquipmentTypeEnum) {
        MonsterParam param = new MonsterParam();
        param.setEquipment(equipment);
        param.setEquipmentTypeEnum(entityEquipmentTypeEnum);
        if (MonsterConstants.diamondLotteryList.randomColunmIndex() == 0) {
            switch(param.getEquipmentTypeEnum()) {
                case EntityEquipmentTypeEnum.BOOTS:
                    param.setMaterial(Material.DIAMOND_BOOTS);
                    break;
                case EntityEquipmentTypeEnum.CHEST_PLATE:
                    param.setMaterial(Material.DIAMOND_CHESTPLATE);
                    break;
                case EntityEquipmentTypeEnum.HELMET:
                    param.setMaterial(Material.DIAMOND_HELMET);
                    break;
                case EntityEquipmentTypeEnum.LEGGINGS:
                    param.setMaterial(Material.DIAMOND_LEGGINGS);
                    break;
                case EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD:
                    param.setMaterial(Material.DIAMOND_SWORD);
                    break;
                case EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_BOW:
                    param.setMaterial(Material.BOW);
            }

            if (MonsterConstants.enchantment.randomColunmIndex() == 0) {
                param.setIsEnchantment(true);
            }

            if (MonsterConstants.diamondDropChance.randomColunmIndex() == 0) {
                param.setDropChance(true);
            }
        } else if (MonsterConstants.ironLotteryList.randomColunmIndex() == 0) {
            switch(param.getEquipmentTypeEnum()) {
                case EntityEquipmentTypeEnum.BOOTS:
                    param.setMaterial(Material.IRON_BOOTS);
                    break;
                case EntityEquipmentTypeEnum.CHEST_PLATE:
                    param.setMaterial(Material.IRON_CHESTPLATE);
                    break;
                case EntityEquipmentTypeEnum.HELMET:
                    param.setMaterial(Material.IRON_HELMET);
                    break;
                case EntityEquipmentTypeEnum.LEGGINGS:
                    param.setMaterial(Material.IRON_LEGGINGS);
                    break;
                case EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD:
                    param.setMaterial(Material.IRON_SWORD);
                    break;
                case EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_BOW:
                    param.setMaterial(Material.BOW);
            }

            if (MonsterConstants.enchantment.randomColunmIndex() == 0) {
                param.setIsEnchantment(true);
            }

            if (MonsterConstants.ironDropChance.randomColunmIndex() == 0) {
                param.setDropChance(true);
            }
        } else if (MonsterConstants.goldenLotteryList.randomColunmIndex() == 0) {
            switch(param.getEquipmentTypeEnum()) {
                case EntityEquipmentTypeEnum.BOOTS:
                    param.setMaterial(Material.GOLDEN_BOOTS);
                    break;
                case EntityEquipmentTypeEnum.CHEST_PLATE:
                    param.setMaterial(Material.GOLDEN_CHESTPLATE);
                    break;
                case EntityEquipmentTypeEnum.HELMET:
                    param.setMaterial(Material.GOLDEN_HELMET);
                    break;
                case EntityEquipmentTypeEnum.LEGGINGS:
                    param.setMaterial(Material.GOLDEN_LEGGINGS);
                    break;
                case EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD:
                    param.setMaterial(Material.GOLDEN_SWORD);
                    break;
                case EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_BOW:
                    param.setMaterial(Material.BOW);
            }

            if (MonsterConstants.enchantment.randomColunmIndex() == 0) {
                param.setIsEnchantment(true);
            }

            if (MonsterConstants.goldenDropChance.randomColunmIndex() == 0) {
                param.setDropChance(true);
            }
        } else if (MonsterConstants.chainMailLotteryList.randomColunmIndex() == 0) {
            switch(param.getEquipmentTypeEnum()) {
                case EntityEquipmentTypeEnum.BOOTS:
                    param.setMaterial(Material.CHAINMAIL_BOOTS);
                    break;
                case EntityEquipmentTypeEnum.CHEST_PLATE:
                    param.setMaterial(Material.CHAINMAIL_CHESTPLATE);
                    break;
                case EntityEquipmentTypeEnum.HELMET:
                    param.setMaterial(Material.CHAINMAIL_HELMET);
                    break;
                case EntityEquipmentTypeEnum.LEGGINGS:
                    param.setMaterial(Material.CHAINMAIL_LEGGINGS);
                    break;
                case EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD:
                    param.setMaterial(Material.STONE_SWORD);
                    break;
                case EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_BOW:
                    param.setMaterial(Material.BOW);
            }

            if (MonsterConstants.enchantment.randomColunmIndex() == 0) {
                param.setIsEnchantment(true);
            }

            if (MonsterConstants.chainMailDropChance.randomColunmIndex() == 0) {
                param.setDropChance(true);
            }
        } else if (MonsterConstants.leatherLotteryList.randomColunmIndex() == 0) {
            switch(param.getEquipmentTypeEnum()) {
                case EntityEquipmentTypeEnum.BOOTS:
                    param.setMaterial(Material.LEATHER_BOOTS);
                    break;
                case EntityEquipmentTypeEnum.CHEST_PLATE:
                    param.setMaterial(Material.LEATHER_CHESTPLATE);
                    break;
                case EntityEquipmentTypeEnum.HELMET:
                    param.setMaterial(Material.LEATHER_HELMET);
                    break;
                case EntityEquipmentTypeEnum.LEGGINGS:
                    param.setMaterial(Material.LEATHER_LEGGINGS);
                    break;
                case EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_SWORD:
                    param.setMaterial(Material.WOODEN_SWORD);
                    break;
                case EntityEquipmentTypeEnum.ITEM_IN_MAIN_HAND_BOW:
                    param.setMaterial(Material.BOW);
            }

            if (MonsterConstants.enchantment.randomColunmIndex() == 0) {
                param.setIsEnchantment(true);
            }

            if (MonsterConstants.leatherDropChance.randomColunmIndex() == 0) {
                param.setDropChance(true);
            }
        }

        return param;
    }

    public static void lotteryEquipment(EntityEquipment equipment, EntityEquipmentTypeEnum weapon) {
        setEquipment(getMonsterParam(equipment, EntityEquipmentTypeEnum.BOOTS));
        setEquipment(getMonsterParam(equipment, EntityEquipmentTypeEnum.CHEST_PLATE));
        setEquipment(getMonsterParam(equipment, EntityEquipmentTypeEnum.HELMET));
        setEquipment(getMonsterParam(equipment, EntityEquipmentTypeEnum.LEGGINGS));
        setEquipment(getMonsterParam(equipment, weapon));
    }
}
