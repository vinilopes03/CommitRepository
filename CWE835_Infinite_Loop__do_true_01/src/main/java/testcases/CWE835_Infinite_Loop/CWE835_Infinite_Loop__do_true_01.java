/*
 * @description Infinite loop - do{}while(true)
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__do_true_01 extends AbstractTestCase 
{
    private void good1() 
    {
        int i = 0; // Initialize counter

        do 
        {
            // FIX: Add a break point for the loop if i equals 10
            if (i == 10) 
            { 
                break; 
            }

            IO.writeLine(i); // Print current count
            i++; // Increment counter
        } while (true);
    }
    
    public void good()  
    {
        good1(); // Call the good1 method
    }
    
    public void bad()
    {
    }

    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        mainFromParent(args);
    }
}