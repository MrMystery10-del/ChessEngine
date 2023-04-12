package persistence.DTO;

public class AchievementDto {
    int id;
    String title;
    String description;
    String icon;
    boolean hasMetCondition=false;

//boilerplate

    public int getId() {
        return id;
    }

    public AchievementDto setId(int id) {
        this.id = id;
        return this;
    }
}
