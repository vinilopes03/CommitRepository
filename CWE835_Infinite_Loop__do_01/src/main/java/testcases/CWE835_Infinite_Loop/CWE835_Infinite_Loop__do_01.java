/*
 * @description Infinite loop - do{}while()
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__do_01 extends AbstractTestCase 
{
    public void bad() {
        int i = 0; // Initialize counter

        /* FLAW: Infinite Loop - do{} with no break point */
        do {
            IO.writeLine(i); // Output current counter value
            i = (i + 1) % 256; // Increment and wrap-around logic
            // Note: This loop never ends naturally
        } while (i >= 0);
    }
    
    private void good1() {
        // Implementation will be added in the next commit
    }
    
    public void good() {
        // Call the good1 method
    }
    
    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}