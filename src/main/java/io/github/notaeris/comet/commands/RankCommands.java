package io.github.notaeris.comet.commands;

import io.github.notaeris.comet.CometPlugin;
import io.github.notaeris.comet.rank.Rank;
import io.github.notaeris.comet.util.CC;
import io.github.notaeris.comet.util.command.Command;
import io.github.notaeris.comet.util.command.CommandArgs;
import org.bukkit.entity.Player;

public class RankCommands {

    @Command(name = "rank", permission = "comet.command.rank", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(args.length() == 0) {
            CometPlugin.get().getConfig().getStringList("command.rank.usage")
                    .forEach(string -> player.sendMessage(CC.translate(string)));
            return;
        }

        if(args.getArgs(0).equalsIgnoreCase("create")) {
            if(args.length() < 2) {
                player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.create.usage")));
                return;
            }
            this.create(args.getArgs(1), player);
            return;
        }

        if(args.getArgs(0).equalsIgnoreCase("delete")) {
            if(args.length() < 2) {
                player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.delete.usage")));
                return;
            }
            this.delete(Rank.findRank(args.getArgs(1)), player);
            return;
        }

        if(args.getArgs(0).equalsIgnoreCase("setdisplayname")) {
            if(args.length() < 3) {
                player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.setdisplayname.usage")));
                return;
            }
            this.setDisplayName(Rank.findRank(args.getArgs(1)), args.getArgs(2), player);
            return;
        }

        if(args.getArgs(0).equalsIgnoreCase("setcolor")) {
            if(args.length() < 3) {
                player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.setcolor.usage")));
                return;
            }
            this.setColor(Rank.findRank(args.getArgs(1)), args.getArgs(2), player);
            return;
        }

        if(args.getArgs(0).equalsIgnoreCase("setprefix")) {
            if(args.length() < 3) {
                player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.setprefix.usage")));
                return;
            }
            this.setPrefix(Rank.findRank(args.getArgs(1)), args.getArgs(2), player);
            return;
        }

        if(args.getArgs(0).equalsIgnoreCase("setweight")) {
            if(args.length() < 3) {
                player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.setweight.usage")));
                return;
            }
            this.setWeight(Rank.findRank(args.getArgs(1)), Integer.parseInt(args.getArgs(2)), player);
            return;
        }

        if(args.getArgs(0).equalsIgnoreCase("info")) {
            if(args.length() < 2) {
                player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.info.usage")));
                return;
            }
            this.info(Rank.findRank(args.getArgs(1)), player);
        }
    }

    /**
     * Create a rank
     *
     * @param name the name
     * @param player the player
     */
    private void create(String name, Player player) {
        if(Rank.findRank(name) != null) {
            player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.already_exists")));
            return;
        }
        player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.create.message"))
                .replace("%rank%", new Rank(name).getName()));
    }

    /**
     * Delete a rank
     *
     * @param rank the rank
     * @param player the player
     */
    private void delete(Rank rank, Player player) {
        if(rank == null) {
            player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.doesnt_exist")));
            return;
        }
        rank.deleteRank();
        player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.delete.message"))
                .replace("%displayName%", CC.translate(rank.getDisplayName())));
    }

    /**
     * Set a rank display name
     *
     * @param rank the rank
     * @param displayName the displayName
     * @param player the player
     */
    private void setDisplayName(Rank rank, String displayName, Player player) {
        if(rank == null) {
            player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.doesnt_exist")));
            return;
        }
        rank.setDisplayName(displayName);
        player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.setdisplayname.message"))
                .replace("%rank%", rank.getName())
                .replace("%displayName%", CC.translate(rank.getDisplayName())));
    }

    /**
     * Set a rank color
     *
     * @param rank the rank
     * @param color the color
     * @param player the player
     */
    private void setColor(Rank rank, String color, Player player) {
        if(rank == null) {
            player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.doesnt_exist")));
            return;
        }
        rank.setColor(color);
        player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.setcolor.message"))
                .replace("%displayName%", CC.translate(rank.getDisplayName()))
                .replace("%player%", CC.translate(rank.getColor()) + player.getName()));
    }

    /**
     * Set a rank prefix
     *
     * @param rank the rank
     * @param prefix the prefix
     * @param player the player
     */
    private void setPrefix(Rank rank, String prefix, Player player) {
        if(rank == null) {
            player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.doesnt_exist")));
            return;
        }
        rank.setPrefix(prefix);
        player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.setprefix.message"))
                .replace("%displayName%", CC.translate(rank.getDisplayName()))
                .replace("%prefix%", CC.translate(rank.getPrefix())));
    }

    /**
     * Set a rank weight
     *
     * @param rank the rank
     * @param weight the weight
     * @param player the player
     */
    private void setWeight(Rank rank, int weight, Player player) {
        if(rank == null) {
            player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.doesnt_exist")));
            return;
        }
        rank.setWeight(weight);
        player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.setweight.message"))
                .replace("%displayName%", CC.translate(rank.getDisplayName()))
                .replace("%weight%", Integer.toString(rank.getWeight())));
    }

    /**
     * Get Info on a rank
     *
     * @param rank the rank
     * @param player the player
     */
    private void info(Rank rank, Player player) {
        if(rank == null) {
            player.sendMessage(CC.translate(CometPlugin.get().getConfig().getString("command.rank.doesnt_exist")));
            return;
        }
        CometPlugin.get().getConfig().getStringList("command.rank.info.message")
                .forEach(string -> player.sendMessage(CC.translate(string
                        .replace("%displayName%", CC.translate(rank.getDisplayName())))
                        .replace("%color%", CC.translate(rank.getColor()) + player.getName())
                        .replace("%prefix%", CC.translate(rank.getPrefix()))
                        .replace("%weight%", Integer.toString(rank.getWeight()))));
    }
}
