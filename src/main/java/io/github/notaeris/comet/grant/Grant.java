package io.github.notaeris.comet.grant;

import io.github.notaeris.comet.profile.Profile;
import io.github.notaeris.comet.rank.Rank;
import lombok.Getter;

@Getter
public class Grant {

    private final Rank rank;
    private final Profile profile;

    public Grant(Rank rank, Profile profile) {
        this.rank = rank;
        this.profile = profile;
    }
}
