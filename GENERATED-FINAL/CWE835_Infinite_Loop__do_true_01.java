/*
 * @description Infinite loop - do{}while(true)
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__do_true_01 extends AbstractTestCase 
{

if (i == 10) 
            { 
                break; 
            }

private void good1() 
    {
        int i = 0;

        do 
        {
            if (i == 10) 
            { 
                break; 
            }
            
            IO.writeLine(i);
            i++;
        } while(true);
    }

}
