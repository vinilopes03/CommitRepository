/*
 * @description Infinite loop - for()
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__for_01 extends AbstractTestCase 
{

/* FIX: Add a break point for the loop if i = 10 */
if (i == 10) 
{ 
    break; 
}

/* FIX: Add a break point for the loop if i = 10 */
for (int i = 0; i < 11; i = (i + 1) % 256)
{
    IO.writeLine(i);
}

}
