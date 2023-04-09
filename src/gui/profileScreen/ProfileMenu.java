package gui.profileScreen;

import content.Screen;

/**
 * This class represent the profile menu when clicking on profile when in main menu
 */
public abstract class ProfileMenu extends Screen {

    public ProfileMenu() {
        ProfileGui gui = new ProfileGui();
        gui.setVisible(true);
        gui.pack();
    }
}