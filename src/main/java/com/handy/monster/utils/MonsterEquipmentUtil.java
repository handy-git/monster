package com.handy.monster.utils;

import com.handy.monster.constant.EntityEquipmentTypeEnum;
import com.handy.monster.constant.MonsterConstants;
import com.handy.monster.constant.VersionCheckEnum;
import com.handy.monster.entity.MonsterParam;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Random;

/**
 * @author hs
 * @date 2020/4/4 20:10
 */
public class MonsterEquipmentUtil {

    /**
     * 设置装备
     *
     * @param param 参数
     */
    private static void setEquipment(MonsterParam param) {
        // 材质为空
        if (param.getMaterial() == null) {
            return;
        }

        ItemStack itemStack = new ItemStack(param.getMaterial());

        if (param.getIsEnchantment() != null && param.getIsEnchantment()) {
            Enchantment[] enchantments = Enchantment.values();

            for (int i = 0; i < MonsterConstants.enchantmentNumber; i++) {
                // 获取随机附魔
                Enchantment enchantment = enchantments[new Random().nextInt(enchantments.length)];
                // 判断该装备是否可附魔
                if (!enchantment.canEnchantItem(itemStack)) {
                    continue;
                }
                // 获取已经有的附魔
                Map<Enchantment, Integer> enchantmentsMap = itemStack.getEnchantments();
                // 如果有该附魔了,跳过
                if (enchantmentsMap.get(enchantment) != null) {
                    continue;
                }
                // 进行附魔
                int level = (new Random()).nextInt(enchantment.getMaxLevel());
                itemStack.addEnchantment(enchantment, level != 0 ? level : enchantment.getStartLevel());
            }
        }

        // 设置装备位置
        switch (param.getEquipmentTypeEnum()) {
            case HELMET:
                param.getEquipment().setHelmet(itemStack);
                // 设置头盔掉落率
                if (param.getDropChance() != null && param.getDropChance()) {
                    param.getEquipment().setHelmetDropChance(1.0F);
                } else {
                    param.getEquipment().setHelmetDropChance(0.0F);
                }
                break;
            case CHEST_PLATE:
                param.getEquipment().setChestplate(itemStack);
                // 设置胸甲掉落率
                if (param.getDropChance() != null && param.getDropChance()) {
                    param.getEquipment().setChestplateDropChance(1.0F);
                } else {
                    param.getEquipment().setChestplateDropChance(0.0F);
                }
                break;
            case LEGGINGS:
                param.getEquipment().setLeggings(itemStack);
                // 设置裤子掉落率
                if (param.getDropChance() != null && param.getDropChance()) {
                    param.getEquipment().setLeggingsDropChance(1.0F);
                } else {
                    param.getEquipment().setLeggingsDropChance(0.0F);
                }
                break;
            case BOOTS:
                param.getEquipment().setBoots(itemStack);
                // 设置鞋子掉落率
                if (param.getDropChance() != null && param.getDropChance()) {
                    param.getEquipment().setBootsDropChance(1.0F);
                } else {
                    param.getEquipment().setBootsDropChance(0.0F);
                }
                break;
            case ITEM_IN_MAIN_HAND_SWORD:
            case ITEM_IN_MAIN_HAND_BOW:
                param.getEquipment().setItemInHand(itemStack);
                // 设置武器掉落率
                if (param.getDropChance() != null && param.getDropChance()) {
                    param.getEquipment().setItemInHandDropChance(1.0F);
                } else {
                    param.getEquipment().setItemInHandDropChance(0.0F);
                }
            default:
                break;
        }
    }

