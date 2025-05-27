import java.util.Stack;

public class EvaluatePostfix {
    public static int evaluatePostfix(String expression) {
        Stack<Integer> stack = new Stack<>();

        for (String token : expression.split("\\s+")) {
            if (token.matches("-?\\d+")) stack.push(Integer.parseInt(token));
            else if (token.matches("[+\\-*/]")) stack.push(apply(stack.pop(), stack.pop(), token.charAt(0)));
            else throw new IllegalArgumentException("Invalid token: " + token);
        }

        if (stack.size() == 1) return stack.pop();
        else throw new IllegalArgumentException("Invalid postfix expression");
    }

    private static int apply(int a, int b, char operator) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': if (b == 0) throw new ArithmeticException("Division by zero"); return a / b;
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.exit(1);
        }

        try {
            System.out.println("Result: " + evaluatePostfix(args[0]));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
