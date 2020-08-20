package io.github.notaeris.comet.rank;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class Rank {

    private final UUID uuid;
    private String name, displayName, color, prefix;
    private int weight;

    @Getter private static final List<Rank> ranks = new ArrayList<>();

    /**
     * Main constructor
     *
     * @param name the name
     */
    public Rank(String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.displayName = name;
        this.color = "";
        this.prefix = "";
        this.weight = 0;

        ranks.add(this);
    }

    /**
     * Delete a rank
     */
    public void deleteRank() {
        ranks.remove(this);
    }

    public static Rank getDefaultRank() {
        if(findRank("Default") == null) {
            Rank rank = new Rank("Default");

            rank.setDisplayName("&fDefault");
            return rank;
        }
        return findRank("Default");
    }

    /**
     * Find a rank
     *
     * @param name the name
     * @return the rank name
     */
    public static Rank findRank(String name) {
        return ranks.stream()
                .filter(rank -> rank.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}
