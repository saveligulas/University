package org.example;

import org.example.stack.IllegalPostfixExpressionException;
import org.example.stack.StackCalculator;
import org.example.stack.StackIsEmptyException;
import org.example.stack.StackIsFullException;
import org.example.temp.Gateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean run = true;
        StackCalculator stackCalculator = new StackCalculator(15);
        try {
            while (run) {
                System.out.println("Enter a postfix expression or exit the program(e):");
                String input = reader.readLine();
                if (input.equals("e")) {
                    run = false;
                }
                try {
                    System.out.println(stackCalculator.calculate(input));
                } catch (IllegalPostfixExpressionException e) {
                    System.out.println("Your postfix expression was invalid: " + e.getMessage());
                } catch (StackIsFullException e) {
                    System.out.println("Your postfix expression does not fit in the stack, whose size is: " + stackCalculator.getSize());
                }
            }
        } catch (IOException e) {
            System.out.println("Wow");
        }*/

        Gateway gateway = new Gateway(5);
        gateway.run(10);
        System.out.println();
    }
}