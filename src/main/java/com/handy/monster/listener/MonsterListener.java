package com.handy.monster.listener;

import com.handy.monster.constant.MonsterConstants;
import com.handy.monster.constant.VersionCheckEnum;
import com.handy.monster.listener.spawn.ElevenCreatureSpawn;
import com.handy.monster.listener.spawn.NineCreatureSpawn;
import com.handy.monster.listener.spawn.SixteenCreatureSpawn;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * @author hs
 * @date 2020/4/4 20:10
 */
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

        // 是否禁用刷怪笼生成的怪物加强(true禁用)
        if (MonsterConstants.spawner && CreatureSpawnEvent.SpawnReason.SPAWNER.equals(event.getSpawnReason())) {
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
                SixteenCreatureSpawn.setCreatureSpawn(event);
                break;
            default:
                break;
        }
    }

}