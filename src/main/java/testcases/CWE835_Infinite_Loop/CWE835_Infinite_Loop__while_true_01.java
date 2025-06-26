/*
 * @description Infinite loop - while(true)
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

// Class demonstrating an infinite loop example
public class CWE835_Infinite_Loop__while_true_01 extends AbstractTestCase 
{
    // Method with an intentional infinite loop flaw
    public void bad() 
    {
        int i = 0;
    
        /* FLAW: Infinite Loop - while(true) with no break point */
        while(true)
        {
            IO.writeLine(i);  // Output the counter
            i++; // Increment counter
        }
    }

    // Method with a corrected loop
    private void good1() 
    {
        int i = 0;

        while(true)
        {
            /* FIX: Add a break point for the loop if i = 10 */
            if (i == 10) 
            { 
                break; 
            }
            
            IO.writeLine(i); // Output the counter
            i++; // Increment counter
        }
    }

    // Public method to call the good1 function
    public void good() 
    {
        good1();
    }

    // Main method for standalone execution
    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        mainFromParent(args);
    }
}