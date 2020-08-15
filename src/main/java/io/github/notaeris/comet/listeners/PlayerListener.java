package io.github.notaeris.comet.listeners;

import io.github.notaeris.comet.profile.Profile;
import io.github.notaeris.comet.util.CC;
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
            Profile.findProfile(event.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        event.setFormat(CC.translate(Profile.findProfile(event.getPlayer().getUniqueId()).getGrants().get(0).getRank().getPrefix() + event.getPlayer().getName() + "&7: &f" + event.getMessage()));
    }
}
