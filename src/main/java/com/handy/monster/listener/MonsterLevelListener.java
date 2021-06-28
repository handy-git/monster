package com.handy.monster.listener;

import com.handy.lib.util.BaseUtil;
import com.handy.lib.util.ProbabilityUtil;
import com.handy.monster.constant.MonsterConstants;
import com.handy.monster.utils.ConfigUtil;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @author hs
 * @date 2020/4/4 20:10
 */
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

        // 随机没随机到0
        if (MonsterConstants.levelProbability.randomIndex() != 0) {
            return;
        }
        // 判断攻击的是怪物
        if (!(event.getDamager() instanceof LivingEntity)) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity) event.getDamager();
        // 判断被攻击的是玩家
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();

        // 获取生物或方块的自定义名称，若无则返回null.
        if (livingEntity.getCustomName() == null) {
            return;
        }

        // 获取当前等级
        int level = BaseUtil.getSeparatorCustomNameNumber(livingEntity.getCustomName());

        // boss不在继续升级
        if (level == -1) {
            return;
        }

        AttributeInstance healthAttribute = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        AttributeInstance damageAttribute = livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        if (healthAttribute == null || damageAttribute == null) {
            return;
        }

        // 升级
        if (MonsterConstants.levelElite.randomIndex() == 0) {
            healthAttribute.setBaseValue(MonsterConstants.levelEliteHealth);
            damageAttribute.setBaseValue(MonsterConstants.levelEliteDamage);
            livingEntity.setHealth(MonsterConstants.levelEliteHealth);
            livingEntity.setCustomName(ChatColor.AQUA + "[BOSS]" + ChatColor.WHITE + BaseUtil.getSeparatorCustomName(livingEntity.getCustomName(), "]"));
            player.sendMessage("绝处逢生:" + livingEntity.getCustomName() + "攻击了你,它进化为了BOSS...");
        } else {
            healthAttribute.setBaseValue(healthAttribute.getValue() + MonsterConstants.levelHealth);
            damageAttribute.setBaseValue(damageAttribute.getValue() + MonsterConstants.levelDamage);
            livingEntity.setHealth(healthAttribute.getValue());
            livingEntity.setCustomName(ChatColor.AQUA + "[" + (level + 1) + "级]" + ChatColor.WHITE + BaseUtil.getSeparatorCustomName(livingEntity.getCustomName(), "]"));
            player.sendMessage("绝处逢生:" + livingEntity.getCustomName() + "攻击了你,它升级了...");
        }
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
        // 判断攻击的是玩家
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getDamager();

        // 判断被攻击的是怪物
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity) event.getEntity();

        // 获取生物或方块的自定义名称，若无则返回null.
        if (livingEntity.getCustomName() == null) {
            return;
        }

        // 随机没随机到0
        if (MonsterConstants.teleport.randomIndex() != 0) {
            return;
        }

        // 获取当前等级
        int level = BaseUtil.getSeparatorCustomNameNumber(livingEntity.getCustomName());
        if (level == -1) {
            return;
        }
        livingEntity.teleport(player);
        player.sendMessage("愤怒出击:" + livingEntity.getCustomName() + "被攻击烦了,它瞬移过来有也要打死你...");
    }

}
