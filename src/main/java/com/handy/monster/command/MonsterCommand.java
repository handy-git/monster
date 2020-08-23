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

public class MonsterCommand implements CommandExecutor {
    public MonsterCommand() {
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command command, String s, String[] args) {
        if (!sender.hasPermission("handy.monster")) {
            sender.sendMessage(ChatColor.RED + "你没有该命令的权限!");
            return true;
        } else {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                ConfigUtil.getConfig();
                sender.sendMessage(ChatColor.AQUA + "monster重载成功!");
            } else {
                String monsterOnMsg;
                if (args.length == 1 && args[0].equalsIgnoreCase("on")) {
                    MonsterConstants.isUse = true;
                    sender.sendMessage(ChatColor.AQUA + "怪物加强开启成功!");
                    monsterOnMsg = ConfigUtil.config.getString("monsterOnMsg");
                    Bukkit.broadcastMessage(monsterOnMsg != null ? monsterOnMsg : "");
                } else if (args.length == 1 && args[0].equalsIgnoreCase("off")) {
                    MonsterConstants.isUse = false;
                    sender.sendMessage(ChatColor.AQUA + "怪物加强关闭成功!");
                    monsterOnMsg = ConfigUtil.config.getString("monsterOffMsg");
                    Bukkit.broadcastMessage(monsterOnMsg != null ? monsterOnMsg : "");
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("on")) {
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
                        }).runTaskLater(Monster.plugin, (long)(Integer.parseInt(args[1]) * 20));
                    } else {
                        sender.sendMessage(ChatColor.AQUA + "子参数错误,格式/monster on或/monster off或/monster on [秒]");
                    }
                } else {
                    sender.sendMessage(ChatColor.AQUA + "子参数错误,格式/monster on或/monster off或/monster on [秒]");
                }
            }

            return true;
        }
    }
}