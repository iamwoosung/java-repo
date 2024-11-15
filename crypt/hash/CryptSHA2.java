package crypt.hash;

public class CryptSHA2 {
	
	private static class CryptBase {
		
		private static final int[] K = {
			0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5,
	        0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
	        0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3,
	        0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
	        0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc,
	        0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
	        0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7,
	        0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
	        0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13,
	        0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
	        0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3,
	        0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
	        0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5,
	        0x391c0cb3, 0x4ed8aa11, 0x5b9cca4f, 0x682e6ff3,
	        0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208,
	        0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
		};
	}
	
	public static String getHashSHA256(String data) {
		byte[] buff = data.getBytes();
        int origin = buff.length;

        // 패딩
        int padding = ((origin + 8) / 64 + 1) * 64;
        byte[] padded = new byte[padding];
        System.arraycopy(buff, 0, padded, 0, origin);
        padded[origin] = (byte) 0x80;
        
        int[] hash = {
        		0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a,
                0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19
        };

        long originLen = origin * 8;
        for (int i = 0; i < 8; i++) {
            padded[padding - 1 - i] = (byte) (originLen >>> (i * 8));
        }
        
        for (int i = 0; i < padded.length / 64; i++) {
            int[] wd = new int[64];
            for (int j = 0; j < 16; j++) {
                wd[j] = ((padded[i * 64 + j * 4] & 0xff) << 24) |
                        ((padded[i * 64 + j * 4 + 1] & 0xff) << 16) |
                        ((padded[i * 64 + j * 4 + 2] & 0xff) << 8) |
                        (padded[i * 64 + j * 4 + 3] & 0xff);
            }
            for (int j = 16; j < 64; j++) {
                int sz = Integer.rotateRight(wd[j - 15], 7) ^ Integer.rotateRight(wd[j - 15], 18) ^ (wd[j - 15] >>> 3);
                int so = Integer.rotateRight(wd[j - 2], 17) ^ Integer.rotateRight(wd[j - 2], 19) ^ (wd[j - 2] >>> 10);
                wd[j] = wd[j - 16] + sz + wd[j - 7] + so;
            }
            
            // shift 시키면서 압축
            int a = hash[0];
            int b = hash[1];
            int c = hash[2];
            int d = hash[3];
            int e = hash[4];
            int f = hash[5];
            int g = hash[6];
            int h = hash[7];

            for (int j = 0; j < 64; j++) {
                int tsz = Integer.rotateRight(e, 6) ^ Integer.rotateRight(e, 11) ^ Integer.rotateRight(e, 25);
                int ch = (e & f) ^ (~e & g);
                int tmpo = h + tsz + ch + CryptBase.K[j] + wd[j];
                int tso = Integer.rotateRight(a, 2) ^ Integer.rotateRight(a, 13) ^ Integer.rotateRight(a, 22);
                int maj = (a & b) ^ (a & c) ^ (b & c);
                int tmpt = tso + maj;

                h = g;
                g = f;
                f = e;
                e = d + tmpo;
                d = c;
                c = b;
                b = a;
                a = tmpo + tmpt;
            }

            hash[0] += a;
            hash[1] += b;
            hash[2] += c;
            hash[3] += d;
            hash[4] += e;
            hash[5] += f;
            hash[6] += g;
            hash[7] += h;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : hash) {
            sb.append(String.format("%08x", i));
        }
        return sb.toString();
	}

}
