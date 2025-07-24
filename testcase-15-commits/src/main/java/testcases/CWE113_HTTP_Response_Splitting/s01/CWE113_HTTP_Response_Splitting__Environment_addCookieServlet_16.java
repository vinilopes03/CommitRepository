package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;
import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_16 extends AbstractTestCaseServlet {
    
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in the next commit
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in the next commit
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in the next commit
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}