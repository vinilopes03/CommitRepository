// Added CWE835_Infinite_Loop__for_01 class
package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__for_01 extends AbstractTestCase 
{
    public void bad()
    {
        /* FLAW: Infinite Loop - for() with no break point */
        for (int i = ...