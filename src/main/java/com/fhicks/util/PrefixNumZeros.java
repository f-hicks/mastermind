package com.fhicks.util;

public class PrefixNumZeros {
    public static String prefixNumberWithZeros(String num, int numlength) { 
        if (num.length() < numlength) { //prefix numbers that are not already 4/5 digits with 0's
            StringBuffer sb = new StringBuffer();
            sb.append("0".repeat((numlength-num.length()))); 
            sb.append(num);
            num = sb.toString();
        }
        return num;
    }
}
