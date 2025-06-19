/*
 * @description Infinite loop - do{}while()
 * This class demonstrates both a flawed and a corrected example of a do-while loop.
 */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__do_01 extends AbstractTestCase 
{
    // Constructor
    public CWE835_Infinite_Loop__do_01() {
        // Default constructor
    }

    public void bad()
    {
        int i = 0;
    
        /* FLAW: Infinite Loop - do{} with no break point */
        do 
        {
            IO.writeLine(i); // Output current value of i
            i = (i + 1) % 256; // Increment i, but loop never breaks
        } while(i >= 0); // This loop will run indefinitely
    }
    
    private void good1() 
    {
        int i = 0;

        do 
        {
            /* FIX: Add a break point for the loop if i = 10 */
            if (i == 10) 
            { 
                break; // Exit the loop if i equals 10
            }
            
            IO.writeLine(i); // Output current value of i
            i = (i + 1) % 256; // Increment i
        } while(i >= 0);
    }
    
    public void good()  
    {
        good1(); // Invoke the corrected method
    }    
    
    /* Below is the main(). It is only used when building this testcase on 
     * its own for testing or for building a binary to use in testing binary 
     * analysis tools. It is not used when compiling all the testcases as one 
     * application, which is how source code analysis tools are tested. 
	 */ 
    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        mainFromParent(args);
    }
}