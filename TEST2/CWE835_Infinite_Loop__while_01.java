/*
 * @description Infinite loop - while()
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__while_01 extends AbstractTestCase 
{    
    // This is a bad method with an infinite loop
    public void bad()
    {
        // Start at 0
        int i = 0;
    
        // Infinite loop with no break point
        while (i >= 0)
        {
            // Print the value of i
            IO.writeLine(i);
            // Increment i by 1 and wrap around at 256
            i = (i + 1) % 256;
        }
    }
    
    // This is a good method with a break point
    private void good1() 
    {
        // Start at 0
        int i = 0;

        // Loop until i reaches 10
        while (i >= 0)
        {
            // Add a break point once i equals 10
            if (i == 10) 
            { 
                break; 
            }

            // Print the value of i
            IO.writeLine(i);
            // Increment i by 1 and wrap around at 256
            i = (i + 1) % 256;
        }
    }
    
    // Wrapper method for the good1() method
    public void good()  
    {
        good1();
    }    
    
    // Main method for standalone testing
    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        mainFromParent(args);
    }
}