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
    do 
    {
        IO.writeLine(i);
        i = (i + 1) % 256;
    } while(i >= 0);
}

private void good1() 
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
        i = (i + 1) % 256;
    } while(i >= 0);
}

}
