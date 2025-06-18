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
    
        /* FLAW: Infinite Loop - do{}while(true)with no break point */
        do 
        {
            IO.writeLine(i);
            i++;
        } while(true);
    }

}
