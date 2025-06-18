/*
 * @description Infinite loop - for() 
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__for_empty_01 extends AbstractTestCase 
{

public void infiniteLoop() {
    int i = 0;

    for (;;) {
        IO.writeLine(i);
        i++;
    }
}

public void infiniteLoopWithBreak() {
    int i = 0;

    for (;;) {
        if (i == 10) {
            break;
        }
        
        IO.writeLine(i);
        i++;
    }
}

}
