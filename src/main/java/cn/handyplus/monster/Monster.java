package cn.handyplus.monster;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.monster.util.ConfigUtil;
import cn.handyplus.monster.util.TaskUtil;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 主类
 *
 * @author handy
 */
public final class Monster extends JavaPlugin {
    public static Monster INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        ConfigUtil.getConfig();
        InitApi.getInstance(this)
                .initCommand("cn.handyplus.monster.command")
                .initListener("cn.handyplus.monster.listener");
        // 设置怪物生成
        TaskUtil.setAsyncMonsterSpawn();
        TaskUtil.clearPlayer();

        MessageUtil.sendConsoleMessage(ChatColor.GREEN + "已成功载入服务器！");
        MessageUtil.sendConsoleMessage(ChatColor.GREEN + "Author:handy WIKI: https://ricedoc.handyplus.cn/wiki/monster/");
    }

    public static Monster getInstance() {
        return INSTANCE;
    }

}
