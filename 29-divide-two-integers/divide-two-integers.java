class Solution {
    public int divide(int dividend, int divisor) {
        // Edge case: overflow when dividing Integer.MIN_VALUE by -1
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine the sign of the result
        // true if both have the same sign, false otherwise
        boolean isPositive = (dividend < 0) == (divisor < 0);

        // Convert both to negative numbers to prevent overflow
        int a = dividend < 0 ? dividend : -dividend;
        int b = divisor < 0 ? divisor : -divisor;

        int quotient = 0;

        // Perform bitwise division using exponential steps
        while (a <= b) {
            int temp = b;
            int multiple = 1;

            // Double the divisor until doubling it exceeds `a`
            // `temp >= Integer.MIN_VALUE >> 1` prevents integer underflow
            while (temp >= (Integer.MIN_VALUE >> 1) && a <= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            a -= temp;
            quotient += multiple;
        }

        return isPositive ? quotient : -quotient;
    }
}