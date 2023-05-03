package persistence.DTO;

public class AchievementDto {
    private int id;
    private String title;
    private String description;
    private String icon;
    private boolean hasMetCondition = false;

//boilerplate

    public int getId() {
        return id;
    }

    public AchievementDto setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AchievementDto setTitle(String title) {
        this.title = title;
        return this;
    }
}
