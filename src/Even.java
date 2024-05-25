public class Even {
    public static int calculateEven(byte[] data) {
        int even = 0;
        for (byte b : data) {
            int count = 0;
            for (int i = 0; i < 8; i++) {
                if ((b & 1) == 1) {
                    count++;
                }
                b >>= 1;
            }
            even ^= (count % 2);
        }
        return even;
    }
}
