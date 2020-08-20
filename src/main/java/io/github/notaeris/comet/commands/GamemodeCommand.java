package io.github.notaeris.comet.commands;

import io.github.notaeris.comet.CometPlugin;
import io.github.notaeris.comet.profile.Profile;
import io.github.notaeris.comet.util.CC;
import io.github.notaeris.comet.util.command.Command;
import io.github.notaeris.comet.util.command.CommandArgs;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GamemodeCommand {

    @Command(name = "gamemode", aliases = { "gm" }, permission = "comet.command.gamemode", playerOnly = true )
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(args.length() == 0) {
            player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.gamemode.usage"))
                    .replace("%command%", args.getLabel()));
            return;
        }
        GameMode gameMode = this.getGameMode(args.getArgs(0));

        if(gameMode == null) {
            player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.gamemode.doesnt_exist")));
            return;
        }

        if(player.getGameMode() == gameMode) {
            player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.gamemode.already_in_gamemode")));
            return;
        }

        player.setGameMode(gameMode);
        player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.gamemode.message"))
                .replace("%player%", CC.translate(Profile.findProfile(player.getUniqueId()).getDisplayName()) + player.getName())
                .replace("%gamemode%", gameMode.name().toUpperCase()));
    }

    /**
     * Get a gamemode
     *
     * @param string the string
     * @return the gamemode
     */
    private GameMode getGameMode(String string) {
        GameMode gameMode = null;

        switch(string) {
            case "s":
            case "survival":
            case "0": {
                gameMode = GameMode.SURVIVAL;
                break;
            }
            case "c":
            case "creative":
            case "1": {
                gameMode = GameMode.CREATIVE;
                break;
            }
            case "a":
            case "adventure":
            case "2": {
                gameMode = GameMode.ADVENTURE;
                break;
            }
        }
        return gameMode;
    }
}
