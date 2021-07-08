package com.eblaszczyk;

import java.util.Stack;

public class SimpleCalculator {

    private String[] ops;
    private String[] nums;

    public int calculate(String input) {

        input = input.trim();

        if (!checkIfInputIsCorrect(input)) {
            throw new RuntimeException("Incorrect input");
        }

        String[] parts = input.split(" ");

        if (parts.length == 1) {
            return Integer.parseInt(parts[0]);
        }

        splitForNumsAndOps(parts);

        return calculateExpression();
    }

    private int calculateExpression() {
        Stack<Integer> numsStack = new Stack<>();
        numsStack.push(Integer.parseInt(nums[0]));

        for (int i = 0; i < ops.length; i++) {
            int curr = Integer.parseInt(nums[i + 1]);

            if (ops[i].equals("*")) {
                curr = numsStack.pop() * curr;
            }

            if (ops[i].equals("/")) {
                curr = numsStack.pop() / curr;
            }

            if (ops[i].equals("-")) {
                curr = -curr;
            }

            numsStack.push(curr);
        }

        int result = 0;

        while (numsStack.size() > 0) {
            result += numsStack.pop();
        }

        return result;
    }

    private void splitForNumsAndOps(String[] parts) {
        ops = new String[parts.length / 2];
        nums = new String[parts.length / 2 + 1];

        for (int i = 0; i < parts.length; i++) {
            if (i % 2 == 1) {
                ops[i / 2] = parts[i];
            } else {
                nums[i / 2] = parts[i];
            }
        }
    }

    private boolean checkIfInputIsCorrect(String input) {
        return input != null && !input.isEmpty()
                && input.matches("^-?\\d+( (\\+|-|\\*|\\/) -?\\d+)*");
    }

    public static void main(String[] args) {
        SimpleCalculator calculator = new SimpleCalculator();

        System.out.println("Ex1 = " + calculator.calculate("2 + 3"));
        System.out.println("Ex1 = " + calculator.calculate("3 * 2 + 1"));
        System.out.println("Ex1 = " + calculator.calculate("3 * -2 + 6"));
    }
}
