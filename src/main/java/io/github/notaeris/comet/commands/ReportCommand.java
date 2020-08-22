package io.github.notaeris.comet.commands;

import io.github.notaeris.comet.CometPlugin;
import io.github.notaeris.comet.profile.Profile;
import io.github.notaeris.comet.util.CC;
import io.github.notaeris.comet.util.command.Command;
import io.github.notaeris.comet.util.command.CommandArgs;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReportCommand {

    @Command(name = "report", playerOnly = true)
    public void execute(CommandArgs args) {
        if(args.length() < 2) {
            args.getPlayer().sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.report.usage")));
            return;
        }

        this.report(Bukkit.getPlayer(args.getArgs(0)), args.getPlayer(), args.getArgs());
    }

    /**
     * Report a player
     *
     * @param target the target
     * @param player the player
     * @param args the args
     */
    private void report(Player target, Player player, String[] args) {
        if(target == null) {
            player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.player_offline")));
            return;
        }

        CometPlugin.get().getConfig().getStringList("command.report.format")
                .forEach(string -> Bukkit.getOnlinePlayers()
                        .forEach(online -> {
                            if(online.hasPermission("comet.staff")) {
                                online.sendMessage(CC.translate(string
                                        .replace("%server%", CometPlugin.get().getConfig().getString("server_name")))
                                        .replace("%player%", CC.translate(Profile.findProfile(player.getUniqueId()).getDisplayName() + player.getName()))
                                        .replace("%target%", CC.translate(Profile.findProfile(target.getUniqueId()).getDisplayName() + target.getName()))
                                        .replace("%message%", StringUtils.join(args, ' ', 1, args.length)));
                            }
                        }));

        player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.report.sent")));
    }
}
