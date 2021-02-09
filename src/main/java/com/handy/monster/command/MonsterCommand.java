package com.handy.monster.command;

import com.handy.monster.Monster;
import com.handy.monster.constant.MonsterConstants;
import com.handy.monster.utils.BaseUtil;
import com.handy.monster.utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author handy
 * @date 2020/10/10 15:00
 */
public class MonsterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, Command command, String s, String[] args) {
        if (!sender.hasPermission("handy.monster")) {
            sender.sendMessage(ChatColor.RED + "你没有该命令的权限!");
            return true;
        }
        String monsterOnMsg;
        if (args.length == 1) {
            switch (args[0]) {
                case "reload":
                    ConfigUtil.getConfig();
                    sender.sendMessage(ChatColor.AQUA + "monster重载成功!");
                    break;
                case "on":
                    MonsterConstants.isUse = true;
                    sender.sendMessage(ChatColor.AQUA + "怪物加强开启成功!");
                    monsterOnMsg = ConfigUtil.config.getString("monsterOnMsg");
                    Bukkit.broadcastMessage(monsterOnMsg != null ? monsterOnMsg : "");
                    break;
                case "off":
                    MonsterConstants.isUse = false;
                    sender.sendMessage(ChatColor.AQUA + "怪物加强关闭成功!");
                    monsterOnMsg = ConfigUtil.config.getString("monsterOffMsg");
                    Bukkit.broadcastMessage(monsterOnMsg != null ? monsterOnMsg : "");
                    break;
                default:
                    break;
            }
        }

        if (args.length == 2) {
            if ("on".equalsIgnoreCase(args[0])) {
                if (!BaseUtil.isNumeric(args[1])) {
                    sender.sendMessage("子参数只能为开启时间,单位为秒");
                    return true;
                }

                MonsterConstants.isUse = true;
                sender.sendMessage(ChatColor.AQUA + "怪物加强开启成功!");
                monsterOnMsg = ConfigUtil.config.getString("monsterOnMsg");
                Bukkit.broadcastMessage(monsterOnMsg != null ? monsterOnMsg : "");
                (new BukkitRunnable() {
                    public void run() {
                        MonsterConstants.isUse = false;
                        sender.sendMessage(ChatColor.AQUA + "怪物加强关闭成功!");
                        String monsterOffMsg = ConfigUtil.config.getString("monsterOffMsg");
                        Bukkit.broadcastMessage(monsterOffMsg != null ? monsterOffMsg : "");
                    }
                }).runTaskLater(Monster.getInstance(), (Integer.parseInt(args[1]) * 20L));
            } else {
                sender.sendMessage(ChatColor.AQUA + "子参数错误,格式/monster on或/monster off或/monster on [秒]");
            }
        } else {
            sender.sendMessage(ChatColor.AQUA + "子参数错误,格式/monster on或/monster off或/monster on [秒]");
        }
        return true;
    }

}