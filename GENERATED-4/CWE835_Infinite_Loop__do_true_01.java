

public void bad()
    {
        int i = 0;
    
        /* FLAW: Infinite Loop - do{}while(true) with no break point */
        do 
        {
            IO.writeLine(i);
            i++;
        } while(true);
    }
