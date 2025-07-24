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
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Set up the environment variable
        String maliciousData = "test\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousData);

        // Call the bad method
        servlet.bad(request, response);

        // Verify that a cookie with the malicious data was added
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> 
            cookie.getValue().equals(maliciousData)
        ));

        // Clean up the environment variable
        System.clearProperty("ADD");
    }

    @Test
    public void testGoodG2B() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Call the goodG2B method
        servlet.goodG2B(request, response);

        // Verify that a cookie with the hardcoded safe data was added
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> 
            cookie.getValue().equals("foo")
        ));
    }

    @Test
    public void testGoodB2G() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Set up the environment variable
        String maliciousData = "test\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousData);

        // Call the goodB2G method
        servlet.goodB2G(request, response);

        // Verify that a cookie with the encoded data was added
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> 
            !cookie.getValue().equals(maliciousData) && cookie.getValue().contains("test%0D%0ASet-Cookie%3A+sessionId%3Dmalicious")
        ));

        // Clean up the environment variable
        System.clearProperty("ADD");
    }
}