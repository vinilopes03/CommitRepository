package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_14Test {

    @Test
    public void testBad() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousData = "value\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousData);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_14 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_14();

        // Call the bad method
        servlet.bad(request, response);

        // Verify that the response contains the malicious cookie
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            return cookie.getName().equals("lang") && cookie.getValue().equals(maliciousData);
        }));
    }

    @Test
    public void testGoodB2G2() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousData = "value\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousData);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_14 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_14();

        // Call the goodB2G2 method
        servlet.goodB2G2(request, response);

        // Verify that the response does not contain the malicious cookie
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            return cookie.getName().equals("lang") && !cookie.getValue().equals(maliciousData);
        }));
    }
}