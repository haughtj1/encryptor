import java.math.BigInteger;
import java.security.SecureRandom;

public class Encryptor {
	public static void main(String args[]) {
		int keySize = 32;

		// Generate some random primes
		BigInteger prime1 = new BigInteger(keySize / 2, 100, new SecureRandom());
		BigInteger prime2 = new BigInteger(keySize / 2, 100, new SecureRandom());

		//Calculate the modulus for the public and private keys (n = pq)
		BigInteger n = prime1.multiply(prime2); 
		
		//Calculate the totient (tot = (p - 1)(q - 1))
		BigInteger totient = prime1.subtract(BigInteger.ONE).multiply(prime2.subtract(BigInteger.ONE));

		//Create the public exponent (used to encrypt)
		BigInteger e;
		do e = new BigInteger(totient.bitLength(), new SecureRandom());
		while (e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(totient) >= 0 || !e.gcd(totient).equals(BigInteger.ONE));
		
		//Create the private exponent (used to decrypt)
		BigInteger d = e.modInverse(totient);
		
		//convert the message to an array of BigIntegers
		String original = "Hello World!";
		
		BigInteger enc = new BigInteger(original.getBytes());
		System.out.println("Original: " + enc.toString());
		
		enc = enc.modPow(e, n);
		
		BigInteger dec = enc;
		dec = dec.modPow(d, n);
		System.out.println("Result: " + dec.toString());
		
		//String message = new String(enc.toByteArray());
		//System.out.println(message);
	}
}
