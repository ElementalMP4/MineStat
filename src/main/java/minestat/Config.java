package main.java.minestat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public class Config {
	
	private MineStat plugin = MineStat.getPlugin(MineStat.class);
	private Properties config = new Properties();
	private File configFile = new File(plugin.getDataFolder(), "config.properties");
	
	public Config() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
		if (configFile.exists()) {
			try (FileInputStream fis = new FileInputStream(configFile)){
				config.load(fis);
			} catch (IOException e) {
				plugin.getLogger().log(Level.SEVERE, "Couldn't read config file!");
			}		
		} else {
			plugin.getLogger().info("No config file found. Using defaults.");
		}
	}
	
	public String getToken() {
		return config.getProperty("token");
	}
	
	public String getChannelID() {
		return config.getProperty("channel");
	}
	
	public String getMessageID() {
		return config.getProperty("message");
	}
	
	public String getEmbedColour() {
		String colour = config.getProperty("colour");
		return  colour == null ? "000000" : colour.replace("#", "");
	}
	
	public void setMessageID(String messageID) {
		config.setProperty("message", messageID);
		saveConfig();
	}
	
	private void saveConfig() {
		try {
			config.store(new FileOutputStream(configFile), null);
		} catch (IOException e) {
			plugin.getLogger().severe("Couldn't save config file: " + e.getMessage());
		}
	}

	public String getEmbedDescription() {
		String description = config.getProperty("description");
		return description == null ? "" : description;
	}

	public String getWebsite() {
		return config.getProperty("website");
	}
	
	public String getServerLink() {
		return config.getProperty("server-ip");
	}
	
	public String getServerName() {
		String name = config.getProperty("name");
		return name == null ? "Minecraft Server" : name;
	}

}