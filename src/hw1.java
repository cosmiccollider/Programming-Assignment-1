import java.io.FileWriter;   // Import the FileWriter class
public class hw1
{
    // This function will take an array of threads and return an array holding the top 10 max prime numbers found among all threads
    public static long[] getMaxList(multiThread[] threadArr)
    {
        multiThread th;
        long[] maxList = new long[10]; 
        long max = -1; // max value among all 8 threads
        int maxThread = -1; // Tells us the thread holding max so we can remove max value from list
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                th = threadArr[j]; // current thread
                long biggestPrimeInThread = th.primeList.get(th.primeList.size()-1); // biggest prime found by the thread is its last added prime
                if(biggestPrimeInThread > max) 
                {
                    maxList[10-i-1] = biggestPrimeInThread; // biggestPrimeInThread is only the biggest for that iteration of i
                    max = biggestPrimeInThread;
                    maxThread = j;
                }
            }
            max = -1; // reset max so we can find the other 10 maximum values
            th = threadArr[maxThread];
            th.primeList.remove(th.primeList.size()-1); // we dont need the number so we remove it from the list 
        }
        return maxList;
    }

    public static void main(String[] args) throws Exception 
    {
        fileInfo file = new fileInfo();
        file.newFile("primes"); // creates a text file with the name primes
        FileWriter myWriter = new FileWriter("primes.txt");

        counter count = new counter();
        long start = System.nanoTime();
        multiThread[] threadArr = new multiThread[8];

        for(int i = 0; i < 8; i++)  // initializes each thread and tells it to start 
        {
            threadArr[i] =  new multiThread(count);
            threadArr[i].start();
        }
        for(int i = 0; i < 8; i++)  // joins each thread telling main to wait until all threads have finished
            threadArr[i].join();
        
        // execution time
        long finish = System.nanoTime();
        double timeInSeconds = (double) (finish-start) / 1000000000;
        
        // # of prime numbers and their summations
        long total = 0, sum = 0;
        
        for(int i = 0; i < 8; i++)  // adds however many prime numbers each thread found to total
            total += threadArr[i].primeNum;
        for(int i = 0; i < 8; i++)  // adds the sum of prime numbers each thread found
            sum += threadArr[i].sumPrime;
        
        String msg = "<"+timeInSeconds+" seconds> " + "<"+total+" # of prime numbers> "+ "<"+sum+" sum of prime #'s> ";
        file.writeFile(msg,myWriter);

        
        long[] maxList = getMaxList(threadArr); // finds the top 10 biggest prime numbers 
        
        String tmp = "<";
        for(int z = 0; z < 10; z++) // adds max prime numbers to txt file
        {
            if(z == 9)
                tmp = tmp + maxList[z] + ", maximum primes listed in order from lowest to highest>";
            else
                tmp = tmp + maxList[z] + ", ";
        }
        file.writeFile(tmp,myWriter);
        long finish2 = System.nanoTime();
        double timeInSeconds2 = (double) (finish2-start) / 1000000000;
        System.out.println("time 1: "+timeInSeconds);
        System.out.println("time 2: "+timeInSeconds2);

        myWriter.close();
    }
}
