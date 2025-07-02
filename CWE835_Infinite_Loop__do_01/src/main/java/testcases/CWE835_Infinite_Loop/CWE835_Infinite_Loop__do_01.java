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

        /* FIX: Add a break point for the loop if i = 10 */
        do {
            if (i == 10) {
                break; // Exit loop when counter reaches 10
            }
            IO.writeLine(i); // Output current counter value
            i = (i + 1) % 256; // Increment and wrap-around logic
        } while (i >= 0);
    }
    
    private void good1() {
        int i = 0; // Initialize counter

        /* FIX: Add a break point for the loop if i = 10 */
        do {
            if (i == 10) {
                break; // Exit loop when counter reaches 10
            }
            IO.writeLine(i); // Output current counter value
            i = (i + 1) % 256; // Increment and wrap-around logic
        } while (i >= 0);
    }
    
    public void good() {
        good1(); // Call the good1 method
    }
    
    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}