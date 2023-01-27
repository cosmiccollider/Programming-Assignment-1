public class counter 
{
    private long value = 2; // 0 and 1 wont count as primes
    
    public long getAndIncrement() 
    {
        long temp;
        synchronized(this)
        {
            temp  = value;
            value = temp + 1;
        }
        return temp;
    }
  }