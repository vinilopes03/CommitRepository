private void good2() 
{
    /* FIX: Add a break point for the loop if i = 10 */
    for (int i = 0; i < 11; i = (i + 1) % 256)
    {
        IO.writeLine(i);
    }
}