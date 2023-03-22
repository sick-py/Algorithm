public class CountPrimes {
    public int countPrimes0(int n) {
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                res++;
            }
        }
        return res;
    } // but it will exceed the time limit

    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n + 2];
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                res++;
                for (int j = 2; j * i < n; j++) {
                    notPrime[j * i] = true;
                }
            }
        }
        return res;
    }

    boolean isPrime(int n) {
        int count = 0;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                count++;
                break;
            }
        }
        return count == 0;
    }
}
