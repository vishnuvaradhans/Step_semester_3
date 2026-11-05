import java.util.*;

public class F3
{
    static int precedence(char op)
    {
        if (op == '+' || op == '-')
            return 1;
        if (op == '*' || op == '/')
            return 2;
        return 0;
    }

    static void apply(Stack<Integer> values, Stack<Character> ops)
    {
        int b = values.pop();
        int a = values.pop();
        char op = ops.pop();

        switch (op)
        {
            case '+': values.push(a + b); break;
            case '-': values.push(a - b); break;
            case '*': values.push(a * b); break;
            case '/': values.push(a / b); break;
        }
    }

    static int evaluate(String expr)
    {
        Stack<Integer> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < expr.length(); i++)
        {
            char ch = expr.charAt(i);

            if (ch == ' ')
                continue;

            if (Character.isDigit(ch))
            {
                int num = 0;

                while (i < expr.length() &&
                       Character.isDigit(expr.charAt(i)))
                {
                    num = num * 10 + (expr.charAt(i) - '0');
                    i++;
                }

                i--;
                values.push(num);
            }
            else if (ch == '(')
            {
                ops.push(ch);
            }
            else if (ch == ')')
            {
                while (ops.peek() != '(')
                    apply(values, ops);

                ops.pop();
            }
            else
            {
                while (!ops.isEmpty() &&
                       ops.peek() != '(' &&
                       precedence(ops.peek()) >= precedence(ch))
                {
                    apply(values, ops);
                }

                ops.push(ch);
            }
        }

        while (!ops.isEmpty())
            apply(values, ops);

        return values.pop();
    }

    public static void main(String[] args)
    {
        String expr = "2 * (3 + 4) - 10 / 5";

        System.out.println(evaluate(expr));
    }
}