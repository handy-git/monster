package com.handy.monster.command;

import com.handy.monster.utils.ConfigUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * 重载配置
 *
 * @author handy
 */
public class ReloadCommand {
    private ReloadCommand() {
    }

    private static class SingletonHolder {
        private static final ReloadCommand INSTANCE = new ReloadCommand();
    }

    public static ReloadCommand getInstance() {
        return ReloadCommand.SingletonHolder.INSTANCE;
    }

    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        ConfigUtil.getConfig();
        sender.sendMessage(ChatColor.AQUA + "monster重载成功!");
    }

}
