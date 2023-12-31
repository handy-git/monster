package cn.handyplus.monster.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.constants.VersionCheckEnum;
import cn.handyplus.lib.core.CollUtil;
import cn.handyplus.monster.constant.MonsterConstants;
import cn.handyplus.monster.spawn.ElevenCreatureSpawn;
import cn.handyplus.monster.spawn.NineCreatureSpawn;
import cn.handyplus.monster.spawn.SixteenCreatureSpawn;
import cn.handyplus.monster.util.ConfigUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.List;

/**
 * @author handy
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
        // 判断世界是否开启
        List<String> worlds = ConfigUtil.config.getStringList("worlds");
        if (CollUtil.isNotEmpty(worlds) && !worlds.contains("[ALL]") && !worlds.contains(event.getEntity().getWorld().getName())) {
            return;
        }
        // 生成规则
        if (!"replace".equals(ConfigUtil.config.getString("spawn"))) {
            return;
        }
        // 不是自然生成的不执行
        if (!CreatureSpawnEvent.SpawnReason.NATURAL.equals(event.getSpawnReason())) {
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
            case V_1_18:
            case V_1_19:
            case V_1_20:
            default:
                SixteenCreatureSpawn.setCreatureSpawn(event);
                break;
        }
    }

}