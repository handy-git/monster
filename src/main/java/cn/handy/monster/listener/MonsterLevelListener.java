package cn.handy.monster.listener;

import cn.handy.monster.constant.MonsterConstants;
import cn.handy.monster.utils.BaseUtil;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class MonsterLevelListener implements Listener {
    public MonsterLevelListener() {
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (MonsterConstants.levelProbability.randomColunmIndex() == 0) {
            if (event.getDamager() instanceof LivingEntity && event.getEntity() instanceof Player) {
                LivingEntity livingEntity = (LivingEntity)event.getDamager();
                Player player = (Player)event.getEntity();
                if (livingEntity.getCustomName() != null) {
                    int level = BaseUtil.isNumber(livingEntity.getCustomName());
                    if (level != -1) {
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
                }
            }

        }
    }

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity.getCustomName() != null) {
            int level = BaseUtil.isNumber(livingEntity.getCustomName());
            if (level != -1) {
                event.setDroppedExp(event.getDroppedExp() + level);
            }

            if (livingEntity.getCustomName().contains("BOSS")) {
                event.setDroppedExp(event.getDroppedExp() + 100);
            }
        }

    }
}
