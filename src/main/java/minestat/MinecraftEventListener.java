package main.java.minestat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MinecraftEventListener implements Listener {

	private MineStat plugin = MineStat.getPlugin(MineStat.class);
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
		Discord.SetMemberCount(plugin.getServer().getOnlinePlayers().size(), plugin.getServer().getMaxPlayers());
		Discord.UpdateMessage();
    }
	
	@EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
		Discord.SetMemberCount(plugin.getServer().getOnlinePlayers().size(), plugin.getServer().getMaxPlayers());
		Discord.UpdateMessage();
    }
	
}
