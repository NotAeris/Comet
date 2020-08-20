package io.github.notaeris.comet.commands;

import io.github.notaeris.comet.profile.Profile;
import io.github.notaeris.comet.rank.Rank;
import io.github.notaeris.comet.util.CC;
import io.github.notaeris.comet.util.command.Command;
import io.github.notaeris.comet.util.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Comparator;
import java.util.stream.Collectors;

public class ListCommand {

    @Command(name = "list", aliases = { "online " } )
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        player.sendMessage(CC.translate(Rank.getRanks().stream()
                .sorted(Comparator.comparingInt(Rank::getWeight).reversed())
                .map(Rank::getDisplayName)
                .collect(Collectors.joining(CC.translate("&f, ")))));

        player.sendMessage(CC.translate("(" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers()) + "): " + Bukkit.getOnlinePlayers().stream()
                .map(target -> Profile.findProfile(target.getUniqueId()))
                .sorted(Comparator.comparingInt(profile -> ((Profile) profile).getRank().getWeight()).reversed())
                .map(profile -> CC.translate(profile.getDisplayName()) + player.getName())
                .collect(Collectors.joining(CC.translate("&f, "))));
    }
}
