package com.handy.monster.listener;

import com.handy.lib.annotation.HandyListener;
import com.handy.lib.constants.VersionCheckEnum;
import com.handy.monster.constant.MonsterConstants;
import com.handy.monster.spawn.ElevenCreatureSpawn;
import com.handy.monster.spawn.NineCreatureSpawn;
import com.handy.monster.spawn.SixteenCreatureSpawn;
import com.handy.monster.utils.ConfigUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * @author hs
 * @date 2020/4/4 20:10
 */
@HandyListener
public class MonsterListener implements Listener {

    /**
     * 当一个生物体在世界中出生时触发该事件.
     * 如果该事件被取消了,那么这个生物将不会出生.
     *
     * @param event 事件.
     */
    @EventHandler
    public void onCreatureSpawnEvent(CreatureSpawnEvent event) {
        // 是否启用
        if (!MonsterConstants.isUse) {
            return;
        }
        if (!"replace".equals(ConfigUtil.config.getString("spawn"))) {
            return;
        }

        // 不是自然生成的不执行
        if (!CreatureSpawnEvent.SpawnReason.NATURAL.equals(event.getSpawnReason())) {
            return;
        }

        // 判断世界是否开启
        if (MonsterConstants.worlds == null || !MonsterConstants.worlds.contains(event.getEntity().getWorld().getName())) {
            return;
        }

        // 不同版本不同怪物
        switch (VersionCheckEnum.getEnum()) {
            case V_1_7:
            case V_1_8:
            case V_1_9:
            case V_1_10:
                NineCreatureSpawn.setCreatureSpawn(event);
                break;
            case V_1_11:
            case V_1_12:
            case V_1_13:
            case V_1_14:
            case V_1_15:
                ElevenCreatureSpawn.setCreatureSpawn(event);
                break;
            case V_1_16:
            case V_1_17:
                SixteenCreatureSpawn.setCreatureSpawn(event);
                break;
            default:
                break;
        }
    }

}