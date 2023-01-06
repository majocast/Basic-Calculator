/*
 * Author: Marc Castro
 * SFSU 2023
 * Major: Computer Science
 * Started Date: 1/4/2023
 * Completion Date: 1/6/2023
 * 
 * Project Description: Simple calculator that hands addition, subtraction, multiplication, and division
 * 
 * File Description: Main Calculator file that handles GUI Display code and intaking/storing user inputs via button commands.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Calculator extends JFrame implements ActionListener {

    private JPanel textPanel;
    private JPanel numberPanel;
    private TextField field;


    private int frameWidth = 300;
    private int frameHeight = 400;

    JButton[] numbers = new JButton[10];
    ArrayList<String> currTextArray = new ArrayList<String>();

    public JPanel createContentPane() {
        // We create a bottom JPanel to place everything on.
        JPanel totalGUI = new JPanel();
        
        // We set the Layout Manager to null so we can manually place
        // the Panels.
        totalGUI.setLayout(null);
        
        //creating textfield where numbers will be placed
        textPanel = new JPanel();
        textPanel.setBackground(Color.red);
        textPanel.setLocation(10, 10);
        textPanel.setSize(frameWidth - 30,40);
        textPanel.setLayout(null);
        
        field = new TextField();
        field.setLocation(0, 0);
        field.setSize(frameWidth - 30, 40);
        textPanel.add(field);

        totalGUI.add(textPanel);

        //creating button panel
        numberPanel = new JPanel();
        numberPanel.setBackground(Color.gray);
        numberPanel.setLocation(10, 60);
        numberPanel.setSize(frameWidth - 30, frameHeight - 100);


        //creates all number buttons
        for(int i = 9; i >= 0; i--) {
            JButton button = new JButton(Integer.toString(i));
            button.addActionListener(this);
            button.setActionCommand("num");
            numbers[i] = button;
            numberPanel.add(numbers[i]);
        }

        //individually creating buttons for arithmetic
        JButton mult = new JButton("*");
        mult.addActionListener(this);
        mult.setActionCommand("func");
        
        JButton add = new JButton("+");
        add.addActionListener(this);
        add.setActionCommand("func");

        JButton sub = new JButton("-");
        sub.addActionListener(this);
        sub.setActionCommand("func");

        JButton divi = new JButton("/");
        divi.addActionListener(this);
        divi.setActionCommand("func");

        JButton clear = new JButton("C");
        clear.addActionListener(this);
        clear.setActionCommand("clear");

        JButton clearAll = new JButton("CE");
        clearAll.addActionListener(this);
        clearAll.setActionCommand("clear all");

        JButton equals = new JButton("=");
        equals.addActionListener(this);
        equals.setActionCommand("equals");

        numberPanel.add(add);
        numberPanel.add(sub);
        numberPanel.add(mult);
        numberPanel.add(divi);
        numberPanel.add(clear);
        numberPanel.add(clearAll);
        numberPanel.add(equals);

        totalGUI.add(numberPanel);
        
        // Finally we return the JPanel.
        totalGUI.setOpaque(true);
        return totalGUI;
    }


    public static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Calculator");
        
        //create and set up the content pane
        Calculator calc = new Calculator();
        frame.setContentPane(calc.createContentPane());
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(calc.frameWidth, calc.frameHeight);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    //override function to handle button presses by user.
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        Object source = e.getSource();
        Functions funcCall = new Functions();
        JButton btn = (JButton) source;
        String srcText = btn.getText();
        switch(action) {
            case "num":
                currTextArray.add(srcText);
                updateText();
                break;
            case "func":
                currTextArray.add(srcText);
                updateText();
                break;
            case "equals":
                double result = funcCall.functionRunner(currTextArray);
                System.out.println(currTextArray);
                if(result == -1) {
                    field.setText(" ");
                    System.out.println("invalid input");
                }
                updateText();
                break;
            case "clear":
                try {
                    currTextArray.remove(currTextArray.size()-1);
                } catch (Exception IndexOutOfBoundsException) {
                    ;
                }
                updateText();
                break;
            case "clear all":
                currTextArray = new ArrayList<String>();
                updateText();
                break;
            default:
                break;
        }
        
    }

    public void updateText() {
        field.setText(" ");
        for(String str : currTextArray) {
            String currText = field.getText() + str;
            field.setText(currText);
        }
        System.out.println(currTextArray);
    }
}