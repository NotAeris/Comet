package io.github.notaeris.comet;

import io.github.notaeris.comet.commands.*;
import io.github.notaeris.comet.listeners.PlayerListener;
import io.github.notaeris.comet.util.command.CommandFramework;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class CometPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Arrays.asList(
                new RankCommands(),
                new GrantCommand(),
                new ListCommand(),
                new GamemodeCommand(),
                new TeleportCommand(),
                new StaffChatCommand(),
                new RequestCommand(),
                new ReportCommand(),
                new ChatCommands()
        ).forEach(new CommandFramework(this)::registerCommands);

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    /**
     * Get the main class
     *
     * @return the class
     */
    public static CometPlugin get() {
        return getPlugin(CometPlugin.class);
    }
}
