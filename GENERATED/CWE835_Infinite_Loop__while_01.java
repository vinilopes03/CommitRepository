

package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__while_01 extends AbstractTestCase
{
    public void bad()
    {
        int i = 0;

        while (i >= 0)
        {
            IO.writeLine(i);
            i = (i + 1) % 256;
        }
    }

    private void good1()
    {
        int i = 0;

        while (i >= 0)
        {
            if (i == 10)
            {
                break;
            }

            IO.writeLine(i);
            i = (i + 1) % 256;
        }
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


package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__while_01 extends AbstractTestCase
{
    /**
     * This method contains a bad practice of an infinite loop.
     */
    public void bad()
    {
        int i = 0;

        while (i >= 0)
        {
            IO.writeLine(i);
            i = (i + 1) % 256;
        }
    }

    /**
     * This method demonstrates fixing the infinite loop by adding a break point.
     */
    private void good1()
    {
        int i = 0;

        while (i >= 0)
        {
            if (i == 10)
            {
                break;
            }

            IO.writeLine(i);
            i = (i + 1) % 256;
        }
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


package testcases.CWE835_Infinite_Loop;

import testcasesupport.*;

public class CWE835_Infinite_Loop__while_01 extends AbstractTestCase
{
    public void bad()
    {
        int i = 0;

        while (i >= 0)
        {
            IO.writeLine(i);
            i = (i + 1) % 256;
        }
    }

    private void good1()
    {
        int i = 0;

        while (i >= 0)
        {
            if (i == 10)
            {
                break;
            }

            IO.writeLine(i);
            i = (i + 1) % 256;
        }
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
