package com.handy.monster.listener;

import com.handy.monster.Monster;

/**
 * 加载监听器
 *
 * @author handy
 */
public class ListenerManage {

    /**
     * 监听器注册
     *
     * @param plugin 插件
     */
    public static void enableListener(Monster plugin) {
        // 当一个生物体在世界中出生时触发该事件.
        plugin.getServer().getPluginManager().registerEvents(new MonsterListener(), plugin);
        // 当一个实体受到另外一个实体伤害时触发该事件.
        plugin.getServer().getPluginManager().registerEvents(new MonsterLevelListener(), plugin);
    }

}