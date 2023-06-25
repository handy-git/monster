package cn.handyplus.monster.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author handy
 */
@Getter
@AllArgsConstructor
public enum EntityEquipmentTypeEnum {
    /**
     * 装备类型
     */
    BOOTS(0, "鞋子"),
    CHEST_PLATE(1, "护腿"),
    HELMET(2, "上衣"),
    LEGGINGS(3, "头盔"),
    ITEM_IN_MAIN_HAND_SWORD(4, "主手剑"),
    ITEM_IN_MAIN_HAND_BOW(5, "主手弓");

    private final Integer id;
    private final String name;

}

