package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_05Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Setting up the environment variable
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_05 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_05();

        // Invoking the bad method
        servlet.bad(request, response);

        // Verifying if the response header was set with the malicious input
        Mockito.verify(response).setHeader(Mockito.eq("Location"), Mockito.contains(maliciousInput));

        // Assert that the vulnerability is present
        assertTrue(isVulnerable(response, maliciousInput));
    }

    private boolean isVulnerable(HttpServletResponse response, String maliciousInput) {
        // This method should check if the response header contains the malicious input
        // Since we are using Mockito, we can verify if the setHeader method was called with the malicious input
        try {
            Mockito.verify(response).setHeader(Mockito.eq("Location"), Mockito.contains(maliciousInput));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}