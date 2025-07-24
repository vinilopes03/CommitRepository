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

        // Set up the environment variable to simulate a malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Invoke the bad method
        servlet.bad(request, response);

        // Verify that the response added a cookie with the malicious input
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> cookie.getValue().equals(maliciousInput)));

        // Assert that the vulnerability is present
        assertTrue(isVulnerable(response, maliciousInput));
    }

    @Test
    public void testGoodB2G() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable to simulate a malicious input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Invoke the goodB2G method
        servlet.goodB2G(request, response);

        // Verify that the response added a cookie with the encoded input
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> !cookie.getValue().equals(maliciousInput)));

        // Assert that the vulnerability is not present
        assertFalse(isVulnerable(response, maliciousInput));
    }

    private boolean isVulnerable(HttpServletResponse response, String input) {
        // This method checks if the response is vulnerable by inspecting the cookies
        // added to the response. If a cookie contains the raw input, it indicates a vulnerability.
        Cookie[] cookies = response.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getValue().equals(input)) {
                    return true;
                }
            }
        }
        return false;
    }
}