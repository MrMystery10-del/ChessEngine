package persistence.DTO;

import java.util.HashSet;
import java.util.Set;

//todo implement
public class ProfileDto {
    private String userName;
    private int userId;
    private String emailAddress;
    private int elo;
    private int wins;
    private int losses;
    private int draw;
    private String image;
    private final Set<AchievementDto> achievements;



    //boilerplate


    public ProfileDto() {
        achievements=new HashSet<>();
    }

    public String getUserName() {
        return userName;
    }

    public ProfileDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public ProfileDto setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public ProfileDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public int getElo() {
        return elo;
    }

    public ProfileDto setElo(int elo) {
        this.elo = elo;
        return this;
    }

    public int getWins() {
        return wins;
    }

    public ProfileDto setWins(int wins) {
        this.wins = wins;
        return this;
    }

    public int getLosses() {
        return losses;
    }

    public ProfileDto setLosses(int losses) {
        this.losses = losses;
        return this;
    }

    public int getDraw() {
        return draw;
    }

    public ProfileDto setDraw(int draw) {
        this.draw = draw;
        return this;
    }

    public Set<AchievementDto> getAchievements() {
        return achievements;
    }

    public ProfileDto addAchievement(AchievementDto achievement) {
        achievements.add(achievement);
        return this;
    }

    public ProfileDto setImage(String image) {
        this.image = image;
        return this;
    }

    public String getImage() {
        return image;
    }
}


