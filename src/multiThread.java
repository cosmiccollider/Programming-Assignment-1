import java.util.ArrayList;

public class multiThread extends Thread
{
    final long lastNumber = 100000000; // we can change this constant whenever we want prime numbers less than some other number
    counter count; // counter object shared by all threads to keep track of prime numbers
    long primeNum; // total # of prime numbers this thread found
    long sumPrime; // summation of all prime numbers found by this thread
    ArrayList<Long> primeList = new ArrayList<Long>(); // arrayList of primes so we can get the 10 biggest prime values later

    public multiThread(counter count)
    {
        this.count = count;
    }

    static boolean isPrime(long n)
    {
        // all primes greater than 3 are of the form 6k+1 or 6k-1
        
        // elminate cases where n <= 3
        if(n <= 1)
            return false;
        if(n <= 3)
            return true;
  
        if(n % 2 == 0 || n % 3 == 0) // n % 2 covers 6k+0,2,4 cases. n % 3 covers 6k+3 case.
            return false;
  
        for(int i = 5; i <= Math.sqrt(n); i += 6) // increments of 6
            if (n % i == 0 || n % (i + 2) == 0) // 6k-1 || 6k+1
                return false;
        return true;
    }

    @Override
    public void run()   // tell thread what to do
    {
        long j = 2;
        
        while(j < lastNumber)
        {
            j = count.getAndIncrement();
            if(j > lastNumber) // this is in case count exceeded the threshold
                break;
            if(isPrime(j))
            {
                primeNum++;
                sumPrime += j;
                primeList.add(j);
            }
        }
    }
}
