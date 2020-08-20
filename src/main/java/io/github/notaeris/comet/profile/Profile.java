package io.github.notaeris.comet.profile;

import io.github.notaeris.comet.grant.Grant;
import io.github.notaeris.comet.rank.Rank;
import lombok.Getter;

import java.util.*;

@Getter
public class Profile {

    private final UUID uuid;
    private Rank rank;

    private static final List<Profile> profiles = new ArrayList<>();
    private final List<Grant> grants = new ArrayList<>();

    /**
     * Constructor for Profile
     *
     * @param uuid the uuid
     */
    public Profile(UUID uuid) {
        this.uuid = uuid;

        profiles.add(this);
    }

    /**
     * Add a grant
     *
     * @param grant the grant
     */
    public void addGrant(Grant grant) {
        this.grants.add(grant);
        this.grants.sort(Comparator.comparing(grant1 -> grant1.getRank().getWeight()));
        Collections.reverse(this.grants);
    }

    /**
     * Get a display name
     *
     * @return the display name
     */
    public String getDisplayName() {
        return this.grants.get(0).getRank().getColor();
    }

    /**
     * Find a profile
     *
     * @param uuid the uuid
     * @return the profile uuid
     */
    public static Profile findProfile(UUID uuid) {
        return profiles.stream()
                .filter(profile -> profile.getUuid().equals(uuid))
                .findFirst().orElse(null);
    }
}
