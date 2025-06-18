/*
 * @description Infinite loop - while()
 *
 * */

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__while_01 extends AbstractTestCase 
{

private void breakInfiniteLoop(int i) {
        if (i == 10) {
            break;
        }
    }

private void good1() {
        int i = 0;

        while (i >= 0) {
            breakInfiniteLoop(i);

            IO.writeLine(i);
            i = (i + 1) % 256;
        }
    }


}