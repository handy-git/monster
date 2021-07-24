package com.handy.monster.command;

import com.handy.lib.annotation.HandyCommand;
import com.handy.lib.annotation.HandySubCommand;
import com.handy.lib.command.HandyCommandFactory;
import com.handy.lib.util.BaseUtil;
import com.handy.monster.Monster;
import com.handy.monster.constant.MonsterConstants;
import com.handy.monster.utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 指令
 *
 * @author handy
 * @date 2020/10/10 15:00
 */
@HandyCommand(name = "monster", permission = "monster.monster")
public class MonsterCommand implements TabExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        // 判断指令是否正确
        if (args.length < 1) {
            return true;
        }
        HandyCommandFactory.getInstance().onSubCommand("monster", sender, cmd, label, args, "&4没有权限");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("on", "off", "reload");
        }
        if (args.length == 2 && "on".equals(args[0])) {
            return Collections.singletonList("请输入持续时间");
        }
        return new ArrayList<>();
    }

    @HandySubCommand(mainCommand = "monster", subCommand = "on", permission = "monster.on")
    public void onOnSubCommand(CommandSender sender, Command cmd, String label, String[] args) {
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

    @HandySubCommand(mainCommand = "monster", subCommand = "off", permission = "monster.on")
    public void onOffCommand(CommandSender sender, Command cmd, String label, String[] args) {
        MonsterConstants.isUse = false;
        sender.sendMessage(ChatColor.AQUA + "怪物加强关闭成功!");
        String monsterOffMsg = ConfigUtil.config.getString("monsterOffMsg");
        Bukkit.broadcastMessage(monsterOffMsg != null ? monsterOffMsg : "");
    }

    @HandySubCommand(mainCommand = "monster", subCommand = "reload", permission = "monster.reload")
    public void onReloadCommand(CommandSender sender, Command cmd, String label, String[] args) {
        ConfigUtil.getConfig();
        sender.sendMessage(ChatColor.AQUA + "monster重载成功!");
    }

}