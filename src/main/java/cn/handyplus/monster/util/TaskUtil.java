package cn.handyplus.monster.util;

import cn.handyplus.lib.constants.VersionCheckEnum;
import cn.handyplus.lib.core.CollUtil;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.monster.Monster;
import cn.handyplus.monster.constant.MonsterConstants;
import cn.handyplus.monster.spawn.ElevenCreatureSpawn;
import cn.handyplus.monster.spawn.NineCreatureSpawn;
import cn.handyplus.monster.spawn.SixteenCreatureSpawn;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * @author handy
 **/
public class TaskUtil {
    private static final Semaphore LOCK = new Semaphore(1);

    private static final Map<UUID, Long> PLAYER_TIME_MAP = new HashMap<>();

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
     * 清除管理缓存
     */
    public static void clearPlayer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                long addPlayerCd = ConfigUtil.config.getLong("addPlayerCd");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Long time = PLAYER_TIME_MAP.get(player.getUniqueId());
                    if (time == null) {
                        continue;
                    }
                    long keepAlive = (System.currentTimeMillis() - time) / 1000L;
                    if (keepAlive >= addPlayerCd) {
                        PLAYER_TIME_MAP.remove(player.getUniqueId());
                        MessageUtil.sendDebugMessage(player, ChatColor.AQUA + player.getName() + "该玩家生成时间重置完成");
                    } else {
                        MessageUtil.sendDebugMessage(player, ChatColor.AQUA + player.getName() + "该玩家距离生成时间还差: " + (addPlayerCd - keepAlive));
                    }
                }
            }
        }.runTaskTimerAsynchronously(Monster.getInstance(), 60, 20L * 60);
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
            int minBound = ConfigUtil.config.getInt("minBound");
            int maxBound = ConfigUtil.config.getInt("maxBound");
            // 获取在线玩家
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (PLAYER_TIME_MAP.get(player.getUniqueId()) != null) {
                    MessageUtil.sendDebugMessage(player, ChatColor.AQUA + "该玩家生成时间正在冷却");
                    continue;
                }
                // 是否夜晚
                if (BaseUtil.worldTimeIsNotNight(player)) {
                    continue;
                }
                World world = player.getWorld();
                // 判断世界是否开启
                List<String> worlds = ConfigUtil.config.getStringList("worlds");
                if (CollUtil.isNotEmpty(worlds) && !worlds.contains("[ALL]") && !worlds.contains(world.getName())) {
                    continue;
                }
                int i = new Random().nextInt(10);
                if (i == 0) {
                    i = 1;
                }
                for (int j = 0; j < i; j++) {
                    Location randomLocation = ConfigUtil.getRandomLocation(player, minBound, maxBound, 0);
                    String key = keyList.get(new Random().nextInt(entityTypeMap.size()));
                    TextComponent message = new TextComponent(ChatColor.BLUE + "怪物出生点, x:" + randomLocation.getX() + ",y: " + randomLocation.getY() + ",z:" + randomLocation.getZ());
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + randomLocation.getX() + " " + randomLocation.getY() + " " + randomLocation.getZ()));
                    MessageUtil.sendDebugMessage(player, message);
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
                        case V_1_18:
                            SixteenCreatureSpawn.setCreatureSpawn(livingEntity);
                            break;
                        default:
                            SixteenCreatureSpawn.setCreatureSpawn(livingEntity);
                            break;
                    }
                }
                PLAYER_TIME_MAP.put(player.getUniqueId(), System.currentTimeMillis());
            }
        } finally {
            LOCK.release();
        }
    }

}