/*
 * @description Infinite loop - while(true)
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__while_true_01 extends AbstractTestCase 
{
    public void bad()
    {
        int i = 0;
    
        /* FLAW: Infinite Loop - while(true) with no break point */
        while(true)
        {
            // Print the current value of i
            IO.writeLine(i);
            i++;
        }
    }
    
    private void good1() 
    {
        int i = 0;
        
        while(true)
        {
            /* FIX: Add a break point for the loop if i = 10 */
            if (i == 10) 
            { 
                // Break out of the loop when i reaches 10
                break; 
            }
            
            IO.writeLine(i); // Print the current value of i
            i++;
        }
    }
    
    public void good()  
    {
        good1();
    }    
    
    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        // Run the test from the parent class
        mainFromParent(args);
    }
}