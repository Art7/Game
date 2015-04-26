package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Artur on 25.04.2015.
 */
public class Model {

    private String flag;
    private String win;
    private int crosses;
    private int zeros;
    private boolean gameType;
    ArrayList <Integer> emptyCells;

    public Model(){
        newGame();
    }

    public String getWin() {
        return win;
    }

    public void setGameType(boolean gameType) {
        this.gameType = gameType;
    }

    public void newGame(){
        win = "";
        flag = "X";
        crosses = 0;
        zeros = 0;
        emptyCells = new ArrayList();
        emptyCells.clear();
        for(int i=0;i<9;i++) emptyCells.add(i, (Integer)i);
    }

    private String checkWinner(JButton[] buttons){
        if(!buttons[4].getText().equals(flag) && !buttons[4].getText().equals("")){
            if (buttons[0].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[8].getText())) {
                setColors(buttons, 0, 4, 8);
            }
            else if (buttons[4].getText().equals(buttons[2].getText()) && buttons[4].getText().equals(buttons[6].getText())) {
                setColors(buttons, 2, 4, 6);
            }
            else if (buttons[4].getText().equals(buttons[3].getText()) && buttons[3].getText().equals(buttons[5].getText())) {
                setColors(buttons, 3, 4, 5);
            }
            else if (buttons[4].getText().equals(buttons[1].getText()) && buttons[1].getText().equals(buttons[7].getText())) {
                setColors(buttons, 1, 4, 7);
            }
        }
        if(!buttons[0].getText().equals(flag) && !buttons[0].getText().equals("")){
            if (buttons[0].getText().equals(buttons[1].getText()) && buttons[1].getText().equals(buttons[2].getText())) {
                setColors(buttons,0,1,2);
            }
            else if (buttons[0].getText().equals(buttons[3].getText()) && buttons[3].getText().equals(buttons[6].getText())) {
                setColors(buttons,0,3,6);
            }
        }
        if(!buttons[8].getText().equals(flag) && !buttons[8].getText().equals("")) {
            if (buttons[8].getText().equals(buttons[7].getText()) && buttons[7].getText().equals(buttons[6].getText())) {
                setColors(buttons,6,7,8);
            } else if (buttons[8].getText().equals(buttons[5].getText()) && buttons[5].getText().equals(buttons[2].getText())) {
                setColors(buttons,2,5,8);
            }
        }
        if ((crosses + zeros) == 9 && win.equals("")) {
            win = "Nobody";
            return "Ничья";
        }
        else  if (!win.equals("")) {
                if (win.equals("X")) return "Выиграли крестики";
                else return "Выиграли нолики";
            }
        else return "";
    }

    public void buttonPressed(JButton[] buttons, JButton button, JLabel label){
            if (flag.equals("X")) {
                button.setText("X");
                button.setForeground(Color.green);
                label.setText("Ход ноликов");
                crosses++;
                flag = "0";
                emptyCells.remove((Integer) getIndex(buttons, button));
                if (crosses >= 3) {
                    String temp = checkWinner(buttons);
                    if (!temp.equals("")) label.setText(temp);
                }
                if (gameType && (crosses + zeros != 9) && win.equals("")) {
                    int index = (int) (Math.random() * (emptyCells.size() - 1));
                    buttons[emptyCells.get(index)].setText("O");
                    buttons[emptyCells.get(index)].setForeground(Color.red);
                    label.setText(String.valueOf(emptyCells.size()));
                    emptyCells.remove(index);
                    setZeros(buttons, label);
                }
            }
            else {
                    button.setText("O");
                    button.setForeground(Color.red);
                    setZeros(buttons, label);
            }
    }

    private void setColors(JButton[] buttons, int first, int second, int third){
        buttons[first].setBackground(Color.YELLOW);
        buttons[second].setBackground(Color.YELLOW);
        buttons[third].setBackground(Color.YELLOW);
        win = buttons[first].getText();
    }

    private int getIndex(JButton[] buttons, JButton button){
        int i = 0;
        while (!(buttons[i]==button)) i++;
        return i;
    }
    private void setZeros(JButton[] buttons, JLabel label){
        label.setText("Ход крестиков");
        zeros++;
        flag = "X";
        if (zeros >= 3) {
            String temp = checkWinner(buttons);
            if (!temp.equals("")) label.setText(temp);
        }
    }
}
