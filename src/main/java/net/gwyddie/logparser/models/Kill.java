package net.gwyddie.logparser.models;

/**
 * A representation of a Kill.
 */
@SuppressWarnings("WeakerAccess")
public class Kill {
    /**
     * Who made the killing.
     */
    private final String killer;

    /**
     * Who was murdered
     */
    private final String victim;

    /**
     * How the killing was
     */
    private final MeansOfDeath meanOfDeath;

    /**
     * Constructor
     *
     * @param killer      who killed
     * @param victim      who died
     * @param meanOfDeath how they did so
     */
    public Kill(String killer, String victim, String meanOfDeath) {

        this.killer = killer;
        this.victim = victim;
        this.meanOfDeath = MeansOfDeath.valueOf(meanOfDeath);
    }

    /**
     * Who killed.
     *
     * @return killer
     */
    public String getKiller() {
        return killer;
    }

    /**
     * Who died.
     *
     * @return victim
     */
    public String getVictim() {
        return victim;
    }

    /**
     * How the Kill happened.
     *
     * @return mean of death
     */
    public MeansOfDeath getMeanOfDeath() {
        return meanOfDeath;
    }

    /**
     * A stringified representation of the instance.
     *
     * @return a String
     */
    @Override
    public String toString() {
        return "Kill{" +
                "killer='" + killer + '\'' +
                ", victim='" + victim + '\'' +
                ", meanOfDeath=" + meanOfDeath +
                '}';
    }
}

