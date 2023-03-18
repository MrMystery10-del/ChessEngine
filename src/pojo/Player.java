package pojo;

import pojo.enums.Difficulty;

public class Player {

    //human
    String name;

    //non human
    boolean bot=false;
    Difficulty difficulty;
    String imageIdentifier;
    int eloScore;
    String description = "ipsum lorem";


    public Player(String name) {
        this.name = name;
    }


    /**
     *
     * @param difficulty difficulty rating
     * @param eloScore   elo rating
     * @param imageIdentifier image identifier
     * @param description
     *
     * Changes the player to ai state, setting diffuculty level
     */
    public void setBot(Difficulty difficulty,int eloScore, String imageIdentifier, String description) {
        this.bot=true;
        this.eloScore=eloScore;
        this.imageIdentifier=imageIdentifier;
        this.description=description;

    }

}
