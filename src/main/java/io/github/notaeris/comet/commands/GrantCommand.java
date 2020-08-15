package io.github.notaeris.comet.commands;

import io.github.notaeris.comet.CometPlugin;
import io.github.notaeris.comet.grant.Grant;
import io.github.notaeris.comet.profile.Profile;
import io.github.notaeris.comet.rank.Rank;
import io.github.notaeris.comet.util.CC;
import io.github.notaeris.comet.util.command.Command;
import io.github.notaeris.comet.util.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GrantCommand {

    @Command(name = "grant", permission = "comet.command.grant", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(args.length() < 2) {
            player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.grant.usage")));
            return;
        }

        if(Bukkit.getPlayer(args.getArgs(0)) == null) {
            player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.player_offline")));
            return;
        }

        this.addGrant(Rank.findRank(args.getArgs(1)), Profile.findProfile(Bukkit.getPlayer(args.getArgs(0)).getUniqueId()), Bukkit.getPlayer(args.getArgs(0)), player);
    }

    /**
     * Add a grant
     *
     * @param rank the rank
     * @param target the target
     * @param profile the profile
     * @param player the player
     */
    private void addGrant(Rank rank, Profile profile, Player target, Player player) {
        if(rank == null) {
            player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.doesnt_exist")));
            return;
        }
        profile.addGrant(new Grant(rank, profile));

        player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.grant.message"))
                .replace("%target%", target.getName())
                .replace("%displayName%", CC.translate(rank.getDisplayName())));
    }
}
