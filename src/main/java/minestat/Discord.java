package main.java.minestat;

import java.awt.Color;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

public class Discord {
	
	private static MineStat plugin = MineStat.getPlugin(MineStat.class);
	private static Config config = new Config();
	private static JDA jda;
	
	private static boolean online;
	private static int playersOnline;
	private static int totalPlayers;
	
	public static void ConnectToDiscord() {
		try {
			JDABuilder builder = JDABuilder.createLight(config.getToken());
			builder.addEventListeners(new DiscordEventListener());
			jda = builder.build();
		} catch (LoginException e) {
			plugin.getLogger().severe("Couldn't connect to Discord: " + e.getMessage());
		}
	}
	
	public static void SetServerStatus(boolean onOrOff) {
		online = onOrOff;
	}
	
	public static void SetMemberCount(int membersOnline, int totalMembers) {
		playersOnline = membersOnline;
		totalPlayers = totalMembers;
	}
	
	private static Color GetEmbedColour(String hex) {
		return new Color(
	            Integer.valueOf(hex.substring(0, 2), 16),
	            Integer.valueOf(hex.substring(2, 4), 16),
	            Integer.valueOf(hex.substring(4, 6), 16));
	}
	
	private static String GetServerStatus(boolean online) {
		return online ? ":white_check_mark: **Online**" : ":no_entry: **Offline**";
	}
	
	public static void UpdateMessage() {
		TextChannel channel = jda.getTextChannelById(config.getChannelID());
		if (channel == null) {
			plugin.getLogger().severe("Channel has not been set properly in config. Please correct this.");
			return;
		}

		plugin.getLogger().info("Updating embed");
		
		EmbedBuilder embedBuilder = new EmbedBuilder()
				.setColor(GetEmbedColour(config.getEmbedColour()))
				.setTitle(config.getServerName() + " Information")
				.setDescription(config.getEmbedDescription())
				.setThumbnail(channel.getGuild().getIconUrl())
				.addField("Server Status", GetServerStatus(online), true)
				.addField("Players Online", String.valueOf(playersOnline) + "/" + String.valueOf(totalPlayers), true);
		
		if (config.getWebsite() != null) embedBuilder.addField("Website", "[**Click Here**](" + config.getWebsite() + ")", true);
		if (config.getServerLink() != null) embedBuilder.addField("Server IP", "```\n" + config.getServerLink() + "\n```", true);
		
		MessageEmbed embed = embedBuilder.build();
		
		if (config.getMessageID() == null) {
			Message message = channel.sendMessageEmbeds(embed).complete();
			config.setMessageID(message.getId());
		} else {
			Message message = channel.retrieveMessageById(config.getMessageID()).complete();
			message.editMessageEmbeds(embed).queue();	
		}
	}
}