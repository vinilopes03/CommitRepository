package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousInput = "value\r\nSet-Cookie: name=value";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();

        // Call the bad method
        servlet.bad(request, response);

        // Verify that a cookie with the malicious input was added
        verify(response, times(1)).addCookie(any(Cookie.class));

        // Check if the vulnerability is present
        Cookie addedCookie = new Cookie("lang", maliciousInput);
        assertTrue(addedCookie.getValue().contains("\r\n"), "The application is vulnerable to HTTP Response Splitting.");
    }
}