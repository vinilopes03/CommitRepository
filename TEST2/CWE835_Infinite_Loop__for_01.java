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