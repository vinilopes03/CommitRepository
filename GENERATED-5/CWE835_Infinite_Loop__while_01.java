/*
 * @description Infinite loop - while()
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__while_01 extends AbstractTestCase 
{

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

public void bad()
{
    int i = 0;

    /* FLAW: Infinite Loop - while() with no break point */
    while (i >= 0)
    {
        IO.writeLine(i);
        i = (i + 1) % 256;

        // Break the loop if i reaches 10
        if (i == 10) {
            break;
        }
    }
}

}
