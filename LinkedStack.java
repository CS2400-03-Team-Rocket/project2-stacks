public class LinkedStack<T> implements StackInterface<T>{
    private Node topNode;

    public LinkedStack()
    {
        topNode = null;
    }

    /**adds a new entry to the LinkedStack
    @param newEntry an entry to be added to the LinkedStack
    */
    public void push(T newEntry){
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }

    /**pops the top element from the LinkedStack
     * @return the element that was popped from the LinkedStack 
     * @throws Exception if the LinkedStack is empty when we call peek
     */
    public T pop() throws Exception
    {
        T top = peek();
        topNode = topNode.getNextNode();
        return top;
    }

    /**returns the top element in the LinkedStack 
     * @return the top element in the stack
     * @throws Exception if the LinkedStack empty 
     */
    public T peek() throws Exception
    {
        if(isEmpty())
        {
            throw new Exception("stack is empty");
        }
        else
        {
            return topNode.getData();
        }
        
    }

    /**returns a true or false value based on if the LinkedStack is empty or not 
     * @return a boolean based on if the LinkedStack is empty or not
     */
    public boolean isEmpty()
    {
        return topNode == null;
    }

    /**clears the LinkedStack by setting the topNode to null
     * the rest of the entries will be garbage collected
     */
    public void clear()
    {
        topNode = null;
    }

    /**converts an infix expression to postfix
     * @param input an infix string to be converted to postfix
     * @return a string containing the computed postfix expression 
     * @throws Exception when the stack empty and we call peek
     */
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
    
    private class Node
    {
        private T data;
        private Node next;
        
        /**
         * Used to construct first Node
         * @param dataLink piece of data stored in initial Node
         */
        private Node (T dataPiece) {
            this(dataPiece, null);
        }
        
        /**
         * Sets portion of data held in this Node, and the pointer to the next
         * @param dataPiece sets a reference to a peice of data
         * @param next points to next linked Node
         */
        private Node(T dataPiece, Node next) {
            data = dataPiece;
            this.next = next;
        }
        private T getData()
        {
            return this.data;
        }
        private Node getNextNode()
        {
            return this.next;
        }
    }
}