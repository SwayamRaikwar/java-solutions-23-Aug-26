class Solution {
    public int divide(int dividend, int divisor) {
        // Handle overflow edge case: Integer.MIN_VALUE / -1 = Integer.MAX_VALUE + 1 (overflows 32-bit int)
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine the sign of the quotient
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Convert inputs to long to prevent overflow during absolute value operations
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);

        int quotient = 0;

        // Exponential subtraction using bit shifts
        while (absDividend >= absDivisor) {
            long tempDivisor = absDivisor;
            long multiple = 1;

            // Double tempDivisor and multiple until it exceeds absDividend
            while (absDividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }

            absDividend -= tempDivisor;
            quotient += multiple;
        }

        return negative ? -quotient : quotient;
    }
}
