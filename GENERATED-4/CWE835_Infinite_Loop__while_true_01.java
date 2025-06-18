public void bad()
    {
        int i = 0;
    
        /* FLAW: Infinite Loop - while(true) with no break point */
        while(true)
        {
            IO.writeLine(i);
            i++;
            if (i == 10) 
            { 
                break; 
            }
        }
    }