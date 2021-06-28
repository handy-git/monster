package com.handy.monster.command;

import com.handy.monster.constant.TabListEnum;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 指令
 *
 * @author handy
 * @date 2020/10/10 15:00
 */
public class MonsterCommand implements TabExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        // 判断指令是否正确
        if (args.length < 1) {
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "on":
                if (!sender.hasPermission("monster.on")) {
                    sender.sendMessage("你有没权限执行该命令");
                    return true;
                }
                OnCommand.getInstance().onCommand(sender, cmd, label, args);
                break;
            case "off":
                if (!sender.hasPermission("monster.off")) {
                    sender.sendMessage("你有没权限执行该命令");
                    return true;
                }
                OffCommand.getInstance().onCommand(sender, cmd, label, args);
                break;
            case "reload":
                if (!sender.hasPermission("monster.reload")) {
                    sender.sendMessage("你有没权限执行该命令");
                    return true;
                }
                ReloadCommand.getInstance().onCommand(sender, cmd, label, args);
                break;
            default:
                return true;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = TabListEnum.returnList(args, args.length);
        if (commands == null) {
            return null;
        }
        StringUtil.copyPartialMatches(args[args.length - 1].toLowerCase(), commands, completions);
        Collections.sort(completions);
        return completions;
    }

}