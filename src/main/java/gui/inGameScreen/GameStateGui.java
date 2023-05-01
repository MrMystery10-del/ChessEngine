package gui.inGameScreen;

public class GameStateGui{}
/*
public class GameStateGui extends Screen {

    private int BUTTONMARGINHOR = 50;

    private Button playButton;
    private Button loadButton;
    private Button saveButton;

    public GameStateGui(ResourceBundle resourceBundle) {
        playButton = new Button(resourceBundle.getString("start"));
        loadButton = new Button(resourceBundle.getString("load"));
        saveButton = new Button(resourceBundle.getString("save"));

        playButton.setFont(GuiConfiguration.regularTitleFont);
        playButton.setToolTipText(resourceBundle.getString("startToolTip"));
        loadButton.setFont(GuiConfiguration.regularTitleFont);
        loadButton.setToolTipText(resourceBundle.getString("loadToolTip"));
        saveButton.setFont(GuiConfiguration.regularTitleFont);
        saveButton.setToolTipText(resourceBundle.getString("saveToolTip"));

        int center = 300;
        int height = 350;
        int location = 260;

        playButton.setBounds(location, center, getButtonWidth(playButton), height);
        location += playButton.getWidth() + 25;
        loadButton.setBounds(location, center, getButtonWidth(loadButton), height);
        location += loadButton.getWidth() + 25;
        saveButton.setBounds(location, center, getButtonWidth(saveButton), height);
        // location += saveButton.getWidth()+25;

        add(playButton);
        add(loadButton);
        add(saveButton);
        repaint();
    }

    /**
     * @param button content.button
     * @return text width + 2x margin
     * the width the text contained in the button
     */
    /*
    private int getButtonWidth(Button button) {
        FontMetrics metrics = getFontMetrics(GuiConfiguration.regularTitleFont);
        return metrics.stringWidth(button.getText()) + (BUTTONMARGINHOR * 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.decode("#a1b0a0"));
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getLoadButton() {
        return loadButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }
}
*/