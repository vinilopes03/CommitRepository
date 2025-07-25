package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_11Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousData = "test\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousData);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_11 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_11();

        // Call the bad method
        servlet.bad(request, response);

        // Verify if the response contains a cookie with the malicious data
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> 
            cookie.getValue().contains("\r\n") || cookie.getValue().contains("\n")
        ));
    }

    @Test
    public void testGoodMethodNoVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousData = "test\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousData);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_11 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_11();

        // Call the good methods
        servlet.good(request, response);

        // Verify that the response does not contain a cookie with the malicious data
        Mockito.verify(response, Mockito.never()).addCookie(Mockito.argThat(cookie -> 
            cookie.getValue().contains("\r\n") || cookie.getValue().contains("\n")
        ));
    }
}