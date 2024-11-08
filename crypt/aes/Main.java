package crypt.aes;

public class Main {

	public static void main(String[] args) {
		String txt = "Test";
		String enc = CryptAES.encrypt(txt);
		String dec = CryptAES.decrypt(enc);
		
		System.out.println(enc);
		System.out.println(dec);
	}

}
