package com.handy.monster.command;

import com.handy.monster.constant.MonsterConstants;
import com.handy.monster.utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * 关闭怪物加强
 *
 * @author handy
 */
public class OffCommand {

    private OffCommand() {
    }

    private static class SingletonHolder {
        private static final OffCommand INSTANCE = new OffCommand();
    }

    public static OffCommand getInstance() {
        return OffCommand.SingletonHolder.INSTANCE;
    }

    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        MonsterConstants.isUse = false;
        sender.sendMessage(ChatColor.AQUA + "怪物加强关闭成功!");
        String monsterOffMsg = ConfigUtil.config.getString("monsterOffMsg");
        Bukkit.broadcastMessage(monsterOffMsg != null ? monsterOffMsg : "");
    }

}
