private void good1() 
{
    int i = 0;

    while (i >= 0)
    {
        // Noise: Add commented out code
        // int temp = i;

        // FIX: Add a break point for the loop if i = 10
        if (i == 10) 
        { 
            break; 
        }

        IO.writeLine(i);
        i = (i + 1) % 256;
    }
}