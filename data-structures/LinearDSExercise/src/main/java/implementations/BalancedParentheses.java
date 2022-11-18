package implementations;

import interfaces.Solvable;

import java.util.Stack;

public class BalancedParentheses implements Solvable {
    private String parentheses;
    private String openingBrackets = "[{(";
    private String closingBrackets = "]})";

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    @Override
    public Boolean solve() {
        Stack<Character> bracketsStack = new Stack<>();
        for (char c : parentheses.toCharArray()) {
            char last = bracketsStack.isEmpty() ? '-' : bracketsStack.peek();
            if (openingBrackets.contains(String.valueOf(c))) {
                bracketsStack.push(c);
            } else if (last == '(' && c == ')') {
                bracketsStack.pop();
            } else if (last == '[' && c == ']') {
                bracketsStack.pop();
            } else if (last == '{' && c == '}') {
                bracketsStack.pop();
            } else {
                return false;
            }
        }
        return bracketsStack.isEmpty();
    }

}
