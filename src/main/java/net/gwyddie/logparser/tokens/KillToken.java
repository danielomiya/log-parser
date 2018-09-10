package net.gwyddie.logparser.tokens;

/**
 * Token representing a killing.
 */
public class KillToken {
    /**
     * Victim
     */
    private String killer;

    /**
     * Killer
     */
    private String victim;

    /**
     * Mean of death
     */
    private String meanOfDeath;

    /**
     * Constructor
     * @param killer who killed
     * @param victim who died
     * @param meanOfDeath the way the killing occurred
     */
    public KillToken(String killer, String victim, String meanOfDeath) {
        this.killer = killer;
        this.victim = victim;
        this.meanOfDeath = meanOfDeath;
    }

    /**
     * Gets who made the killing.
     *
     * @return killer
     */
    public String getKiller() {
        return killer;
    }

    /**
     * Gets who got dead.
     *
     * @return victim
     */
    public String getVictim() {
        return victim;
    }

    /**
     * Gets the mean of the death.
     *
     * @return mean of the death
     */
    public String getMeanOfDeath() {
        return meanOfDeath;
    }

    /**
     * Turns the Object into a String.
     *
     * @return a stringified Object
     */
    @Override
    public String toString() {
        return "KillToken{" +
                "killer='" + killer + '\'' +
                ", victim='" + victim + '\'' +
                ", meanOfDeath='" + meanOfDeath + '\'' +
                '}';
    }
}
