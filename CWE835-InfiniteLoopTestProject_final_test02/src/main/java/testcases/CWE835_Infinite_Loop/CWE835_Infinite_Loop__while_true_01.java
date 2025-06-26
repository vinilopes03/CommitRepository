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
        while(true) {
            IO.writeLine(i);
            i++;
        }
    }
    
    private void good1() {
        // Method implementation to be added in subsequent commits
    }
    
    public void good() {
        // Method implementation to be added in subsequent commits
    }
    
    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // Main method implementation to be added in subsequent commits
    }
}