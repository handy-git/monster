package com.handy.monster;

import com.handy.monster.command.MonsterCommand;
import com.handy.monster.listener.MonsterLevelListener;
import com.handy.monster.listener.MonsterListener;
import com.handy.monster.utils.ConfigUtil;
import org.bukkit.Bukkit;
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

        Bukkit.getPluginCommand("monster").setExecutor(new MonsterCommand());

        Bukkit.getPluginManager().registerEvents(new MonsterListener(), this);
        Bukkit.getPluginManager().registerEvents(new MonsterLevelListener(), this);

        this.getLogger().info("Monster插件开启");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Monster插件关闭");
    }

    public static Monster getInstance() {
        return instance;
    }

}
