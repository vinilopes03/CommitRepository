/*
 * @description Infinite loop - for()
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__for_01 extends AbstractTestCase 
{

public class CWE835_Infinite_Loop__for_01 extends AbstractTestCase 
{
    public void bad()
    {
        /* FLAW: Infinite Loop - for() with no break point */
        infiniteLoop();
    }
    
    private void infiniteLoop()
    {
        for (int i = 0; i >= 0; i = (i + 1) % 256)
        {
            IO.writeLine(i);
        }
    }
    
    // Existing good() and main() methods remain unchanged
}

}
