

/*
 * @description Set up project structure and initial bad implementation
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__do_true_01 extends AbstractTestCase 
{
    
    public void bad()
    {
        int i = 0;
    
        /* FLAW: Infinite Loop - do{}while(true) with no break point */
        do 
        {
            IO.writeLine(i);
            i++;
        } while(true);
    }
    
    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        mainFromParent(args);
    }
}


/*
 * @description Implement good1 method with a break point
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__do_true_01 extends AbstractTestCase 
{
    
    public void bad()
    {
        int i = 0;
    
        /* FLAW: Infinite Loop - do{}while(true) with no break point */
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
    
    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        mainFromParent(args);
    }
}
