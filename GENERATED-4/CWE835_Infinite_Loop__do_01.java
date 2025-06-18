public void bad()
{
    int i = 0;

    /* FLAW: Infinite Loop - do{} with no break point */
    do 
    {
        IO.writeLine(i);
        i = (i + 1) % 256;
    } while(i >= 0);
}