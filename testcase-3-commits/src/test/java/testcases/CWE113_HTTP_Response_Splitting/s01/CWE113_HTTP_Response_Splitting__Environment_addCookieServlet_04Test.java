package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04Test {

    @Test
    public void testBad() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable to simulate the vulnerability
        String maliciousData = "value\r\nSet-Cookie: sessionId=malicious";
        Mockito.when(System.getenv("ADD")).thenReturn(maliciousData);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04();

        // Call the bad method
        servlet.bad(request, response);

        // Verify that the cookie was added with the malicious data
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> cookie.getValue().equals(maliciousData)));

        // Assert that the vulnerability is present
        assertTrue(isVulnerable(response));
    }

    @Test
    public void testGoodB2G2() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable to simulate the vulnerability
        String maliciousData = "value\r\nSet-Cookie: sessionId=malicious";
        Mockito.when(System.getenv("ADD")).thenReturn(maliciousData);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04();

        // Call the goodB2G2 method
        servlet.goodB2G2(request, response);

        // Verify that the cookie was added with the encoded data
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> !cookie.getValue().equals(maliciousData)));

        // Assert that the vulnerability is not present
        assertFalse(isVulnerable(response));
    }

    private boolean isVulnerable(HttpServletResponse response) {
        // This method should check if the response contains a cookie with unencoded data
        // For simplicity, we assume that if a cookie with the malicious data is added, it's vulnerable
        try {
            Mockito.verify(response).addCookie(Mockito.argThat(cookie -> cookie.getValue().contains("\r\n")));
            return true;
        } catch (Throwable e) {
            return false;
        }
    }
}