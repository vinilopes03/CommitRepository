/*
 * @description Infinite loop - while(true)
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__while_true_01 extends AbstractTestCase 
{
    public void bad() {
        int i = 0;

        /* FLAW: Infinite Loop - while(true) with no break point */
        while (true) {
            IO.writeLine(i);
            i++;
        }
    }
    
    private void good1() {
        // Method stub for good1
    }
    
    public void good() {
        // Method stub for good
    }    
    
    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        // Main method stub
        mainFromParent(args);
    }
}