import java.util.Arrays;

public class ResizableArrayStack<T> implements StackInterface<T>
{

    private T[] stack;    // Array of stack entries
    private int topIndex; // Index of top entry
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ResizableArrayStack()
    {
        this(DEFAULT_CAPACITY);
    } //end default constructor

    public ResizableArrayStack(int givenCapacity)
    {
        integrityOK = false;
        checkCapacity(givenCapacity);
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[givenCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    } //end constructor

    /**adds a new entry to the ResizableArrayStack
    @param newEntry an entry to be added to the ResizableArrayStack
    */
    public void push(T newEntry) 
    {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    } //end push

    /**pops the top element from the ResizableArrayStack
     * @return the element that was popped from the ResizableArrayStack
     */
    public T pop() throws Exception
    {
        checkIntegrity();
        if (isEmpty())
        	throw new Exception("stack is empty");
        else
        {
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        }
    }//end pop

    /**returns the top element in the ResizableArrayStack
     * @return the top element in the stack, null if stack is empty
     * @throws Exception if the stack is empty
     */
    public T peek() throws Exception
    {
        checkIntegrity();
        if (isEmpty())
            throw new Exception("stack is empty");
        else
            return stack[topIndex];
    }//end peek

    /**returns a true or false value based on if the ResizableArrayStack is empty or not 
     * @return a boolean based on if the ResizableArrayStack is empty or not
     */
    public boolean isEmpty() 
    {
        return topIndex < 0;
    }//end IsEmpty

    /**clears the LinkedStack by setting the topNode to null
     * the rest of the entries will be garbage collected
     */
    public void clear() 
    {
        checkIntegrity();
        while (topIndex > -1)
        {
            stack[topIndex] = null;
            topIndex--;
        }//end while
    }//end clear

    /**
     * throws SecurityException if ResizableArrayStack object has not been initialized
     */
    private void checkIntegrity()
   {
        if (!integrityOK)
            throw new SecurityException("ResizableArrayStack object is corrupt.");
   }

    /**
     * throws IllegalStateException if hypothetical capacity is greater than the set MAX_CAPACITY
     * @param capacity
     */
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a stack whose capacity " +
                                            "exeeds allowed maximum of " + MAX_CAPACITY);
    }

    /**
     * doubles the length of the stack if the current capacity is reached
     */
    private void ensureCapacity()
    {
        if (topIndex >= stack.length - 1)
        {
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    }
}