package com.handy.monster.listener;

import com.handy.monster.constant.MonsterConstants;
import com.handy.monster.utils.BaseUtil;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * @author hs
 * @date 2020/4/4 20:10
 */
public class MonsterLevelListener implements Listener {

    /**
     * 当一个实体受到另外一个实体伤害时触发该事件
     *
     * @param event 事件
     */
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        // 随机没随机到0
        if (MonsterConstants.levelProbability.randomColunmIndex() != 0) {
            return;
        }
        // 不是怪物造成伤害
        if (!(event.getDamager() instanceof LivingEntity)) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity) event.getDamager();
        // 不是玩家造成伤害
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();

        // 获取生物或方块的自定义名称，若无则返回null.
        if (livingEntity.getCustomName() == null) {
            return;
        }

        // 获取当前等级
        int level = BaseUtil.isNumber(livingEntity.getCustomName());
        // boss不在继续升级
        if (level == -1) {
            return;
        }

        if (MonsterConstants.levelElite.randomColunmIndex() == 0) {
            livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(MonsterConstants.levelEliteHealth);
            livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(MonsterConstants.levelEliteDamage);
            livingEntity.setHealth(MonsterConstants.levelEliteHealth);
            livingEntity.setCustomName(ChatColor.AQUA + "[BOSS]" + ChatColor.WHITE + BaseUtil.getCustomName(livingEntity.getCustomName()));
            player.sendMessage("绝处逢生:" + livingEntity.getCustomName() + "攻击了你,它进化为了BOSS...");
        } else {
            livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + MonsterConstants.levelHealth);
            livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() + MonsterConstants.levelDamage);
            livingEntity.setHealth(livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            livingEntity.setCustomName(ChatColor.AQUA + "[" + (level + 1) + "级]" + ChatColor.WHITE + BaseUtil.getCustomName(livingEntity.getCustomName()));
            player.sendMessage("绝处逢生:" + livingEntity.getCustomName() + "攻击了你,它升级了...");
        }

    }

    /**
     * 当任何一个实体死亡时触发本事件
     *
     * @param event 事件
     */
    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        // 获取生物或方块的自定义名称，若无则返回null.
        if (livingEntity.getCustomName() == null) {
            return;
        }
        // 获取当前等级
        int level = BaseUtil.isNumber(livingEntity.getCustomName());

        // 掉落经验增加
        if (level != -1) {
            event.setDroppedExp(event.getDroppedExp() + level);
        }

        if (livingEntity.getCustomName().contains("BOSS")) {
            event.setDroppedExp(event.getDroppedExp() + 100);
        }

    }

}