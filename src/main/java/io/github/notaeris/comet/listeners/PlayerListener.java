package io.github.notaeris.comet.listeners;

import io.github.notaeris.comet.CometPlugin;
import io.github.notaeris.comet.grant.Grant;
import io.github.notaeris.comet.profile.Profile;
import io.github.notaeris.comet.rank.Rank;
import io.github.notaeris.comet.util.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        if(Profile.findProfile(event.getPlayer().getUniqueId()) == null) {
            new Profile(event.getPlayer().getUniqueId());
            Profile.findProfile(event.getPlayer().getUniqueId()).addGrant(new Grant(Rank.getDefaultRank(), Profile.findProfile(event.getPlayer().getUniqueId())));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if(Profile.findProfile(player.getUniqueId()).isChatToggled()) {
            if(!player.hasPermission("comet.staff")) {
                event.setCancelled(true);
                player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.chat.mute.currently_enabled")));
                return;
            }
        }
        event.setFormat(CC.translate(Profile.findProfile(player.getUniqueId()).getGrants().get(0).getRank().getPrefix() + player.getName() + "&7: &f" + event.getMessage()));
    }
}
