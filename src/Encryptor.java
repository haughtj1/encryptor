import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class Encryptor {
	public static void main(String args[]) {
		int keySize = 10;
		
		String original = "Hello World! This is some sensitive information that I don't want you to see.";
		String encrypted = "";
		
		System.out.println("Original Message: " + original);
		
		System.out.println("Attempting to generate key");
		
		BigInteger p1 = BigInteger.probablePrime(keySize, new Random());
		BigInteger p2 = BigInteger.probablePrime(keySize, new Random());
		
		
		BigInteger n = p1.multiply(p2); //modulus for the public key and private key
		
		BigInteger totient = p1.subtract(BigInteger.ONE);
		totient = totient.multiply(p2.subtract(BigInteger.ONE));
		
		//gives us the size to help create public key exponent
		BigInteger size = BigInteger.TWO;
		int numBits;
		for(numBits = 0; size.pow(numBits).compareTo(totient) == -1; numBits++) {
		}
		
		BigInteger e = BigInteger.probablePrime(numBits - 1, new Random());
		
		BigInteger d = null;
				
		System.out.println("generated prime number " + p1.toString());
		System.out.println("generated prime number " + p2.toString());
		System.out.println("totient " + totient.toString() + " is " + numBits + " bits long");
		System.out.println("public key exponent is " + e.toString());
		System.out.println("private key exponent is " + e.toString());
	}
	
}
