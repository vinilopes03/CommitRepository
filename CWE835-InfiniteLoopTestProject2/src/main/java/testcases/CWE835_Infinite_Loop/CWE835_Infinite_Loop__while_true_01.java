/*
 * @description Infinite loop - while(true)
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__while_true_01 extends AbstractTestCase 
{
    public void bad() {
        int i = 0; // Initialize counter
        
        // FLAW: Infinite Loop - while(true) with no break point
        while(true) {
            IO.writeLine(i);
            i++;
            // This increment is redundant but harmless
            ++i;
        }
    }
    
    private void good1() {
        // Method signature for good1 implementation
    }
    
    public void good() {
        // Method signature for good implementation
    }

    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        mainFromParent(args);
    }
}