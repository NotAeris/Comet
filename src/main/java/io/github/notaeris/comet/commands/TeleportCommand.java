package io.github.notaeris.comet.commands;

import io.github.notaeris.comet.CometPlugin;
import io.github.notaeris.comet.profile.Profile;
import io.github.notaeris.comet.util.CC;
import io.github.notaeris.comet.util.command.Command;
import io.github.notaeris.comet.util.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TeleportCommand {

    @Command(name = "teleport", aliases = { "tp" }, permission = "comet.command.teleport", playerOnly = true)
    public void execute(CommandArgs args) {
        if(args.length() == 0) {
            args.getPlayer().sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.teleport.usage"))
                    .replace("%command%", args.getLabel()));
            return;
        }

        this.teleport(args.getPlayer(), Bukkit.getPlayer(args.getArgs(0)));
    }

    @Command(name = "teleporthere", aliases = { "tphere" }, permission = "comet.command.teleporthere", playerOnly = true)
    public void execute2(CommandArgs args) {
        if(args.length() < 1) {
            args.getPlayer().sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.teleport.usage"))
                    .replace("%command%", args.getLabel()));
            return;
        }

        this.teleporthere(args.getPlayer(), Bukkit.getPlayer(args.getArgs(0)));
    }

    /**
     * Teleport to a player
     *
     * @param player the player
     * @param target the target
     */
    private void teleport(Player player, Player target) {
        player.teleport(target);

        player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.teleport.message"))
                .replace("%target%", CC.translate(Profile.findProfile(target.getUniqueId()).getDisplayName()) + target.getName()));
    }

    /**
     * Teleport a player to you
     *
     * @param target the target
     * @param player the player
     */
    private void teleporthere(Player target, Player player) {
        target.teleport(player);

        target.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.teleport.here.target"))
                .replace("%player%", CC.translate(Profile.findProfile(player.getUniqueId()).getDisplayName()) + player.getName()));

        player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.teleport.here.message"))
                .replace("%target%", CC.translate(Profile.findProfile(target.getUniqueId()).getDisplayName()) + target.getName())
                .replace("%player%", CC.translate(Profile.findProfile(player.getUniqueId()).getDisplayName()) + player.getName()));
    }
}
