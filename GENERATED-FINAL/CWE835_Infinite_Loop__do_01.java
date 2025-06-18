/*
 * @description Infinite loop - do{}while()
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__do_01 extends AbstractTestCase 
{

public void bad()
{
    int i = 0;

    /* FLAW: Infinite Loop - do{} with no break point */
    while(i < 10) 
    {
        IO.writeLine(i);
        i = (i + 1) % 256;
    }
}


}