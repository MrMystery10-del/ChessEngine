package gui;

/**
 * Small screen to represent a bot to choose on the ChooseAIMenu
 */

public class AIProfile {}
/*
public abstract class AIProfile extends Screen {

    public AIProfile(Bot bot) {
        setUpProfile(bot);
    }

    // Sets the picture and name of teh bot on screen
    private void setUpProfile(Bot bot) {
        Label picture = new Label();
        picture.setBounds(0, 0, 1920, 700);
        picture.setIcon(new ImageIcon(bot.getPicture()));
        add(picture);

        Button button = new Button(bot.getName());
        button.setBounds(0, 700, 1920, 380);
        button.addActionListener(event -> viewAiProfile(bot));
        add(button, 1);
    }

    /**
     * View the full AI profile via AIProfilePopup
     */
    /*
    public abstract void viewAiProfile(Bot bot);
}*/
