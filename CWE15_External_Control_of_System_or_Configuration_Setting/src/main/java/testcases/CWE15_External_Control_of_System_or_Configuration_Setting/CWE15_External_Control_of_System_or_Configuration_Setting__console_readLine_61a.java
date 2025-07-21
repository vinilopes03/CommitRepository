package testcases.CWE15_External_Control_of_System_or_Configuration_Setting;

import testcasesupport.*;

import java.sql.*;

import java.util.logging.Level;

public class CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61a extends AbstractTestCase {
    
    public void bad() throws Throwable {
        String data = (new CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61b()).badSource();

        Connection dbConnection = null;

        try {
            dbConnection = IO.getDBConnection();
            dbConnection.setCatalog(data);
        } catch (SQLException exceptSql) {
            IO.logger.log(Level.WARNING, "Error getting database connection", exceptSql);
        } finally {
            try {
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException exceptSql) {
                IO.logger.log(Level.WARNING, "Error closing Connection", exceptSql);
            }
        }
    }

    public void good() throws Throwable {
        // Method signature for good
    }

    private void goodG2B() throws Throwable {
        // Method signature for goodG2B
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}