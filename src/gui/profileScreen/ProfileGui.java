package gui.profileScreen;

import gui.arrangement.GuiConfiguration;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProfileGui extends JFrame {
    private JLabel profileLabel;
    private JPanel imagePanel;
    private JPanel detailPanel;
    private JPanel statPanel;
    private JLabel details;
    private JTextPane detailTextPane;
    private JLabel userStats;
    private JTextPane overviewTextPane;
    private JLabel userImage;
    private JPanel profilePane;

    private List<JLabel>labels = List.of(profileLabel,userStats,details);


    public ProfileGui(){
        add(profilePane);
        setMinimumSize(new Dimension(800,800));
        labels.forEach(label->label.setFont(GuiConfiguration.regularFont));
        profileLabel.setFont(GuiConfiguration.regularTitleFont);



    }
}
