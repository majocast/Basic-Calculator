/*
 * Author: Marc Castro
 * SFSU 2023
 * Major: Computer Science
 * Started Date: 1/4/2023
 * Completion Date: 1/6/2023
 * 
 * Project Description: Simple calculator that hands addition, subtraction, multiplication, and division
 * 
 * File Description: Functions file that handles operational computation of inputted number values and parsing 
 *                   ArrayList to clean up inputs from individual numbers to intended compound values.
 */

import java.util.*;

public class Functions extends Calculator {

    private String[] operations = new String[] {"+", "-", "/", "*"}; 

    Functions() {}


    //function that checks for strings that are both numbers and combines them into one string, essentially cleans up the ArrayList.
    private ArrayList<String> comboNums(ArrayList<String> passedArray, boolean check) {
        try {
            for(int i = 0; i < passedArray.size()-1; i++) {
                while(passedArray.get(i).matches("[0-9]+") && passedArray.get(i + 1).matches("[0-9]+")) {
                    String newNum = passedArray.get(i) + passedArray.get(i + 1);
                    passedArray.remove(i + 1);
                    passedArray.set(i, newNum);
                    System.out.println(passedArray);
                }
            }
        } catch (Exception IndexOutOfBoundsException) {}
        
        return passedArray;
    }


    //function that is called after hitting the equals button to perform computation
    public double functionRunner(ArrayList<String> passedArray) {
        ArrayList<String> finalArray = passedArray;
        //implement for loop to combine all possible numbers
        boolean check = false;
        finalArray = comboNums(finalArray, check);

        //checker if-else to check for one number/invalid input
        if(finalArray.size() == 1) {
            for(String str : operations) {
                if(str == finalArray.get(0)) {   
                    return -1;
                } 
            }
            return Double.parseDouble(finalArray.get(0));
        //performing arithmetic
        } else {
            System.out.println("in else statement");
            while(finalArray.size() > 1) {
                System.out.println("in while loop");
                System.out.println(finalArray.size());

                //creating code to follow PEMDAS (mult/divide first, then loop again and check for add/sub)
                for(int i = 0; i < finalArray.size(); i++) {
                    System.out.println(finalArray);
                    if(finalArray.get(i).equals(operations[3])) {
                        if(i != 0 && i != finalArray.size()-1) {
                            double result = multi(Double.parseDouble(finalArray.get(i-1)), Double.parseDouble(finalArray.get(i+1)));
                            finalArray = remSet(finalArray, result, i);
                        }
                    } else if(finalArray.get(i).equals(operations[2])) {
                        if(i != 0 && i != finalArray.size()-1) {
                            double result = divide(Double.parseDouble(finalArray.get(i-1)), Double.parseDouble(finalArray.get(i+1)));
                            finalArray = remSet(finalArray, result, i);
                        }
                    }
                }

                //for-loop for running add/subtract after mult/divide
                for(int i = 0; i < finalArray.size(); i++) {
                    System.out.println(finalArray);
                    if(finalArray.get(i).equals(operations[0])) {
                        if(i != 0 && i != finalArray.size()-1) {
                            double result = add(Double.parseDouble(finalArray.get(i-1)), Double.parseDouble(finalArray.get(i+1)));
                            finalArray = remSet(finalArray, result, i);
                        }
                    } else if(finalArray.get(i).equals(operations[1])) {
                        if(i != 0 && i != finalArray.size()-1) {
                            double result = sub(Double.parseDouble(finalArray.get(i-1)), Double.parseDouble(finalArray.get(i+1)));
                            finalArray = remSet(finalArray, result, i);
                        }
                    }
                }
            }
            return 0;
        }
    }

    private double multi(double i, double j) {
        return i * j;
    }

    private double divide(double i, double j) {
        return i / j;
    }

    private double add(double i, double j) {
        return i + j;
    }

    private double sub(double i, double j) {
        return i - j;
    }

    //function to remove objects in position after performing function
    private ArrayList<String> remSet(ArrayList<String> passedArray, double num, int pos) {
        passedArray.remove(pos + 1);
        passedArray.remove(pos);
        passedArray.set(pos-1, Double.toString(num));
        return passedArray;
    }
}
