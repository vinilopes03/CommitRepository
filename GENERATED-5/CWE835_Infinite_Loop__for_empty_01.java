/*
 * @description Infinite loop - for() 
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__for_empty_01 extends AbstractTestCase 
{

private void breakInfiniteLoop(int i) {
    if (i == 10) {
        break;
    }
}

public void bad() {
    int i = 0;
    
    /* FLAW: Infinite Loop - for() with no break point */
    for (;;) {
        breakInfiniteLoop(i);
        
        IO.writeLine(i);
        i++;
    }
}

}
