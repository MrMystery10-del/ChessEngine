package gui.controllers;

import core.Configuration;
import gui.profileScreen.ProfileGui;
import gui.profileScreen.ProfileMenu;
import persistence.DTO.ProfileDto;
import persistence.Persistence;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ProfileController {
    private static final Logger logger = Logger.getLogger(ProfileController.class.getName());
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.translations.bundle", Locale.getDefault());
    private final Persistence persistence;
    private final ProfileGui gui;
    private ProfileDto dto;

    public ProfileController(Persistence persistence, ProfileMenu menu) {
        this.persistence = persistence;
        this.gui = menu.getGui();
        readInitialData();
        updateGui();
    }

    /**
     * Read data from the profile source
     */
    private void readInitialData() {
        logger.finest("reading initial dataset from profile source");
        dto = persistence.readFromProfile();
        checkForProfile();

    }

    /**
     * checks that a profile actually exists in the specified folder, if not create one
     */
    private void checkForProfile() {
        if (dto.getUserName() == null && dto.getImage() == null && dto.getUserId() == 0) {
            logger.info("New user detected, creating profile");
            Configuration.startWithNewProfile = true;
            dto = persistence.readFromProfile();

        }

    }


    private void updateGui() {
        if (!dto.getImage().equals("not set yet")) {
            logger.finest("no image set yet - skipping update on GUI");
            ImageIcon image = new ImageIcon(dto.getImage());
            gui.getUserImage().setIcon(image);
            gui.getUserImage().updateUI();
        }

        updateUserDetails();
        updateUserStats();


    }

    //@formatter:off
    private void updateUserDetails() {
        String details =
                 resourceBundle.getString("profileUserName") + reformat(dto.getUserName())
                +resourceBundle.getString("emailAddress") + reformat(dto.getEmailAddress())
                +resourceBundle.getString("eloRating") + reformat(dto.getElo())
                +"\n"
                +resourceBundle.getString("achievements") + reformat("")
                +
                "- To be designed -";

        gui.getDetailTextPane().setText(details);

    }

    private void updateUserStats() {
        double winLoss;
        try {
            winLoss = ((double)dto.getWins() / ((dto.getLosses() + (double)dto.getWins()) / 100));
        }
        catch (ArithmeticException e){
            //division by zero
            winLoss = 0D;
        }

        String details =
                 resourceBundle.getString("totalWins") + reformat(dto.getWins())
                +resourceBundle.getString("totalLosses") + reformat(dto.getLosses())
                +resourceBundle.getString("totalDraws") + reformat(dto.getDraw())
                +"\n"
                +resourceBundle.getString("winLossRatio") + " : "+  winLoss + "%"
                ;


        gui.getOverviewTextPane().setText(details);
    }


    //@formatter:on

    /**
     * prepends the String with " : " and adds a new line to end
     *
     * @param toAppend the string to append with a separator sequence
     * @return reformatted string
     */
    private String reformat(String toAppend) {
        return " : " + toAppend + "\n";
    }

    /**
     * prepends the String with " : " and adds a new line to end
     *
     * @param toAppend the string to append with a separator sequence
     * @return reformatted string
     */
    private String reformat(int toAppend) {
        return reformat(String.valueOf(toAppend));
    }
}
