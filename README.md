# MineStat

Shows live Minecraft Server information in Discord!

## Installation

1) Download the latest jar from the releases tab
2) Place `MineStat` in your `plugins` folder
3) Create a folder inside `plugins` called `MineStat` and add a file called `config.properties`
4) Complete your config

## Configuration

The following config options are supported:

```
channel=discord-text-channel-id (required)
token=discord-bot-token (required)
description=Server Description (optional)
website=https://server.com/ (optional)
server-ip=mc.server.com (optional)
name=A Funky Server (optional)
```

The configuration options are set in `MineStat/config.properties`. It is required to create the config before running MineStat as it will not be able to connect to discord.