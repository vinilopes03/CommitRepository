/*
 * @description Infinite loop - do{}while(true)
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__do_true_01 extends AbstractTestCase 
{
    public void bad()
    {
        int i = 0;
    
        /* FLAW: Infinite Loop - do{}while(true) with no break point */
        do 
        {
            IO.writeLine(i); // Print the current value of i
            i++;
        } while(true); // Infinite loop
    }
    
    private void good1() 
    {
        int i = 0;

        do 
        {
            /* FIX: Add a break point for the loop if i = 10 */
            if (i == 10) 
            {
                break; 
            }

            IO.writeLine(i); // Print the current value of i
            i++;
        } while(true); // Loop with a break condition
    }
    
    public void good()  
    {
        good1(); // Call the good1 method
    }

    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        mainFromParent(args); // Execute the main method from parent
    }
}