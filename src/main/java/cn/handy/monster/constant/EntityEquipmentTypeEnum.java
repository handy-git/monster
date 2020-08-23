package cn.handy.monster.constant;

public enum EntityEquipmentTypeEnum {
    BOOTS(0, "鞋子"),
    CHEST_PLATE(1, "护腿"),
    HELMET(2, "上衣"),
    LEGGINGS(3, "头盔"),
    ITEM_IN_MAIN_HAND_SWORD(4, "主手剑"),
    ITEM_IN_MAIN_HAND_BOW(5, "主手弓");

    private Integer id;
    private String name;

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    private EntityEquipmentTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}

