package com.handy.monster;

import com.handy.lib.InitApi;
import com.handy.lib.api.MessageApi;
import com.handy.monster.util.ConfigUtil;
import com.handy.monster.util.TaskUtil;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 主类
 *
 * @author handy
 */
public final class Monster extends JavaPlugin {
    public static Monster instance;

    @Override
    public void onEnable() {
        instance = this;
        ConfigUtil.getConfig();
        InitApi.getInstance(this)
                .initCommand("com.handy.monster.command")
                .initListener("com.handy.monster.listener")
        ;
        // 设置怪物生成
        TaskUtil.setAsyncMonsterSpawn();
        TaskUtil.clearPlayer();
        MessageApi.sendConsoleMessage(this, "&aMonster插件成功开启");
    }

    @Override
    public void onDisable() {
        MessageApi.sendConsoleMessage(this, "&aMonster插件成功关闭");
    }

    public static Monster getInstance() {
        return instance;
    }

}
