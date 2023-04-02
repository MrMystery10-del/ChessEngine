package gui.constants;

import gui.components.Block_Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Piece_info {
    private Block_Button selected_button;

    private List<Block_Button> highlighted_button = new ArrayList<>();

    private List<Color> button_colors = new ArrayList<>();

    public List<Block_Button> getHighlighted_button()
    {
        return highlighted_button;
    }

    public List<Color> getButton_colors()
    {
        return button_colors;
    }

    public Block_Button getSelected_button()
    {
        return  selected_button;
    }

    public void setSelected_button(Block_Button button)
    {
        this.selected_button=button;
    }

    public void setHighlighted_button(List<Block_Button> button)
    {
        this.highlighted_button=button;
    }

    public void setButton_colors(List<Color> button)
    {
        this.button_colors=button;
    }
}
