package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_12Test {

    @Test
    public void testVulnerabilityPresent() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable to simulate the bad source
        String maliciousData = "value%0d%0aSet-Cookie:malicious=true";
        System.setProperty("ADD", maliciousData);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_12 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_12();

        // Call the bad method
        servlet.bad(request, response);

        // Verify if the cookie added contains the malicious data
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> cookie.getValue().equals(maliciousData)));
    }

    @Test
    public void testVulnerabilityNotPresent() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable to simulate the bad source
        String maliciousData = "value%0d%0aSet-Cookie:malicious=true";
        System.setProperty("ADD", maliciousData);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_12 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_12();

        // Call the goodB2G method
        servlet.good(request, response);

        // Verify if the cookie added does not contain the malicious data
        Mockito.verify(response, Mockito.never()).addCookie(Mockito.argThat(cookie -> cookie.getValue().equals(maliciousData)));
    }
}