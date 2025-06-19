/*
 * @description Infinite loop - do{}while()
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__do_01 extends AbstractTestCase 
{
    
    public void bad()
    {
        int counter = 0;
    
        /* FLAW: Infinite Loop - do{} with no break point */
        do 
        {
            IO.writeLine(counter);
            counter = (counter + 1) % 256;
        } while(counter >= 0);
    }
    
    private void good1() 
    {
        int counter = 0;

        do 
        {
            /* FIX: Add a break point for the loop if counter = 10 */
            if (counter == 10) 
            { 
                break; 
            }
            
            IO.writeLine(counter);
            counter = (counter + 1) % 256;
        } while(counter >= 0);
    }
    
    public void good()  
    {
        good1();
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