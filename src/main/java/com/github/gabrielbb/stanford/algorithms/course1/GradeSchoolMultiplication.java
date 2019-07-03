package com.github.gabrielbb.stanford.algorithms.course1;

public class GradeSchoolMultiplication {

    public static long solve(long a, long b, int base) {
        
        if(b / base == 0)
            return 0;

        final int digit = (int) (b / base) % 10; 

        long result = 0;
        int innerBase = 1;
        int remaining = 0;

        while(a / innerBase > 0) {

            int singleResult = (int) (digit * (a / innerBase % 10)) + remaining;
            remaining = 0;

            if(singleResult >= 10) {
                remaining = singleResult / 10;
                result += singleResult % 10 * innerBase;
            } else {
                result += singleResult * innerBase;
            }

            innerBase *= 10;
        }

        if(remaining > 0) {
            result += remaining * innerBase;
        }

        return (result * base) + solve(a, b, base * 10);
    }
}