package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable to simulate untrusted input
        String untrustedInput = "value\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", untrustedInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();

        // Call the bad method
        servlet.bad(request, response);

        // Verify that a cookie with potentially malicious input was added
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> 
            cookie.getValue().equals(untrustedInput)
        ));
    }

    @Test
    public void testGoodB2G2MethodNoVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable to simulate untrusted input
        String untrustedInput = "value\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", untrustedInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();

        // Call the goodB2G2 method
        servlet.goodB2G2(request, response);

        // Verify that the cookie value is URL encoded, preventing HTTP Response Splitting
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> 
            !cookie.getValue().equals(untrustedInput) && cookie.getValue().contains("%0D%0A")
        ));
    }
}