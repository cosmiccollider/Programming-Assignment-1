My approach to the problem of finding prime numbers up to some given large number was to create 8 threads that would run in parallel following a given protocol. Having these 8 threads running at the same time, I would utilize a synchronized counter between them so that threads will not attempt to determine if the same number is prime, this ensures correctness amongst my threads. I was able to maintain efficiency with my code by utilizing the idea that all prime numbers greater than 3 are of the form 6k(+/-)1. What this meant was that if my number n was of a different form, n would not be prime and if it was the correct form I would only have to check numbers in increments of 6. To further enhance the efficiency of my code I also checked only the cofactors that led up to my tested number n by going up to its square root. This is because a number with no divisible cofactors must be prime.

How to run:
Download all available java type files from the source folder.
To run in command prompt change the directory to the folder that contains the java files using cd followed
by the file path of the folder in double brackets. Ex: cd "/users/folder"
Next type javac hw1.java into the command prompt
Finally type java hw1 and after the file has finished running you will have a txt file called primes.txt
