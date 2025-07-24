package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_16Test {

    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_16 servlet = new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_16();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set environment variable to simulate bad input
        String originalEnv = System.getenv("ADD");
        try {
            setEnvironmentVariable("ADD", "en-US\nContent-Length: 0");

            // Act
            servlet.bad(request, response);

            // Assert
            Mockito.verify(response).setHeader(Mockito.eq("Location"), Mockito.contains("en-US\nContent-Length: 0"));
            assertTrue(true, "The servlet is vulnerable to HTTP Response Splitting.");
        } finally {
            // Restore original environment variable
            setEnvironmentVariable("ADD", originalEnv);
        }
    }

    @Test
    public void testGoodB2G() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_16 servlet = new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_16();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set environment variable to simulate bad input
        String originalEnv = System.getenv("ADD");
        try {
            setEnvironmentVariable("ADD", "en-US\nContent-Length: 0");

            // Act
            servlet.goodB2G(request, response);

            // Assert
            Mockito.verify(response).setHeader(Mockito.eq("Location"), Mockito.not(Mockito.contains("\n")));
            assertFalse(false, "The servlet is not vulnerable to HTTP Response Splitting when using goodB2G.");
        } finally {
            // Restore original environment variable
            setEnvironmentVariable("ADD", originalEnv);
        }
    }

    // Helper method to set environment variables for testing
    private void setEnvironmentVariable(String key, String value) throws Exception {
        java.util.Map<String, String> env = System.getenv();
        java.lang.reflect.Field field = env.getClass().getDeclaredField("m");
        field.setAccessible(true);
        java.util.Map<String, String> writableEnv = (java.util.Map<String, String>) field.get(env);
        if (value == null) {
            writableEnv.remove(key);
        } else {
            writableEnv.put(key, value);
        }
    }
}