package core.pojo.Achievements;

import java.awt.image.BufferedImage;

public record Achievement (int id,String title,String description,BufferedImage icon,boolean hasMetCondition) {}