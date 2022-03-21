import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest extends Calculator
{
    
    @Test 
    public void testPush() throws Exception
    {
        LinkedStack<Integer> mockLinkedStack = new LinkedStack<Integer>();
        
        mockLinkedStack.push(4);
        mockLinkedStack.push(3);
        
        assertEquals((Integer)3, (Integer)mockLinkedStack.peek());
    }

    @Test 
    public void testPop() throws Exception
    {
        LinkedStack<Integer> mockLinkedStack = new LinkedStack<Integer>();
        
        mockLinkedStack.push(4);
        mockLinkedStack.push(3);
        mockLinkedStack.pop();

        assertEquals((Integer)4, (Integer)mockLinkedStack.peek());
    }

    @Test
    public void testPeek() throws Exception
    {
        LinkedStack<Integer> mockLinkedStack = new LinkedStack<Integer>();
        
        mockLinkedStack.push(4);
        mockLinkedStack.push(3);
        mockLinkedStack.pop();
        mockLinkedStack.push(7);

        assertEquals((Integer)7, (Integer)mockLinkedStack.peek());
    }

    @Test
    public void testIsEmpty()
    {
        LinkedStack<Integer> mockLinkedStack = new LinkedStack<Integer>();
        
        mockLinkedStack.push(4);
        mockLinkedStack.push(3);
        mockLinkedStack.push(7);

        assertEquals(false, mockLinkedStack.isEmpty());

        mockLinkedStack.clear();

        assertEquals(true, mockLinkedStack.isEmpty());
    }

    @Test
    public void testClear()
    {
        LinkedStack<Integer> mockLinkedStack = new LinkedStack<Integer>();
        
        mockLinkedStack.push(4);
        mockLinkedStack.push(3);
        mockLinkedStack.push(7);
        mockLinkedStack.clear();

        assertEquals(true, mockLinkedStack.isEmpty());
    }

    @Test
    public void testConvertToPostfix() throws Exception
    {
        String infix = "a*b/(c-a)+d*e";
        String expectedPosfix = "ab*ca-/de*+";

        String result = convertToPostfix(infix);

        assertEquals(expectedPosfix, result);
    }


}
