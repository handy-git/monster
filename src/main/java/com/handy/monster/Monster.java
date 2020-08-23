package com.handy.monster;

import com.handy.monster.command.MonsterCommand;
import com.handy.monster.listener.MonsterLevelListener;
import com.handy.monster.listener.MonsterListener;
import com.handy.monster.utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Monster extends JavaPlugin {
    public static Plugin plugin;

    public Monster() {
    }

    @Override
    public void onEnable() {
        plugin = this;
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
}
