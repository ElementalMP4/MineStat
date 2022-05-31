# MineStat

Shows live Minecraft Server information in Discord!

## Configuration

The following config options are supported:

```
channel=discord-text-channel-id (required)
token=discord-bot-token (required)
colour=hex-colour (optional)
description=Server Description (optional)
website=https://server.com/ (optional)
server-ip=mc.server.com (optional)
```

The configuration options are set in `MineStat/config.properties`. It is required to create the config before running MineStat as it will not be able to connect to discord.