package com.pluralsight;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double[] leftVars = {100.0d, 25.0d, 225.0d, 11.0d};
        double[] rightVars = {50.0d, 92.0d,17.0d, 3.0d};
        char[] opCodes = {'d','a','s','m'};
        double[] results = new double[opCodes.length];
        if (args.length == 0 && args[0].charAt(0) != 'w'){
            for (int i = 0; i < opCodes.length;i++){
                results[i] = execute(opCodes[i], leftVars[i], rightVars[i]);
            }
            for (double currentResult : results)
                System.out.println(currentResult);
        }
        else if (args.length == 1 && args[0].equals("Interactively"))
            executeInteractively();
        else if (args.length == 3)
            handleCommandLine(args);
        else
            System.out.println("Please provide an operation code and 2 numeric values");
    }
    private static void handleCommandLine(String[] args) {
        //To get the data from the command line
        //convert the string to the right thing
        char opCode = args[0].charAt(0);
        double leftVal = Double.parseDouble(args[1]);
        double rightVal = Double.parseDouble(args[2]);
        double result = execute(opCode,leftVal,rightVal);
        displayResult(opCode,leftVal,rightVal, result);
    }
    private static void displayResult(char opCode, double leftVal, double rightVal, double result) {
        char symbol = symbolFromOpcode(opCode);
        String output = String.format("%.3f %c %.3f = %.3f", leftVal,symbol,rightVal,result);
        System.out.println(output);
    }
    private static char symbolFromOpcode(char opCode){
        char[] opCodes = {'a','s','m','d'};
        char[] symbols = {'+','-','*','/'};
        char symbol = ' ';
        for (int index = 0;index<opCodes.length; index++){
            if(opCode == opCodes[index]){
                symbol = symbols[index];
                break;
            }
        }
        return symbol;
    }

    //Interact with the user
    static void executeInteractively(){
        System.out.println("Enter an operation and two numbers: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String[] parts = userInput.split(" ");
        performOperation(parts);

    }

    private static void performOperation(String[] parts) {
        char opCode = opCodeFromString(parts[0]);
        if(opCode == 'w')
            handleWhen(parts);
        double leftVal = valueFromWord(parts[1]);
        double rightVal = valueFromWord(parts[2]);
        double result = execute(opCode, leftVal, rightVal);
        displayResult(opCode,leftVal,rightVal, result);
    }

    private static void handleWhen(String[] parts) {
        LocalDate startDate = LocalDate.parse(parts[1]);
        long daysToAdd = (long) valueFromWord(parts[2]);
        LocalDate newDate = startDate.plusDays(daysToAdd);
        String output = String.format("%s plus %d days is %s", startDate, daysToAdd, newDate);
        System.out.println(output);
    }

    static double execute(char opCode, double leftVal, double rightVal){
        double result = 0.0d;
        switch (opCode){
            case 'a':
                result = leftVal + rightVal;
                break;
            case 's':
                result = leftVal - rightVal;
                break;
            case 'm':
                result = leftVal * rightVal;
                break;
            case 'd':
                if(rightVal != 0 ){
                    result = leftVal / rightVal;
                }
                break;
            default:
                System.out.println("invalid Opcode entered");
        }
        return result;
    }
    static char opCodeFromString(String operationName){
        char opCode = operationName.charAt(0);
        return opCode;
    }
    static double valueFromWord(String word){
        String[] numberWords = {
                "zero","one","two","three","four","five","six",
                "seven","eight","nine"
        };
        double value = 0d;
        for(int index = 0;index<numberWords.length; index++){
            if (word.equals(numberWords[index])){
                value = index;
                break;
            }
        }
        return value;
    }
}
