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

    public void push(T newEntry) 
    {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    } //end push

    public T pop() 
    {
        checkIntegrity();
        if (isEmpty())
            return null;
        else
        {
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        }
    }//end pop

    public T peek() 
    {
        checkIntegrity();
        if (isEmpty())
            return null;
        else
            return stack[topIndex];
    }//end peek

    public boolean isEmpty() 
    {
        return topIndex < 0;
    }//end IsEmpty

    public void clear() 
    {
        checkIntegrity();
        while (topIndex > -1)
        {
            stack[topIndex] = null;
            topIndex--;
        }//end while
    }//end clear

    private void checkIntegrity()
   {
        if (!integrityOK)
            throw new SecurityException("ArrayBag object is corrupt.");
   }

    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose capacity " +
                                            "exeeds allowed maximum of " + MAX_CAPACITY);
    }

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