package crypt.hash;

public class Main {

	public static void main(String[] args) {
		String hash = CryptSHA2.getHashSHA256("TEST");
		System.out.println(hash);
	}

}
