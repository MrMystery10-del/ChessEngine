package core.pojo;

import core.constants.Difficulty;

import java.awt.image.BufferedImage;

/**
 * This class represents a player/bot
 */
public final class Player {

    // Name of the player
    private final String name;
    // Image of the player/bot profile
    private final BufferedImage image;
    // Difficulty of the bot if player is bot
    private final Difficulty difficulty;
    private final String description;
    private final int eloScore;
    private final boolean bot;

    /**
     * Constructs a new Player (human or bot)
     *
     * @param name name of the player
     * @param image profile image of the player
     */
    public Player(String name, BufferedImage image) {
        this(name, image, false, null, 0, null);
    }

    private Player(String name, BufferedImage image, boolean bot, Difficulty difficulty, int eloScore, String description) {
        this.name = name;
        this.image = image;
        this.bot = bot;
        this.difficulty = difficulty;
        this.eloScore = eloScore;
        this.description = description;
    }

    /**
     * Returns a new instance of Player representing a bot
     *
     * @param difficulty  difficulty/strength of the bot
     * @param eloScore    elo score of the bot
     * @param description description of the bot
     */
    public Player asBot(Difficulty difficulty, int eloScore, String description) {
        return new Player(name, image, true, difficulty, eloScore, description);
    }

    public String getName() {
        return name;
    }

    public int getEloScore() {
        return eloScore;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getDescription() {
        if (description != null) {
            return description;
        }
        return "ipsum lorem";
    }

    public boolean isBot() {
        return bot;
    }
}
