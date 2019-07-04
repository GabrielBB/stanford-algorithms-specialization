package com.github.gabrielbb.stanford.algorithms.course1;

import java.math.BigInteger;

public class GradeSchoolMultiplication {

    public static String solve(String a, String b) {
        return String.valueOf(solve(a, b, b.length() - 1, new StringBuilder("1")));
    }

    private static BigInteger solve(String a, String b, int bIndex, StringBuilder base) {

        if (bIndex < 0)
            return BigInteger.ZERO;

        final int currentB = Character.getNumericValue(b.charAt(bIndex));

        BigInteger abTotal = BigInteger.ZERO;
        int remaining = 0;
        StringBuilder innerBase = new StringBuilder("1");

        for (int i = a.length() - 1; i >= 0; i--) {
            int ab = currentB * Character.getNumericValue(a.charAt(i)) + remaining;
            remaining = ab / 10;

            abTotal = abTotal.add(
                    BigInteger.valueOf(remaining > 0 ? ab % 10 : ab).multiply(new BigInteger(innerBase.toString())));

            innerBase.append("0");
        }

        if (remaining > 0) {
            abTotal = abTotal.add(BigInteger.valueOf(remaining).multiply(new BigInteger(innerBase.toString())));
        }

        return abTotal.multiply(new BigInteger(base.toString())).add(solve(a, b, --bIndex, base.append("0")));
    }
}