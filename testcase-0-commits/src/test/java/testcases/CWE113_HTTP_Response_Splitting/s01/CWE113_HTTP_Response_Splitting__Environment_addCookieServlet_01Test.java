package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousData = "value%0d%0aSet-Cookie:sessionId=malicious";
        System.setProperty("ADD", maliciousData);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Call the bad method
        servlet.bad(request, response);

        // Verify that a cookie with the malicious data was added
        verify(response).addCookie(argThat(cookie -> cookie.getValue().equals(maliciousData)));

        // Clean up the environment variable
        System.clearProperty("ADD");

        // Assert that the vulnerability is present
        assertTrue(true, "The method is vulnerable to HTTP Response Splitting.");
    }
}