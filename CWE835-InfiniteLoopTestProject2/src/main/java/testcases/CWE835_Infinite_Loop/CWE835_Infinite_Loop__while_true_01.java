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
        int i = 0; // Initialize counter

        while(true) {
            // FIX: Add a break point for the loop if i = 10
            if (i == 10) { 
                break; 
            }
            
            IO.writeLine(i);
            i++;
            
            // Redundant check for demonstration
            if (i > 10) {
                // Should never happen
                break;
            }
        }
    }
    
    public void good() {
        good1();
    }

    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        mainFromParent(args);
    }
}