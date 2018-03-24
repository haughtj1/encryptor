import java.math.BigInteger;
import java.security.SecureRandom;

public class Encryptor {
	public static void main(String args[]) {
		int keySize = 2048;

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
		String words[] = original.split("\\s+");
		
		StringBuilder enc = new StringBuilder();
		for(int i = 0; i < words.length; i++) {
			System.out.println(words[i]);
			byte[] bytes = words[i].getBytes();
			StringBuilder sb = new StringBuilder();
			
			//appends the char values of a word together
			for(byte b : bytes) {
				sb.append(String.format("%02X", b));
			}
			words[i] = sb.toString();
			System.out.println(words[i]);
			
			BigInteger bigInt = new BigInteger(words[i], 16);
			System.out.println(bigInt.toString());
			
			BigInteger encInt = bigInt.modPow(e, n);
			if(i < words.length - 1) {
				enc.append(encInt + ":");
			} else if(i == words.length - 1) {
				enc.append(encInt);
			}
		}
		
		String encMessage = enc.toString();
		System.out.println(enc.toString());
		
		//This would then transmit
		System.out.println("\nDecrypting Message");
		
		String[] temp = encMessage.split(":");
		StringBuilder dec = new StringBuilder();
		for(String s : temp) {
			BigInteger decWord = new BigInteger(s);
			decWord = decWord.modPow(d, n);
			
			System.out.println(decWord.toString());
			
			String decHex = decWord.toString(16); 
			System.out.println(decHex);
			
			//TODO: need to parse the hex and convert to a string
		}
	}
}
