package io.github.notaeris.comet.commands;

import io.github.notaeris.comet.CometPlugin;
import io.github.notaeris.comet.profile.Profile;
import io.github.notaeris.comet.util.CC;
import io.github.notaeris.comet.util.command.Command;
import io.github.notaeris.comet.util.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.stream.IntStream;

public class ChatCommands {

    @Command(name = "chat", permission = "comet.command.chat", playerOnly = true)
    public void execute(CommandArgs args) {
        if(args.length() < 1) {
            CometPlugin.get().getConfig().getStringList("command.chat.usage")
                    .forEach(string -> args.getPlayer().sendMessage(CC.translate(string)));
            return;
        }

        if(args.getArgs(0).equalsIgnoreCase("clear")) {
            this.clear();
        }

        if(args.getArgs(0).equalsIgnoreCase("mute")) {
            this.mute(Profile.findProfile(args.getPlayer().getUniqueId()));
        }
    }

    /**
     * Clear the chat
     */
    private void clear() {
        IntStream.range(0, CometPlugin.get().getConfig().getInt("command.chat.clear.lines"))
                .forEach(i -> Bukkit.getOnlinePlayers().stream()
                .filter(player -> !player.hasPermission("cobra.staff"))
                .forEach(player -> player.sendMessage("")));

        Bukkit.broadcastMessage(CC.translate(CometPlugin.get().getConfig().getString("command.chat.clear.message")));
    }

    /**
     * Mute the chat
     */
    private void mute(Profile profile) {
        if(!profile.isChatToggled()) {
            profile.setChatToggled(true);
            Bukkit.broadcastMessage(CC.translate(CometPlugin.get().getConfig().getString("command.chat.mute.disabled")));
            return;
        }
        profile.setChatToggled(false);
        Bukkit.broadcastMessage(CC.translate(CometPlugin.get().getConfig().getString("command.chat.mute.enabled")));
    }
}
