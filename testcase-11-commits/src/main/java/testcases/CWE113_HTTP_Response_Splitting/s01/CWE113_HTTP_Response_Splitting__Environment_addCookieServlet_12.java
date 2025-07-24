package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_12 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {}

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {}

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {}

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {}

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}