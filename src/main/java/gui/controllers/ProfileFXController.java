package gui.controllers;

import core.pojo.ChessEngine;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import persistence.DTO.ProfileDto;
import persistence.Persistence;
import utils.Bots;
import utils.ImageManager;

import java.util.logging.Logger;

public class ProfileFXController {
    private static final Logger logger = Logger.getLogger(ProfileFXController.class.getName());
    @javafx.fxml.FXML
    private TextField profileElo;
    @javafx.fxml.FXML
    private TextField totalWins;
    @javafx.fxml.FXML
    private TextField totalLosses;
    @javafx.fxml.FXML
    private TextField totalDraws;
    @javafx.fxml.FXML
    private ProgressBar winLossRatio;
    @javafx.fxml.FXML
    private TextField profileUserName;
    @javafx.fxml.FXML
    private TextField profileEmail;
    @javafx.fxml.FXML
    private ImageView userImage;


    private final Persistence model = ChessEngine.getInstance().persistence;


    public void initialize() {
        ProfileDto dto = model.readFromProfile();
        totalWins.setText(String.valueOf(dto.getWins()));
        totalLosses.setText(String.valueOf(dto.getLosses()));
        totalDraws.setText(String.valueOf(dto.getDraw()));
        profileElo.setText(String.valueOf(dto.getElo()));
        profileEmail.setText(dto.getEmailAddress());
        profileUserName.setText(dto.getUserName());

        userImage.setImage(ImageManager.getBotPicture(Bots.BOB));
        userImage.setPreserveRatio(true);


        try {
            double rate = (double) dto.getWins() / ((double) dto.getWins() + (double) dto.getLosses());
            winLossRatio.setProgress(rate);
        } catch (ArithmeticException e) {
            logger.finest("new profile -> div by 0 ");
            winLossRatio.setProgress(0);
        }


    }


}
