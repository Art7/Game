package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Artur on 23.04.2015.
 */
public class GUI {

    // main Frame objects
    private JFrame mainFrame;
    private JButton closeButton;
    private JPanel mainPanel;
    private GridLayout mainGridLayout;
    private JButton[] mainButtons;

    // close Frame objects
    private JFrame closeFrame;
    private JPanel closeUpPanel;
    private JLabel closeLabel;
    private JPanel closeBottomPanel;
    private GridLayout closeBottomLayout;
    private JButton cancelButton;
    private JButton confirmButton;

    public void buildGUI(){

        // create main Frame objects
        mainFrame = new JFrame("Крестики нолики");
        mainPanel = new JPanel();
        mainGridLayout = new GridLayout(3,3,5,5);
        mainButtons = new JButton[9];
        closeButton = new JButton("Закрыть");

        // create close Frame odjects
        closeFrame = new JFrame("Закрыть?");
        cancelButton = new JButton("Отмена");
        confirmButton = new JButton("Закрыть");
        closeBottomPanel = new JPanel();
        closeBottomLayout = new GridLayout(1,2);
        closeUpPanel = new JPanel();
        closeLabel = new JLabel("Вы уверены?");

        // main Frame Button activities
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFrame.setVisible(true);
            }
        });

        // close Fraim Button activities
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFrame.setVisible(false);
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        // main Frame construction
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setBounds(100, 100, 300, 400);
        mainFrame.add(BorderLayout.CENTER, mainPanel);
        mainFrame.add(BorderLayout.SOUTH, closeButton);
        mainPanel.setLayout(mainGridLayout);
        mainPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        for(int i = 0; i<9; i++) {
            mainButtons[i] = new JButton();
            mainPanel.add(mainButtons[i]);
            mainButtons[i].addActionListener(new mainButtonsActionListener());
        }
        mainFrame.setVisible(true);

        // close Frame construction
        closeFrame.setBounds(150, 150, 220, 100);
        closeUpPanel.add(closeLabel);
        closeBottomPanel.setLayout(closeBottomLayout);
        closeFrame.add(BorderLayout.SOUTH, closeBottomPanel);
        closeFrame.add(BorderLayout.CENTER, closeUpPanel);
        closeBottomPanel.add(cancelButton);
        closeBottomPanel.add(confirmButton);
    }
    private class mainButtonsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           if(((JButton) e.getSource()).getText() == "") ((JButton) e.getSource()).setText("X");
           else if(((JButton) e.getSource()).getText() == "X") ((JButton) e.getSource()).setText("O");
            else ((JButton) e.getSource()).setText("X");
        }
    }
}
