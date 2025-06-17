private void good1() 
    {
        int i = 0;

        do 
        {
            /* FIX: Add a break point for the loop if i = 10 */
            if (i == 10) 
            { 
                break; 
            }
            
            IO.writeLine(i);
            i++;
        } while(true);
    }