package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_08 extends AbstractTestCaseServlet
{
    private boolean privateReturnsTrue() {
        return true;
    }

    private boolean privateReturnsFalse() {
        return false;
    }

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature for bad case
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature for goodG2B1
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature for goodG2B2
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature for goodB2G1
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature for goodB2G2
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature for good
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}