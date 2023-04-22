package gui.profileScreen;

import content.Screen;

/**
 * This class represent the profile menu when clicking on profile when in main menu
 */
public class ProfileMenu extends Screen {


    private final ProfileGui gui;

    public ProfileMenu() {
        gui = new ProfileGui();
        gui.setVisible(true);
    }

    public ProfileGui getGui() {
        return gui;
    }
}