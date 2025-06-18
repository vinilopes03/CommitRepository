

public void bad()
{
    /* FLAW: Infinite Loop - for() with no break point */
    for (int i = 0; i >= 0; i = (i + 1) % 256)
    {
        if (i == 10) 
        { 
            break; 
        }
        IO.writeLine(i);
    }
}


private void good1() 
{
    for (int i = 0; i >= 0; i = (i + 1) % 256)
    {
        /* FIX: Add a break point for the loop if i = 10 */
        if (i == 10) 
        { 
            break; 
        }
        IO.writeLine(i);
    }
}

private void good2() 
{
    /* FIX: Add a break point for the loop if i = 10 */
    for (int i = 0; i < 11; i = (i + 1) % 256)
    {
        IO.writeLine(i);
    }
}
