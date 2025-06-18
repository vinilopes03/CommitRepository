/*
 * @description Infinite loop - while(true)
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__while_true_01 extends AbstractTestCase 
{

private void fixInfiniteLoop(int i) 
    {
        /* FIX: Add a break point for the loop if i = 10 */
        if (i == 10) 
        { 
            break; 
        }
        
        IO.writeLine(i);
        i++;
    }

public void bad()
    {
        int i = 0;
    
        while(true)
        {
            fixInfiniteLoop(i);
        }
    }

}
