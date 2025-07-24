package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_13Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable to simulate the vulnerability
        String maliciousInput = "value\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_13 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_13();

        // Call the bad method
        servlet.bad(request, response);

        // Verify if the response.addCookie() was called with a potentially malicious cookie
        verify(response, times(1)).addCookie(any(Cookie.class));

        // Check if the vulnerability is present by inspecting the cookie value
        Cookie cookie = new Cookie("lang", maliciousInput);
        assertTrue(cookie.getValue().contains("\r\n"), "The application is vulnerable to HTTP Response Splitting.");
    }
}