/*
 * @description Infinite loop - for()
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__for_01 extends AbstractTestCase 
{
    // Method to demonstrate bad practice of an infinite loop
    public void bAd()
    {
        /* FLAW: Infinite Loop - for() with no break point */
        for (int i = 0; i >= 0; i = (i + 1) % 256)
        {
            IO.writeLine(i);
        }
    }
    
    // Method with a fix to add a break point in the loop
    private void gOoD1() 
    {
        for (int i = 0; i >= 0; i = (i + 1) % 256)
        {
            /* FIX: Add a break point for the loop if i = 10 */
            if (i == 10) 
            { 
                break; 
            }
            IO.writeLine(i);
        }
    }
    
    // Method with a fix to add a break point in the loop
    private void gOoD2() 
    {
        /* FIX: Add a break point for the loop if i = 10 */
        for (int i = 0; i < 11; i = (i + 1) % 256)
        {
            IO.writeLine(i);
        }
    }
    
    // Method to demonstrate good practice with added break points
    public void gOoD()  
    {
        gOoD1();
        gOoD2();
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