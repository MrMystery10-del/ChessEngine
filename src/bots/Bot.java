package bots;

public interface Bot {

    /**
     * @return the elo of the bot
     */
    int getElo();

    /**
     * @return name of the bot
     */
    String getName();

    /**
     * @return imagine description/bio of the bot as a small storyline
     */
    String getDescription();

    /**
     * @return name of the used algorithm for the bot
     */
    String getUsedAlgorithm();
}
