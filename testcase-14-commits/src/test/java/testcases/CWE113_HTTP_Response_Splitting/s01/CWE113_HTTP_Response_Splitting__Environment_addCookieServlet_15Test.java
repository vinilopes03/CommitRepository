package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_15Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Set up the environment variable
        String maliciousData = "test\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousData);

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_15 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_15();

        // Call the bad method
        servlet.bad(request, response);

        // Verify if the response.addCookie was called with potentially malicious data
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            String value = cookie.getValue();
            return value.equals(maliciousData);
        }));
    }

    @Test
    public void testGoodMethodNoVulnerability() throws Throwable {
        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_15 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_15();

        // Call the good methods
        servlet.goodG2B1(request, response);
        servlet.goodG2B2(request, response);
        servlet.goodB2G1(request, response);
        servlet.goodB2G2(request, response);

        // Verify that no cookies with malicious data are added
        Mockito.verify(response, Mockito.never()).addCookie(Mockito.argThat(cookie -> {
            String value = cookie.getValue();
            return value.contains("\r") || value.contains("\n");
        }));
    }
}