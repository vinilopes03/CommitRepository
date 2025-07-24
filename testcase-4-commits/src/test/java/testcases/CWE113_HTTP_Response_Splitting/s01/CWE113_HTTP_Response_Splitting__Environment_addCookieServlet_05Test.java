package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousInput = "value%0D%0ASet-Cookie:sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05();

        // Call the bad method
        servlet.bad(request, response);

        // Verify if the response contains a cookie with the malicious input
        verify(response).addCookie(argThat(cookie -> cookie.getValue().equals(maliciousInput)));

        // Assert that the vulnerability is present
        assertTrue(true, "The method is vulnerable to HTTP Response Splitting.");
    }

    @Test
    public void testGoodB2G2MethodNoVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousInput = "value%0D%0ASet-Cookie:sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_05();

        // Call the goodB2G2 method
        servlet.goodB2G2(request, response);

        // Verify if the response contains a cookie with the encoded input
        verify(response).addCookie(argThat(cookie -> !cookie.getValue().equals(maliciousInput)));

        // Assert that the vulnerability is not present
        assertFalse(false, "The method is not vulnerable to HTTP Response Splitting.");
    }
}