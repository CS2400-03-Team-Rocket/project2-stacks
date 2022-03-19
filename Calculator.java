public class Calculator {

    public static String convertToPostfix(String input) throws Exception
    {
        //initialize operator stack
        LinkedStack<Character> operators = new LinkedStack<Character>();
        //initialize postfix return string 
        String postfixExpression = "";

        //loop thru input expression
        for(int i = 0; i < input.length(); i++)
        {
            //save the char at index i to current 
            char current = input.charAt(i);
            //check if the current char is an operand and add to output if true 
            if(Character.isLetter(current))
            {
                postfixExpression += current;
            }
            //add operator to stack if it is empty
            else if(operators.isEmpty())
            {
                operators.push(current);
            }
            //the stack is not empty and the current char is a operator
            else
            {
                //check for opening parenthesis -> add to operator stack
                if(current == '(')
                {
                    operators.push(current);
                }
                /*check for closing parenthesis ->
                * pop the operators until we see the opening parenthesis
                * pop the opening parenthesis 
                */
                else if(current == ')')
                {
                    while(operators.peek() != '(')
                    {
                        postfixExpression += operators.pop();
                    }
                    operators.pop();
                }
                //check for * and /
                else if(current == '*' || current == '/')
                {
                    //if peek is * or / then pop() and add it to the postfixExpression
                    //push the current char to the operator stack
                    if(operators.peek() == '*' || operators.peek() == '/')
                    {
                        postfixExpression += operators.pop();
                        operators.push(current);
                    }
                    //add to the operator stack
                    else
                    {
                        operators.push(current);
                    }
                }
                //checks for + and -
                else if(current == '+' || current == '-')
                {
                    /*if there is a higher precedence opperator when we peek() ->
                    * pop() and add it the postfixExpression
                    * push() the current char to the operator stack
                    */
                    if(operators.peek() == '*' || operators.peek() == '/')
                    {
                        postfixExpression += operators.pop();
                        operators.push(current);
                    }
                    //if peek is + or - then pop() and add it to the postfixExpression
                    //push the current char to the operator stack
                    else if(operators.peek() == '+' || operators.peek() == '-')
                    {
                        postfixExpression += operators.pop();
                        operators.push(current);
                    }
                    //add to the operator stack
                    else
                    {
                        operators.push(current);
                    }
                }
            }
        }
        //while the operator stack is not empty pop() and add the operators to the postfixExpression
        while(operators.isEmpty() == false)
        {
            postfixExpression += operators.pop();
        }
        //return the postfixExpression
        return postfixExpression;
    }
    public static void main(String[] args) throws Exception
    {
        //expression is a*b/(c-a)+d*e
        //postfix is ab*ca-/de*+

        //intialize operator stack
        LinkedStack<Character> operators = new LinkedStack<Character>();
        //intialize output string
        String postfixExpression = "";
        
        //manual conversion(infix to postfix)
        postfixExpression += 'a';
        operators.push('*');
        postfixExpression += 'b';
        postfixExpression += operators.pop();
        operators.push('/');
        operators.push('(');
        postfixExpression += 'c';
        operators.push('-');
        postfixExpression += 'a';
        operators.push(')');
        operators.pop();
        postfixExpression += operators.pop();
        operators.pop();
        postfixExpression += operators.pop();
        operators.push('+');
        postfixExpression += 'd';
        operators.push('*');
        postfixExpression += 'e';
        postfixExpression += operators.pop();
        postfixExpression += operators.pop();

        //using convertToPostfix() method
        System.out.println(convertToPostfix("a*b/(c-a)+d*e"));

        //manual conversion result
        System.out.println(postfixExpression);

        //expression is a*b/(c-a)+d*e
        //postfix is ab*ca-/de*+
        //a=2, b=3, c=4, d=5, e=6
        //answer should be 30
        int answer = 0;
        LinkedStack<Integer> operands = new LinkedStack<Integer>();
        int first, second;
        operands.push(2);
        operands.push(3);

        second = operands.pop();
        first = operands.pop();
        operands.push(first * second);

        operands.push(4);
        operands.push(2);

        second = operands.pop();
        first = operands.pop();
        operands.push(first - second);

        second = operands.pop();
        first = operands.pop();
        operands.push(first / second);

        operands.push(5);
        operands.push(6);

        second = operands.pop();
        first = operands.pop();
        operands.push(first * second);

        second = operands.pop();
        first = operands.pop();
        operands.push(first + second);
        answer = operands.pop();

        //manual eval postfix expression
        System.out.println(answer);
    }
}
