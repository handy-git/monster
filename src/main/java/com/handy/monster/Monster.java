package com.handy.monster;

import com.handy.monster.command.MonsterCommand;
import com.handy.monster.listener.ListenerManage;
import com.handy.monster.utils.ConfigUtil;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author handy
 * @date 2020/10/10 15:00
 */
public final class Monster extends JavaPlugin {
    public static Monster instance;

    @Override
    public void onEnable() {
        instance = this;
        ConfigUtil.getConfig();
        // 加载命令
        PluginCommand monsterPluginCommand = this.getCommand("monster");
        if (monsterPluginCommand != null) {
            monsterPluginCommand.setExecutor(new MonsterCommand());
            monsterPluginCommand.setTabCompleter(new MonsterCommand());
        }
        // 加载监听器
        ListenerManage.enableListener(this);
        this.getLogger().info("Monster插件成功开启");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Monster插件成功关闭");
    }

    public static Monster getInstance() {
        return instance;
    }

}
