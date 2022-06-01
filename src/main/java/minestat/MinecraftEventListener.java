package main.java.minestat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MinecraftEventListener implements Listener {

	private MineStat plugin = MineStat.getPlugin(MineStat.class);
	
	private static int online = 0;
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
		int total = plugin.getServer().getMaxPlayers();
		online++;
		plugin.getLogger().info("Player has joined - (" + online + "/" + total + ")");
		Discord.SetMemberCount(online, total);
		Discord.UpdateMessage();
    }
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
		int total = plugin.getServer().getMaxPlayers();
		online--;
		plugin.getLogger().info("Player has left - (" + online + "/" + total + ")");
		Discord.SetMemberCount(online, total);
		Discord.UpdateMessage();
    }
	
}
