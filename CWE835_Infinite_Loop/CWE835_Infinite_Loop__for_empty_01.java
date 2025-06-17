// Added CWE835_Infinite_Loop__for_empty_01 class
package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__for_empty_01 extends AbstractTestCase 
{    
    public void bad()
    {
        int i = 0;
        
        /* FLAW: Infinite Loop - for() with ...