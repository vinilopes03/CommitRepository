// Added CWE835_Infinite_Loop__while_01 class
package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__while_01 extends AbstractTestCase 
{    
    public void bad()
    {
        int i = 0;
    
        /* FLAW: Infinite Loop - while() with no br...