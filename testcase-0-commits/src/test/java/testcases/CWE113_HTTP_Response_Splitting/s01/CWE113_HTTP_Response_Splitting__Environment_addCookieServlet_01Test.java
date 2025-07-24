package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    @Test
    public void testBad() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Set an environment variable with a value that could cause HTTP Response Splitting
        String maliciousValue = "value%0d%0aSet-Cookie:sessionId=malicious";
        System.setProperty("ADD", maliciousValue);

        // Call the bad method
        servlet.bad(request, response);

        // Verify if the response contains a cookie with the malicious value
        Mockito.verify(response).addCookie(Mockito.argThat((Cookie cookie) -> {
            return cookie.getValue().equals(maliciousValue);
        }));
    }

    @Test
    public void testGoodB2G() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Set an environment variable with a value that could cause HTTP Response Splitting
        String maliciousValue = "value%0d%0aSet-Cookie:sessionId=malicious";
        System.setProperty("ADD", maliciousValue);

        // Call the goodB2G method
        servlet.goodB2G(request, response);

        // Verify if the response contains a cookie with the encoded value
        Mockito.verify(response).addCookie(Mockito.argThat((Cookie cookie) -> {
            return cookie.getValue().equals(java.net.URLEncoder.encode(maliciousValue, "UTF-8"));
        }));
    }
}