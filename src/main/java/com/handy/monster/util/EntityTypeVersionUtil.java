package com.handy.monster.util;

import com.handy.lib.constants.VersionCheckEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 怪物类型
 *
 * @author handy
 **/
public class EntityTypeVersionUtil {
    /**
     * 怪物存储 版本: 怪物类型: 怪物名称
     */
    private static final Map<Integer, Map<String, String>> ENTITY_TYPE_MAP = new HashMap<>();

    private EntityTypeVersionUtil() {
    }

    private static class SingletonHolder {
        private static final EntityTypeVersionUtil INSTANCE = new EntityTypeVersionUtil();
    }

    public static EntityTypeVersionUtil getInstance() {
        if (ENTITY_TYPE_MAP.size() == 0) {
            init();
        }
        return EntityTypeVersionUtil.SingletonHolder.INSTANCE;
    }

    /**
     * 初始化
     */
    private static void init() {
        Map<String, String> map9 = new HashMap<>();
        map9.put("ZOMBIE", "僵尸");
        map9.put("PIG_ZOMBIE", "猪人");
        map9.put("SKELETON", "骷髅");
        map9.put("SPIDER", "蜘蛛");
        map9.put("CAVE_SPIDER", "洞穴蜘蛛");
        ENTITY_TYPE_MAP.put(VersionCheckEnum.V_1_9.getVersionId(), map9);
        ENTITY_TYPE_MAP.put(VersionCheckEnum.V_1_10.getVersionId(), map9);

        Map<String, String> map11 = new HashMap<>();
        map11.put("ZOMBIE", "僵尸");
        map11.put("ZOMBIE_VILLAGER", "僵尸村民");
        map11.put("HUSK", "尸壳");
        map11.put("PIG_ZOMBIE", "猪人");
        map11.put("SKELETON", "骷髅");
        map11.put("WITHER_SKELETON", "凋零骷髅");
        map11.put("STRAY", "流浪者");
        map11.put("SPIDER", "蜘蛛");
        map11.put("CAVE_SPIDER", "洞穴蜘蛛");
        ENTITY_TYPE_MAP.put(VersionCheckEnum.V_1_11.getVersionId(), map11);
        ENTITY_TYPE_MAP.put(VersionCheckEnum.V_1_12.getVersionId(), map11);
        ENTITY_TYPE_MAP.put(VersionCheckEnum.V_1_13.getVersionId(), map11);
        ENTITY_TYPE_MAP.put(VersionCheckEnum.V_1_14.getVersionId(), map11);
        ENTITY_TYPE_MAP.put(VersionCheckEnum.V_1_15.getVersionId(), map11);

        Map<String, String> map16 = new HashMap<>();
        map16.put("ZOMBIE", "僵尸");
        map16.put("ZOMBIE_VILLAGER", "僵尸村民");
        map16.put("HUSK", "尸壳");
        map16.put("DROWNED", "溺尸");
        map16.put("PIGLIN", "猪灵");
        map16.put("ZOMBIFIED_PIGLIN", "僵尸猪灵");
        map16.put("PIGLIN_BRUTE", "猪灵蛮兵");
        map16.put("SKELETON", "骷髅");
        map16.put("WITHER_SKELETON", "凋零骷髅");
        map16.put("STRAY", "流浪者");
        map16.put("SPIDER", "蜘蛛");
        map16.put("CAVE_SPIDER", "洞穴蜘蛛");
        ENTITY_TYPE_MAP.put(VersionCheckEnum.V_1_16.getVersionId(), map16);
        ENTITY_TYPE_MAP.put(VersionCheckEnum.V_1_17.getVersionId(), map16);
        ENTITY_TYPE_MAP.put(VersionCheckEnum.V_1_18.getVersionId(), map16);
    }

    /**
     * 获取对应版本的怪物
     *
     * @param versionId 版本id
     * @return 怪物map
     */
    public Map<String, String> getEntityTypeMap(Integer versionId) {
        return ENTITY_TYPE_MAP.get(versionId);
    }

}