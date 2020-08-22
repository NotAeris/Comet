package io.github.notaeris.comet.commands;

import io.github.notaeris.comet.CometPlugin;
import io.github.notaeris.comet.profile.Profile;
import io.github.notaeris.comet.util.CC;
import io.github.notaeris.comet.util.command.Command;
import io.github.notaeris.comet.util.command.CommandArgs;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RequestCommand {

    @Command(name = "request", aliases = { "helpop" }, playerOnly = true)
    public void execute(CommandArgs args) {
        if(args.length() < 1) {
            args.getPlayer().sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.request.usage"))
                    .replace("%command%", args.getLabel()));
            return;
        }

        this.request(args.getPlayer(), args.getArgs());
    }

    /**
     * Request assistance
     *
     * @param player the player
     * @param args the args
     */
    private void request(Player player, String[] args) {
        CometPlugin.get().getConfig().getStringList("command.request.format")
                .forEach(string -> Bukkit.getOnlinePlayers()
                        .forEach(online -> {
                            if(online.hasPermission("comet.staff")) {
                                online.sendMessage(CC.translate(string
                                        .replace("%server%", CometPlugin.get().getConfig().getString("server_name")))
                                        .replace("%player%", CC.translate(Profile.findProfile(player.getUniqueId()).getDisplayName() + player.getName()))
                                        .replace("%message%", StringUtils.join(args, ' ', 0, args.length)));
                            }
                        }));

        player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.request.sent")));
    }
}
