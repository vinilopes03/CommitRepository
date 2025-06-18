/*
 * @description Infinite loop - for() 
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__for_empty_01 extends AbstractTestCase 
{

private void good1() 
{
    int i = 0;
    
    for (;;)
    {
        /* FIX: Add a break point for the loop if i = 10 */
        if (i == 10) 
        { 
            break; 
        }
        
        IO.writeLine(i);
        i++;
    }
}

public void good()  
{
    good1();
}

}
