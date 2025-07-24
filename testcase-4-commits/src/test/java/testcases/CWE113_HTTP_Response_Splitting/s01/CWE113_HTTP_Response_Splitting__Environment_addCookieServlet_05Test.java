package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousInput = "value\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05();

        // Call the bad method
        servlet.bad(request, response);

        // Verify that the response.addCookie() was called with the malicious input
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value contains CRLF characters, indicating a vulnerability
            return cookie.getValue().contains("\r\n");
        }));
    }

    @Test
    public void testGoodB2G2MethodNoVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousInput = "value\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05();

        // Call the goodB2G2 method
        servlet.goodB2G2(request, response);

        // Verify that the response.addCookie() was called with a properly encoded value
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value does not contain CRLF characters, indicating no vulnerability
            return !cookie.getValue().contains("\r\n");
        }));
    }
}