/*
 * @description Infinite loop - while()
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__while_01 extends AbstractTestCase 
{    
    /**
     * @description Contains the vulnerable code with an infinite loop.
     */
    public void bad()
    {
        int i = 0;
    
        /* FLAW: Infinite Loop - while() with no break point */
        while (i >= 0)
        {
            IO.writeLine(i);
            i = (i + 1) % 256;
        }
    }
    
    /**
     * @description Contains the fixed code with a break point added to the loop.
     */
    private void good1() 
    {
        int i = 0;

        while (i >= 0)
        {
            /* FIX: Add a break point for the loop if i = 10 */
            if (i == 10) 
            { 
                break; 
            }

            IO.writeLine(i);
            i = (i + 1) % 256;
        }
    }
    
    /**
     * @description Entry method for the good code path.
     */
    public void good()  
    {
        good1();
    }    
    
    /**
     * @description Main method for standalone test execution.
     */
    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        mainFromParent(args);
    }
}