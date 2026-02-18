public class practical12 {

    static long countOn3;
    static long countOn2A;
    static long countOn2B;
    static long countOn;

    public static int mcsOn3(int[] X) {
        int n = X.length;
        int maxSoFar = 0;

        for (int low = 0; low < n; low++) {
            for (int high = low; high < n; high++) {
                int sum = 0;
                for (int r = low; r < high; r++) {
                    sum += X[r];
                    countOn3++;
                }
                if (sum > maxSoFar) {
                    maxSoFar = sum;
                }
            }
        }
        return maxSoFar;
    }

    public static int mcsOn2A(int[] X) {
        int n = X.length;
        int maxSoFar = 0;

        for (int low = 0; low < n; low++) {
            int sum = 0;
            for (int r = low; r < n; r++) {
                sum += X[r];
                countOn2A++;
                if (sum > maxSoFar) {
                    maxSoFar = sum;
                }
            }
        }
        return maxSoFar;
    }

    // O(n^2) version B (prefix sums)
    public static int mcsOn2B(int[] X) {
        int n = X.length;
        int[] sumTo = new int[n + 1];

        sumTo[0] = 0;
        for (int i = 1; i <= n; i++) {
            sumTo[i] = sumTo[i - 1] + X[i - 1];
        }

        int maxSoFar = 0;

        for (int low = 0; low < n; low++) {
            for (int high = low; high < n; high++) {
                int sum = sumTo[high + 1] - sumTo[low];
                countOn2B++;
                if (sum > maxSoFar) {
                    maxSoFar = sum;
                }
            }
        }

        return maxSoFar;
    }
    public static int mcsOn(int[] X) {
        int maxSoFar = 0;
        int maxToHere = 0;

        for (int i = 0; i < X.length; i++) {
            maxToHere = Math.max(maxToHere + X[i], 0);
            countOn++;
            maxSoFar = Math.max(maxSoFar, maxToHere);
        }

        return maxSoFar;
    }

    public static void main(String[] args) {

        int[] sizes = {100, 1000, 10000, 100000};

        System.out.println("n\tO(n^3)\t\tO(n^2)A\t\tO(n^2)B\t\tO(n)");

        for (int n : sizes) {

            int[] X = new int[n];

            for (int i = 0; i < n; i++) {
                X[i] = (int)(Math.random() * 20) - 10;
            }

            countOn3 = 0;
            countOn2A = 0;
            countOn2B = 0;
            countOn = 0;

            if (n <= 1000) {
                mcsOn3(X);
            }

            mcsOn2A(X);
            mcsOn2B(X);
            mcsOn(X);

            System.out.println(n + "\t" +
                    countOn3 + "\t\t" +
                    countOn2A + "\t\t" +
                    countOn2B + "\t\t" +
                    countOn);
        }
    }
}