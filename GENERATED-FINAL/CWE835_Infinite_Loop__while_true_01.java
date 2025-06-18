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

    while(true)
    {
        IO.writeLine(i);
        i++;
    }
}

}