    /**
     * 获取怪物装备参数
     *
     * @param equipment               装备
     * @param entityEquipmentTypeEnum 装备类型
     * @return 装备参数
     */
    private static MonsterParam getMonsterParam(EntityEquipment equipment, EntityEquipmentTypeEnum entityEquipmentTypeEnum) {
        MonsterParam param = new MonsterParam();
        param.setEquipment(equipment);
        param.setEquipmentTypeEnum(entityEquipmentTypeEnum);
        String material;
        // 合金装备
        if (MonsterConstants.netheriteLotteryList.randomIndex() == 0) {
            switch (param.getEquipmentTypeEnum()) {
                case BOOTS:
                    param.setMaterial(Material.NETHERITE_BOOTS);
                    break;
                case CHEST_PLATE:
                    param.setMaterial(Material.NETHERITE_CHESTPLATE);
                    break;
                case HELMET:
                    param.setMaterial(Material.NETHERITE_HELMET);
                    break;
                case LEGGINGS:
                    param.setMaterial(Material.NETHERITE_LEGGINGS);
                    break;
                case ITEM_IN_MAIN_HAND_SWORD:
                    param.setMaterial(Material.NETHERITE_SWORD);
                    break;
                case ITEM_IN_MAIN_HAND_BOW:
                    param.setMaterial(Material.BOW);
                default:
                    break;
            }

            if (MonsterConstants.enchantment.randomIndex() == 0) {
                param.setIsEnchantment(true);
            }

            if (MonsterConstants.netheriteDropChance.randomIndex() == 0) {
                param.setDropChance(true);
            }
            // 钻石装备
        } else if (MonsterConstants.diamondLotteryList.randomIndex() == 0) {
            switch (param.getEquipmentTypeEnum()) {
                case BOOTS:
                    param.setMaterial(Material.DIAMOND_BOOTS);
                    break;
                case CHEST_PLATE:
                    param.setMaterial(Material.DIAMOND_CHESTPLATE);
                    break;
                case HELMET:
                    param.setMaterial(Material.DIAMOND_HELMET);
                    break;
                case LEGGINGS:
                    param.setMaterial(Material.DIAMOND_LEGGINGS);
                    break;
                case ITEM_IN_MAIN_HAND_SWORD:
                    param.setMaterial(Material.DIAMOND_SWORD);
                    break;
                case ITEM_IN_MAIN_HAND_BOW:
                    param.setMaterial(Material.BOW);
                default:
                    break;
            }

            if (MonsterConstants.enchantment.randomIndex() == 0) {
                param.setIsEnchantment(true);
            }

            if (MonsterConstants.diamondDropChance.randomIndex() == 0) {
                param.setDropChance(true);
            }
            // 铁装备
        } else if (MonsterConstants.ironLotteryList.randomIndex() == 0) {
            switch (param.getEquipmentTypeEnum()) {
                case BOOTS:
                    param.setMaterial(Material.IRON_BOOTS);
                    break;
                case CHEST_PLATE:
                    param.setMaterial(Material.IRON_CHESTPLATE);
                    break;
                case HELMET:
                    param.setMaterial(Material.IRON_HELMET);
                    break;
                case LEGGINGS:
                    param.setMaterial(Material.IRON_LEGGINGS);
                    break;
                case ITEM_IN_MAIN_HAND_SWORD:
                    param.setMaterial(Material.IRON_SWORD);
                    break;
                case ITEM_IN_MAIN_HAND_BOW:
                    param.setMaterial(Material.BOW);
                default:
                    break;
            }

            if (MonsterConstants.enchantment.randomIndex() == 0) {
                param.setIsEnchantment(true);
            }

            if (MonsterConstants.ironDropChance.randomIndex() == 0) {
                param.setDropChance(true);
            }
            // 金装备
        } else if (MonsterConstants.goldenLotteryList.randomIndex() == 0) {
            switch (param.getEquipmentTypeEnum()) {
                case BOOTS:
                    material = "GOLD_BOOTS";
                    if (VersionCheckEnum.getEnum().getVersionId() > VersionCheckEnum.V_1_12.getVersionId()) {
                        material = "GOLDEN_BOOTS";
                    }
                    param.setMaterial(Material.valueOf(material));
                    break;
                case CHEST_PLATE:
                    material = "GOLD_CHESTPLATE";
                    if (VersionCheckEnum.getEnum().getVersionId() > VersionCheckEnum.V_1_12.getVersionId()) {
                        material = "GOLDEN_CHESTPLATE";
                    }
                    param.setMaterial(Material.valueOf(material));
                    break;
                case HELMET:
                    material = "GOLD_HELMET";
                    if (VersionCheckEnum.getEnum().getVersionId() > VersionCheckEnum.V_1_12.getVersionId()) {
                        material = "GOLDEN_HELMET";
                    }
                    param.setMaterial(Material.valueOf(material));
                    break;
                case LEGGINGS:
                    material = "GOLD_LEGGINGS";
                    if (VersionCheckEnum.getEnum().getVersionId() > VersionCheckEnum.V_1_12.getVersionId()) {
                        material = "GOLDEN_LEGGINGS";
                    }
                    param.setMaterial(Material.valueOf(material));
                    break;
                case ITEM_IN_MAIN_HAND_SWORD:
                    material = "GOLD_SWORD";
                    if (VersionCheckEnum.getEnum().getVersionId() > VersionCheckEnum.V_1_12.getVersionId()) {
                        material = "GOLDEN_SWORD";
                    }
                    param.setMaterial(Material.valueOf(material));
                    break;
                case ITEM_IN_MAIN_HAND_BOW:
                    param.setMaterial(Material.BOW);
                default:
                    break;
            }

            if (MonsterConstants.enchantment.randomIndex() == 0) {
                param.setIsEnchantment(true);
            }

            if (MonsterConstants.goldenDropChance.randomIndex() == 0) {
                param.setDropChance(true);
            }
            // 锁链装备
        } else if (MonsterConstants.chainMailLotteryList.randomIndex() == 0) {
            switch (param.getEquipmentTypeEnum()) {
                case BOOTS:
                    param.setMaterial(Material.CHAINMAIL_BOOTS);
                    break;
                case CHEST_PLATE:
                    param.setMaterial(Material.CHAINMAIL_CHESTPLATE);
                    break;
                case HELMET:
                    param.setMaterial(Material.CHAINMAIL_HELMET);
                    break;
                case LEGGINGS:
                    param.setMaterial(Material.CHAINMAIL_LEGGINGS);
                    break;
                case ITEM_IN_MAIN_HAND_SWORD:
                    param.setMaterial(Material.STONE_SWORD);
                    break;
                case ITEM_IN_MAIN_HAND_BOW:
                    param.setMaterial(Material.BOW);
                default:
                    break;
            }

            if (MonsterConstants.enchantment.randomIndex() == 0) {
                param.setIsEnchantment(true);
            }

            if (MonsterConstants.chainMailDropChance.randomIndex() == 0) {
                param.setDropChance(true);
            }
            // 皮革装备
        } else if (MonsterConstants.leatherLotteryList.randomIndex() == 0) {
            switch (param.getEquipmentTypeEnum()) {
                case BOOTS:
                    param.setMaterial(Material.LEATHER_BOOTS);
                    break;
                case CHEST_PLATE:
                    param.setMaterial(Material.LEATHER_CHESTPLATE);
                    break;
                case HELMET:
                    param.setMaterial(Material.LEATHER_HELMET);
                    break;
                case LEGGINGS:
                    param.setMaterial(Material.LEATHER_LEGGINGS);
                    break;
                case ITEM_IN_MAIN_HAND_SWORD:
                    material = "WOOD_SWORD";
                    if (VersionCheckEnum.getEnum().getVersionId() > VersionCheckEnum.V_1_12.getVersionId()) {
                        material = "WOODEN_SWORD";
                    }
                    param.setMaterial(Material.valueOf(material));
                    break;
                case ITEM_IN_MAIN_HAND_BOW:
                    param.setMaterial(Material.BOW);
                default:
                    break;
            }

            if (MonsterConstants.enchantment.randomIndex() == 0) {
                param.setIsEnchantment(true);
            }

            if (MonsterConstants.leatherDropChance.randomIndex() == 0) {
                param.setDropChance(true);
            }
        }
        return param;
    }

    /**
     * 随机装备
     *
     * @param equipment 装备栏
     * @param weapon    装备位置
     */
    public static void lotteryEquipment(EntityEquipment equipment, EntityEquipmentTypeEnum weapon) {
        setEquipment(getMonsterParam(equipment, EntityEquipmentTypeEnum.HELMET));
        setEquipment(getMonsterParam(equipment, EntityEquipmentTypeEnum.CHEST_PLATE));
        setEquipment(getMonsterParam(equipment, EntityEquipmentTypeEnum.LEGGINGS));
        setEquipment(getMonsterParam(equipment, EntityEquipmentTypeEnum.BOOTS));
        if (weapon != null) {
            setEquipment(getMonsterParam(equipment, weapon));
        }
    }

}
