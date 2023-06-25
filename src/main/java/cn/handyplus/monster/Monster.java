package cn.handyplus.monster;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.api.MessageApi;
import cn.handyplus.monster.util.ConfigUtil;
import cn.handyplus.monster.util.TaskUtil;
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
        MessageApi.sendConsoleMessage("&aMonster插件成功开启");
    }

    @Override
    public void onDisable() {
        MessageApi.sendConsoleMessage("&aMonster插件成功关闭");
    }

    public static Monster getInstance() {
        return INSTANCE;
    }

}
