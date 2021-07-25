package com.handy.monster.util;

import com.handy.lib.api.MessageApi;
import com.handy.lib.constants.VersionCheckEnum;
import com.handy.lib.util.BaseUtil;
import com.handy.monster.Monster;
import com.handy.monster.constant.MonsterConstants;
import com.handy.monster.spawn.ElevenCreatureSpawn;
import com.handy.monster.spawn.NineCreatureSpawn;
import com.handy.monster.spawn.SixteenCreatureSpawn;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author handy
 **/
public class TaskUtil {
    private static final Semaphore LOCK = new Semaphore(1);

    /**
     * 设置定时任务
     */
    public static void setAsyncMonsterSpawn() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if ("add".equals(ConfigUtil.config.getString("spawn"))) {
                    setMonsterSpawn();
                }
            }
        }.runTaskTimer(Monster.getInstance(), 60, 20L * ConfigUtil.config.getLong("time"));
    }

    /**
     * 生成怪物
     */
    private static void setMonsterSpawn() {
        if (!LOCK.tryAcquire()) {
            return;
        }
        try {
            // 是否启用
            if (!MonsterConstants.isUse) {
                return;
            }
            Map<String, String> entityTypeMap = EntityTypeVersionUtil.getInstance().getEntityTypeMap(VersionCheckEnum.getEnum().getVersionId());
            if (entityTypeMap == null) {
                return;
            }
            List<String> keyList = new ArrayList<>(entityTypeMap.size());
            entityTypeMap.forEach((key, value) -> {
                keyList.add(key);
            });
            // 获取在线玩家
            for (Player player : Bukkit.getOnlinePlayers()) {
                // 是否夜晚
                if (BaseUtil.worldTimeIsNotNight(player)) {
                    continue;
                }
                World world = player.getWorld();
                // 判断世界是否开启
                if (MonsterConstants.worlds == null || !MonsterConstants.worlds.contains(world.getName())) {
                    continue;
                }
                int i = new Random().nextInt(10);
                if (i == 0) {
                    i = 1;
                }
                for (int j = 0; j < i; j++) {
                    Location randomLocation = ConfigUtil.getRandomLocation(player, 10, 128, 0);
                    String key = keyList.get(new Random().nextInt(entityTypeMap.size()));
                    TextComponent message = new TextComponent("怪物出生点, x:" + randomLocation.getX() + ",y: " + randomLocation.getY() + ",z:" + randomLocation.getZ());
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + randomLocation.getX() + " " + randomLocation.getY() + " " + randomLocation.getZ()));
                    MessageApi.sendDebugMessage(player, message);
                    // 获取怪物
                    EntityType entitytype = EntityType.valueOf(key);
                    Entity entity = world.spawnEntity(randomLocation, entitytype);
                    LivingEntity livingEntity = (LivingEntity) entity;
                    // 不同版本不同怪物
                    switch (VersionCheckEnum.getEnum()) {
                        case V_1_7:
                        case V_1_8:
                        case V_1_9:
                        case V_1_10:
                            NineCreatureSpawn.setCreatureSpawn(livingEntity);
                            break;
                        case V_1_11:
                        case V_1_12:
                        case V_1_13:
                        case V_1_14:
                        case V_1_15:
                            ElevenCreatureSpawn.setCreatureSpawn(livingEntity);
                            break;
                        case V_1_16:
                        case V_1_17:
                            SixteenCreatureSpawn.setCreatureSpawn(livingEntity);
                            break;
                        default:
                            break;
                    }
                }
            }
        } finally {
            LOCK.release();
        }
    }

}