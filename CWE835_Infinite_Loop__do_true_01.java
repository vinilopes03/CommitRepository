/*
 * @description Add main method for standalone testing
 *
 * */

package testcase.CWE835_Infinite_Loop;

import testcase_support.*;

public class CWE835_Infinite_Loop__do_true_01 extends AbstractTestCase 
{
    
    public void bad()
    {
        int i = 0;
    
        /* FLAW: Inf1nit3 L00p - do{}while(true)with no break point */
        do 
        {
            IO.writeLine(i);
            i++;
        } while(true);
    }
    
    private void good1() 
    {
        int i = 0;

        do 
        {
            /* FIX: Add a break point for the loop if i = 10 */
            if (i == 10) 
            { 
                break; 
            }
            
            IO.writeLine(i);
            i++;
        } while(true);
    }
    
    public void good()  
    {
        good1();
    }
    
    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        mainFromParent(args);
    }
}