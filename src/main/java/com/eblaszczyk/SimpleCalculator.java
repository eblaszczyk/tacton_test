package com.eblaszczyk;

import java.util.Stack;

public class SimpleCalculator {

  public static final String SUBTRACTION = "-";
  public static final String MULTIPLICATION = "*";
  public static final String DIVISION = "/";
  public static final String REGEX_TO_CHECK_INPUT = "^-?\\d+( \\+|-|\\*|/ -?\\d+)*";

  private String[] operations;
  private String[] numbers;

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
    numsStack.push(Integer.parseInt(numbers[0]));

    for (int i = 0; i < operations.length; i++) {
      int curr = Integer.parseInt(numbers[i + 1]);

      if (operations[i].equals(MULTIPLICATION)) {
        curr = numsStack.pop() * curr;
      }

      if (operations[i].equals(DIVISION)) {
        curr = numsStack.pop() / curr;
      }

      if (operations[i].equals(SUBTRACTION)) {
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
    operations = new String[parts.length / 2];
    numbers = new String[parts.length / 2 + 1];

    for (int i = 0; i < parts.length; i++) {
      if (i % 2 != 0) {
        operations[i / 2] = parts[i];
      } else {
        numbers[i / 2] = parts[i];
      }
    }
  }

  private boolean checkIfInputIsCorrect(String input) {
    return input != null && !input.isEmpty()
        && input.matches(REGEX_TO_CHECK_INPUT);
  }

  public static void main(String[] args) {
    SimpleCalculator calculator = new SimpleCalculator();

    System.out.println("Ex1 = " + calculator.calculate("2 + 3"));
    System.out.println("Ex1 = " + calculator.calculate("3 * 2 + 1"));
    System.out.println("Ex1 = " + calculator.calculate("3 * -2 + 6"));
  }
}
