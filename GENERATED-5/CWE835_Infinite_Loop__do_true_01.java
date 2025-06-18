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

    do
    {
        /* FIX: Add a break point for the loop if i = 10 */
        if (i == 10)
        {
            break;
        }

        IO.writeLine(i);
        i++;
    } while (true);
}

public void good()
{
    good1();
}

}
