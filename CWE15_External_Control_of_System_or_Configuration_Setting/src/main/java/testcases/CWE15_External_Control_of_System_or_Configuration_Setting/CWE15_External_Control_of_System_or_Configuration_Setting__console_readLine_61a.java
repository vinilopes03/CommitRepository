/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61a.java
Label Definition File: CWE15_External_Control_of_System_or_Configuration_Setting.label.xml
Template File: sources-sink-61a.tmpl.java
*/
/*
 * @description
 * CWE: 15 External Control of System or Configuration Setting
 * BadSource: console_readLine Read data from the console using readLine()
 * GoodSource: A hardcoded string
 * Sinks:
 *    BadSink : Set the catalog name with the value of data
 * Flow Variant: 61 Data flow: data returned from one method to another in different classes in the same package
 *
 * */

package testcases.CWE15_External_Control_of_System_or_Configuration_Setting;

import testcasesupport.*;

import javax.servlet.http.*;

import java.sql.*;

import java.util.logging.Level;

public class CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61a extends AbstractTestCase
{
    public void bad() throws Throwable
    {
        String data = (new CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61b()).badSource();

        Connection dbConnection = null;

        try
        {
            dbConnection = IO.getDBConnection();
            // Set the catalog name with the value of data
            dbConnection.setCatalog(data);
        }
        catch (SQLException exceptSql)
        {
            IO.logger.log(Level.WARNING, "Error getting database connection", exceptSql);
        }
        finally
        {
            try
            {
                if (dbConnection != null)
                {
                    dbConnection.close();
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error closing Connection", exceptSql);
            }
        }
    }

    public void good() throws Throwable
    {
        goodG2B();
    }

    private void goodG2B() throws Throwable
    {
        // Method signature for goodG2B
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}