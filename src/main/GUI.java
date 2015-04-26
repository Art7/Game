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
    private JPanel mainUpPanel;
    private JLabel mainLabel;
    private JPanel mainMidPanel;
    private GridLayout mainMidGridLayout;
    private JButton[] mainButtons;
    private JPanel mainPanelDown;
    private GridLayout mainDownGridLayout;
    private JButton closeButton;

    // close Frame objects
    private JFrame closeFrame;
    private JPanel closeUpPanel;
    private JLabel closeLabel;
    private JPanel closeBottomPanel;
    private GridLayout closeBottomGridLayout;
    private JButton cancelButton;
    private JButton confirmButton;

    // about Frame objects
    private JFrame aboutFrame;
    private JPanel aboutUpPanel;
    private JLabel aboutLabel;
    private JPanel aboutDownPanel;
    private GridLayout aboutDownGridLayout;
    private JButton aboutButton;

    // selection Frame objects
    private JFrame selectFrame;
    private JPanel selectUpPanel;
    private JLabel selectLabel;
    private JPanel selectDownPanel;
    private GridLayout selectDownGridLayout;
    private JButton compButton;
    private JButton manButton;

    // Logic object
    private Model Logic;

    public void buildGUI() {

        // create Model object
        Logic = new Model();

        // create main Frame objects
        mainFrame = new JFrame("Крестики нолики");
        mainUpPanel = new JPanel();
        mainLabel = new JLabel("Ход крестиков");
        mainLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainMidPanel = new JPanel();
        mainMidGridLayout = new GridLayout(3, 3, 5, 5);
        mainButtons = new JButton[9];
        mainPanelDown = new JPanel();
        mainDownGridLayout = new GridLayout(1, 1, 5, 5);
        closeButton = new JButton("Закрыть");

        // create close Frame objects
        closeFrame = new JFrame("Закрыть?");
        closeUpPanel = new JPanel();
        closeLabel = new JLabel("Вы уверены?");
        closeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        closeBottomPanel = new JPanel();
        closeBottomPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        closeBottomGridLayout = new GridLayout(1, 2, 5, 5);
        cancelButton = new JButton("Отмена");
        confirmButton = new JButton("Закрыть");

        // create about Frame objects
        aboutFrame = new JFrame("О программе");
        aboutUpPanel = new JPanel();
        aboutLabel = new JLabel("Создано Art");
        aboutLabel.setFont(new Font("Arial", Font.BOLD, 16));
        aboutDownPanel = new JPanel();
        aboutDownGridLayout = new GridLayout(1, 1, 5, 5);
        aboutButton = new JButton("OK");

        // create selection Frame objects
        selectFrame = new JFrame("Тип игры");
        selectUpPanel = new JPanel();
        selectLabel = new JLabel("Выберите тип игры:");
        selectLabel.setFont(new Font("Arial", Font.BOLD, 14));
        selectDownPanel = new JPanel();
        selectDownGridLayout = new GridLayout(1, 2, 2, 2);
        compButton = new JButton("Компьютер");
        manButton = new JButton("Ручной");

        // create menu objects
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Игра");
        JMenu helpMenu = new JMenu("Помощь");
        JMenuItem newMenuItem = new JMenuItem("Новая игра");
        JMenuItem quitMenuItem = new JMenuItem("Выход");
        JMenuItem aboutMenuItem = new JMenuItem("О программе");

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

        // about Frame Button activities
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutFrame.setVisible(false);
            }
        });

        // selection Frame Button activities
        compButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.setGameType(true);
                selectFrame.setVisible(false);
                if (!mainFrame.isVisible()) {
                    mainFrame.setVisible(true);
                    selectFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                }
            }
        });
        manButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.setGameType(false);
                selectFrame.setVisible(false);
                if (!mainFrame.isVisible()) {
                    mainFrame.setVisible(true);
                    selectFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                }
            }
        });

        // main Frame construction
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setBounds(100, 100, 280, 360);
        mainFrame.setResizable(false);
        mainMidPanel.setLayout(mainMidGridLayout);
        mainMidPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        mainFrame.add(BorderLayout.NORTH, mainUpPanel);
        mainFrame.add(BorderLayout.CENTER, mainMidPanel);
        mainFrame.add(BorderLayout.SOUTH, mainPanelDown);
        mainPanelDown.setLayout(mainDownGridLayout);
        mainPanelDown.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        mainUpPanel.add(mainLabel);
        for (int i = 0; i < mainButtons.length; i++) {
            mainButtons[i] = new JButton();
            Font F = new Font("Arial", Font.BOLD, 28);
            mainButtons[i].setFont(F);
            mainButtons[i].setBackground(Color.darkGray);
            mainMidPanel.add(mainButtons[i]);
            mainButtons[i].addActionListener(new mainButtonsActionListener());
        }
        mainPanelDown.add(closeButton);

        // Menu construction
        gameMenu.add(newMenuItem);
        gameMenu.add(quitMenuItem);
        helpMenu.add(aboutMenuItem);
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);
        mainFrame.setJMenuBar(menuBar);

        // Menu activities
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutFrame.setVisible(true);
            }
        });
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFrame.setVisible(true);
                for (int i = 0; i < mainButtons.length; i++) {
                    mainButtons[i].setText("");
                    mainButtons[i].setBackground(Color.darkGray);
                }
                mainLabel.setText("Ход крестиков");
                Logic.newGame();
            }
        });
        quitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // close Frame construction
        closeFrame.setBounds(150, 150, 300, 110);
        closeFrame.setResizable(false);
        closeUpPanel.add(closeLabel);
        closeBottomPanel.setLayout(closeBottomGridLayout);
        closeFrame.add(BorderLayout.SOUTH, closeBottomPanel);
        closeFrame.add(BorderLayout.CENTER, closeUpPanel);
        closeBottomPanel.add(cancelButton);
        closeBottomPanel.add(confirmButton);

        // about Frame construction
        aboutFrame.setBounds(150, 150, 300, 100);
        aboutFrame.setResizable(false);
        aboutFrame.setUndecorated(true);
        aboutFrame.add(BorderLayout.CENTER, aboutUpPanel);
        aboutUpPanel.add(aboutLabel);
        aboutFrame.add(BorderLayout.SOUTH, aboutDownPanel);
        aboutDownPanel.setLayout(aboutDownGridLayout);
        aboutDownPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        aboutDownPanel.add(aboutButton);

        // select Frame construction
        selectFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        selectFrame.setBounds(150, 150, 250, 110);
        selectFrame.setResizable(false);
        selectFrame.add(BorderLayout.CENTER, selectUpPanel);
        selectFrame.add(BorderLayout.SOUTH, selectDownPanel);
        selectUpPanel.add(selectLabel);
        selectDownPanel.setLayout(selectDownGridLayout);
        selectDownPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        selectDownPanel.add(compButton);
        selectDownPanel.add(manButton);

        selectFrame.setVisible(true);
    }

    // mainButtons listener
    private class mainButtonsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (((JButton) e.getSource()).getText().equals("") && (Logic.getWin()).equals("")) {
                Logic.buttonPressed(mainButtons, (JButton) e.getSource(), mainLabel);
            }
        }
    }
}
//JMenuBar - панель меню
//JMenu - конкретное меню панели
//JMenuItem - пункт меню

//Tasks:
// 1) Вывести логику приложения в отдельный класс Model
// (обработчики по прежнему остаются в классе GUI, но выбор хода,
// проверка выигрыша и другие части, не относящиеся к графическому интерфейсу,
// должны быть реализованы в классе Model) ok
// 2) За 0 играет компьютер: ходит по случайной свободной клетке ok
// 3) !!! ПРОЧИТАТЬ И ПОПРОБОВАТЬ на вкус ИНТЕРФЕЙСЫ (не графические)
// 4) Добавить надпись, чей сейчас ход  ok

