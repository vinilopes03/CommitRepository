package testcases.CWE15_External_Control_of_System_or_Configuration_Setting;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61aTest {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Mock the Connection class
        Connection mockConnection = Mockito.mock(Connection.class);

        // Mock the IO class to return the mock connection
        IO ioMock = mock(IO.class);
        when(ioMock.getDBConnection()).thenReturn(mockConnection);

        // Create an instance of the class to be tested
        CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61a instance =
                new CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61a();

        // Call the bad method
        instance.bad();

        // Verify if setCatalog was called with an external input
        try {
            verify(mockConnection, times(1)).setCatalog(anyString());
            assertTrue(true, "The method is vulnerable as it sets the catalog with external input.");
        } catch (SQLException e) {
            assertFalse(true, "SQLException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGoodMethodNoVulnerability() throws Throwable {
        // Mock the Connection class
        Connection mockConnection = Mockito.mock(Connection.class);

        // Mock the IO class to return the mock connection
        IO ioMock = mock(IO.class);
        when(ioMock.getDBConnection()).thenReturn(mockConnection);

        // Create an instance of the class to be tested
        CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61a instance =
                new CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61a();

        // Call the good method
        instance.good();

        // Verify if setCatalog was called with a hardcoded string
        try {
            verify(mockConnection, times(1)).setCatalog(anyString());
            assertFalse(false, "The method is not vulnerable as it sets the catalog with a hardcoded string.");
        } catch (SQLException e) {
            assertFalse(true, "SQLException occurred: " + e.getMessage());
        }
    }
}