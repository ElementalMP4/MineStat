package main.java.minestat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MinecraftEventListener implements Listener {

	private static MineStat plugin = MineStat.getPlugin(MineStat.class);
	private static final int TOTAL = plugin.getServer().getMaxPlayers();
	
	private static int online = 0;
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
		online++;
		Discord.SetMemberCount(online, TOTAL);
		Discord.UpdateMessage();
    }
	
	@EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
		online--;
		Discord.SetMemberCount(online, TOTAL);
		Discord.UpdateMessage();
    }
	
}
