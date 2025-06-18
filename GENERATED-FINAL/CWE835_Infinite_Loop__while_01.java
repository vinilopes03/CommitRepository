/*
 * @description Infinite loop - while()
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__while_01 extends AbstractTestCase 
{

/* FIX: Add a break condition for the loop if i reaches a certain value */
int breakPoint = 10;
while (i >= 0)
{
    if (i == breakPoint)
    {
        break;
    }

    IO.writeLine(i);
    i = (i + 1) % 256;
}

}
