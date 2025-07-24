package testcases.CWE113_HTTP_Response_Splitting.s01;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02Test {

    private CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testBad() throws Throwable {
        // Set up the environment variable to simulate bad input
        String maliciousInput = "value%0D%0ASet-Cookie:sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Execute the bad method
        servlet.bad(request, response);

        // Verify that the cookie was added with the malicious input
        verify(response).addCookie(argThat(cookie -> cookie.getValue().equals(maliciousInput)));
    }

    @Test
    public void testGoodB2G1() throws Throwable {
        // Set up the environment variable to simulate bad input
        String maliciousInput = "value%0D%0ASet-Cookie:sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Execute the goodB2G1 method
        servlet.goodB2G1(request, response);

        // Verify that the cookie was added with encoded input
        verify(response).addCookie(argThat(cookie -> !cookie.getValue().equals(maliciousInput)));
    }

    @Test
    public void testGoodB2G2() throws Throwable {
        // Set up the environment variable to simulate bad input
        String maliciousInput = "value%0D%0ASet-Cookie:sessionId=malicious";
        System.setProperty("ADD", maliciousInput);

        // Execute the goodB2G2 method
        servlet.goodB2G2(request, response);

        // Verify that the cookie was added with encoded input
        verify(response).addCookie(argThat(cookie -> !cookie.getValue().equals(maliciousInput)));
    }
}