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