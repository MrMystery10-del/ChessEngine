package gui.constants;

import gui.components.Block_Button;

import java.awt.*;
import java.util.List;


public class PieceInfo {


    Block_Button selectedButton;

    List<Block_Button> highlightedButton;
    List<Color> colorOfButtons;

    public Block_Button getSelectedButton() {
        return selectedButton;
    }

    public List<Color> getColorOfButtons() {
        return colorOfButtons;
    }

    public List<Block_Button> getHighlightedButton() {
        return highlightedButton;
    }

    public void setColorOfButtons(List<Color> colorOfButtons) {
        this.colorOfButtons = colorOfButtons;
    }

    public void setHighlightedButton(List<Block_Button> highlightedButton) {
        this.highlightedButton = highlightedButton;
    }

    public void setSelectedButton(Block_Button selectedButton) {
        this.selectedButton = selectedButton;
    }

    public boolean containsObj(Block_Button check) {
        //CHECK if the list contains the button
        //this method is necessary because it runs even when the list is empty
        return this.highlightedButton != null && this.highlightedButton.contains(check);
    }
}
