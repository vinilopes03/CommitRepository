package testcases.CWE113_HTTP_Response_Splitting.s01;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    @Test
    public void testBadMethodForVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();

        // Set up the environment variable to simulate the vulnerability
        String envVarValue = "value\r\nSet-Cookie: sessionId=malicious";
        setEnvironmentVariable("ADD", envVarValue);

        // Call the bad method
        servlet.bad(request, response);

        // Capture the cookie added to the response
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value contains the malicious part
            return cookie.getValue().contains("\r\nSet-Cookie: sessionId=malicious");
        }));
    }

    private void setEnvironmentVariable(String key, String value) {
        try {
            java.util.Map<String, String> env = System.getenv();
            java.lang.reflect.Field field = env.getClass().getDeclaredField("m");
            field.setAccessible(true);
            java.util.Map<String, String> writableEnv = (java.util.Map<String, String>) field.get(env);
            writableEnv.put(key, value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set environment variable", e);
        }
    }
}