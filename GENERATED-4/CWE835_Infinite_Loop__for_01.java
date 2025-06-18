private void good1() 
{
    for (int i = 0; i < 11; i = (i + 1) % 256)
    {
        IO.writeLine(i);
    }
}