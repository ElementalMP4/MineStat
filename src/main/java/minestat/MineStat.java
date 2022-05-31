package main.java.minestat;

import org.bukkit.plugin.java.JavaPlugin;

public class MineStat extends JavaPlugin {
    @Override
    public void onEnable() {
    	Discord.ConnectToDiscord();
    	getServer().getPluginManager().registerEvents(new MinecraftEventListener(), this);
        getLogger().info("MineStat is alive");
    }
    
    @Override
    public void onDisable() {
    	Discord.SetServerStatus(false);
		Discord.SetMemberCount(0, getServer().getMaxPlayers());
		Discord.UpdateMessage();
        getLogger().info("MineStat is offline");
    }
}