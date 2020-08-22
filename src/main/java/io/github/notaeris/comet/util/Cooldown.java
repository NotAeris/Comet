package io.github.notaeris.comet.util;

import java.util.HashMap;
import java.util.UUID;

public class Cooldown {

    private final HashMap<String, Cooldown> cooldownHashMap = new HashMap<>();
    private long start;
    private int seconds;

    /**
     * Add a cooldown
     *
     * @param uuid the uuid
     * @param name the name
     * @param seconds the seconds
     */
    public void addCooldown(UUID uuid, String name, int seconds) {
        this.cooldownHashMap.put(uuid + name, this);
        this.start = System.currentTimeMillis();
        this.seconds = seconds;
    }

    /**
     * Check if a cooldown is active
     *
     * @param uuid the uuid
     * @param name the name
     * @return the result
     */
    public boolean hasCooldown(UUID uuid, String name) {
        if(this.getRemaining(uuid, name) >= 1) {
            return true;
        }

        this.cooldownHashMap.remove(uuid + name);
        return false;
    }

    /**
     * Get the remaining time
     *
     * @param uuid the uuid
     * @param name the name
     * @return the time remaining
     */
    public int getRemaining(UUID uuid, String name) {
        Cooldown cooldown = this.cooldownHashMap.get(uuid + name);
        int time = -1;

        if(cooldown != null) {
            time = (int) ((System.currentTimeMillis() - this.start) / 1000 - seconds) * -1;
        }

        return time;
    }
}
