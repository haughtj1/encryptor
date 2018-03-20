import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Encryptor {
	public static void main(String args[]) {
		String original = "Hello World! This is some sensitive information that I don't want you to see.";
		String encrypted = "";
		
		System.out.println("Original Message: " + original);
		
		System.out.println("Attempting to generate key");
		long p1 = 0;
		boolean firstPrimeFound = false;
		long p2 = 0;
		boolean secondPrimeFound = false;
		
		Random rand = new Random(System.currentTimeMillis());
		while(!firstPrimeFound) {
			//we want our primes to be random, large and similar size
			p1 = ThreadLocalRandom.current().nextLong( (long)(Math.pow(2, 60)), (long)(Math.pow(2, 61))); 
			System.out.println("Checking if " + p1 + " is a prime number");
			
			//now we try fermat little's theorem to see if we have a prime
			boolean fermats1 = false;
			boolean fermats2 = false;
			while(!fermats1 && !fermats2) {
				long a = ThreadLocalRandom.current().nextLong(2, p1); //random a not divisible by p1
				
				long temp = (long)Math.pow(a, (p1 - 1)); //we want to see if this is congruent with 1 modulo p
				temp--;
				
				if(temp % p1 == 0) {
					
				}
			}
			
			
			
		}
		
		
		
	}
}
