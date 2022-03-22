import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ResizableArrayStackTest extends ResizableArrayStack {

	@Test 
    public void testPush() throws Exception
    {
        ResizableArrayStack<Integer> mockResizableStack = new ResizableArrayStack<Integer>();
        
        mockResizableStack.push(4);
        mockResizableStack.push(3);
        
        assertEquals((Integer)3, (Integer)mockResizableStack.peek());
    }
	
	
}
