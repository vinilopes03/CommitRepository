public void bad()
{
    /* FLAW: Infinite Loop - for() with no break point */
    for (int i = 0; i >= 0; i = (i + 1) % 256)
    {
        IO.writeLine(i);
    }
}