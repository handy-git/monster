package com.handy.monster.listener;

import com.handy.lib.annotation.HandyListener;
import com.handy.lib.api.MessageApi;
import com.handy.lib.util.BaseUtil;
import com.handy.lib.util.ProbabilityUtil;
import com.handy.monster.constant.MonsterConstants;
import com.handy.monster.util.ConfigUtil;
import com.handy.monster.util.MonsterLevelUtil;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * 怪物攻击进化
 *
 * @author handy
 */
@HandyListener
public class MonsterLevelListener implements Listener {

    /**
     * 当一个实体受到另外一个实体伤害时触发该事件
     * 怪物攻击进化
     *
     * @param event 事件
     */
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        // 判断世界是否开启
        if (MonsterConstants.worlds == null || !MonsterConstants.worlds.contains(event.getDamager().getWorld().getName())) {
            return;
        }
        // 判断攻击的是怪物
        if (!(event.getDamager() instanceof LivingEntity) || (event.getDamager() instanceof Player)) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity) event.getDamager();
        // 获取生物或方块的自定义名称，若无则返回null.
        if (livingEntity.getCustomName() == null) {
            return;
        }
        // 判断被攻击的是玩家
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        // 怪物攻击玩家升级概率
        if (!ProbabilityUtil.getInstance().pickIndex(ConfigUtil.config.getDouble("levelProbability"))) {
            return;
        }
        // 获取当前等级
        int level = MonsterLevelUtil.getLevel(livingEntity);
        MessageApi.sendDebugMessage(player, player.getName() + " 正在攻击的" + livingEntity.getCustomName() + ",当前等级:" + level);
        if (level == -1) {
            return;
        }
        MonsterLevelUtil.setLevel(livingEntity, BaseUtil.getSeparatorCustomName(livingEntity.getCustomName(), "]"), level + 1);
        player.sendMessage("绝处逢生:" + livingEntity.getCustomName() + "攻击了你,它升级了...");
    }

    /**
     * 当一个实体受到另外一个实体伤害时触发该事件
     * 怪物被攻击瞬间移动打击
     *
     * @param event 事件
     */
    @EventHandler
    public void onEntityDamageByEntityTeleport(EntityDamageByEntityEvent event) {
        // 判断世界是否开启
        if (MonsterConstants.worlds == null || !MonsterConstants.worlds.contains(event.getDamager().getWorld().getName())) {
            return;
        }
        // 判断被攻击的是怪物 和 判断攻击的是玩家
        if (!(event.getEntity() instanceof LivingEntity) || (event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player)) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity) event.getEntity();
        // 获取生物或方块的自定义名称，若无则返回null.
        if (livingEntity.getCustomName() == null) {
            return;
        }
        Player player = (Player) event.getDamager();
        // 玩家攻击怪物,怪物瞬移的概率
        if (!ProbabilityUtil.getInstance().pickIndex(ConfigUtil.config.getDouble("teleport"))) {
            return;
        }
        livingEntity.teleport(player);
        player.sendMessage("愤怒出击:" + livingEntity.getCustomName() + "被攻击烦了,它瞬移过来有也要打死你...");
    }

}