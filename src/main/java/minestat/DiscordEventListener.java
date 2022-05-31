package main.java.minestat;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class DiscordEventListener implements EventListener{

	private MineStat plugin = MineStat.getPlugin(MineStat.class);
	
	@Override
	public void onEvent(GenericEvent event) {
		if (event instanceof ReadyEvent) {
			Discord.SetServerStatus(true);
			Discord.SetMemberCount(plugin.getServer().getOnlinePlayers().size(), plugin.getServer().getMaxPlayers());
			Discord.UpdateMessage();
			return;
		}
	}

}
