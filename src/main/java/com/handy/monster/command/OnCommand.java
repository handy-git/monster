package com.handy.monster.command;

import com.handy.lib.util.BaseUtil;
import com.handy.monster.Monster;
import com.handy.monster.constant.MonsterConstants;
import com.handy.monster.utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * 开启怪物加强
 *
 * @author handy
 */
public class OnCommand {
    private OnCommand() {
    }

    private static class SingletonHolder {
        private static final OnCommand INSTANCE = new OnCommand();
    }

    public static OnCommand getInstance() {
        return OnCommand.SingletonHolder.INSTANCE;
    }

    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            MonsterConstants.isUse = true;
            sender.sendMessage(ChatColor.AQUA + "怪物加强开启成功!");
            String monsterOnMsg = ConfigUtil.config.getString("monsterOnMsg");
            Bukkit.broadcastMessage(monsterOnMsg != null ? monsterOnMsg : "");
            return;
        }
        Integer num = BaseUtil.isNumericToInt(args[1]);
        if (num == null) {
            sender.sendMessage(BaseUtil.replaceChatColor("&4子参数只能为数字,单位为秒"));
            return;
        }
        MonsterConstants.isUse = true;
        sender.sendMessage(ChatColor.AQUA + "怪物加强开启成功,持续时间：" + num);
        String monsterOnMsg = ConfigUtil.config.getString("monsterOnMsg");
        Bukkit.broadcastMessage(monsterOnMsg != null ? monsterOnMsg : "");
        (new BukkitRunnable() {
            @Override
            public void run() {
                MonsterConstants.isUse = false;
                sender.sendMessage(ChatColor.AQUA + "怪物加强到期自动关闭成功!");
                String monsterOffMsg = ConfigUtil.config.getString("monsterOffMsg");
                Bukkit.broadcastMessage(monsterOffMsg != null ? monsterOffMsg : "");
            }
        }).runTaskLater(Monster.getInstance(), (num * 20L));
    }

}
