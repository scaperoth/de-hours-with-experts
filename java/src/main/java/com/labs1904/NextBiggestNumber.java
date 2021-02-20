package com.labs1904;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NextBiggestNumber {

    public static void main(String[] args) {
        Integer input = Integer.parseInt(args[0]);
        Integer nextBiggestNumber = getNextBiggestNumber(input);
        System.out.println("Input: " + input);
        System.out.println("Next biggest number: " + nextBiggestNumber);
    }

    public static int[] intToArray(Integer int_to_convert){
        String temp = Integer.toString(int_to_convert);
        int[] new_int = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++)
        {
            new_int[i] = temp.charAt(i) - '0';
        }
        return new_int;
    }

    public static int intArrToInt(int[] intArr){
        List<String> strListValues = new ArrayList<String>(intArr.length);
        for (Integer digit : intArr) { 
            strListValues.add(String.valueOf(digit)); 
        }
        String strValue = String.join("", strListValues);
        return Integer.parseInt(strValue);  
    }

    public static int getNextBiggestNumber(Integer num) {
        int[] digits = intToArray(num);
        int sm_from_right = -1;

        // (1) split left and right at left number being 
        // smaller than right number
        for (int idx = digits.length - 1; idx >= 0; idx--) {
            if(idx == 0){
                break;
            }

            if(digits[idx-1] < digits[idx]){
                sm_from_right = idx - 1;
                break;
            }
        }

        
        // (2) if we traversed the whole number, we're donezo
        if(sm_from_right == -1){
            return -1;
        }
               

        // (3) find new number on right less than the splitting number
        int sm_idx = sm_from_right + 1;
        for (int r_idx = sm_idx; r_idx < digits.length; r_idx++) {
            if(digits[r_idx] > digits[sm_from_right] && digits[r_idx] < digits[sm_idx]){
                sm_idx = r_idx;
            }
        }

        // (4) swap lowest higher number on right with final left number 
        // which is the splitting number
        int tmp = digits[sm_from_right];
        digits[sm_from_right] = digits[sm_idx];
        digits[sm_idx] = tmp;

        // (5) sort the right side and BAM! we're done
        Arrays.sort(digits, sm_from_right + 1, digits.length);
        
        return intArrToInt(digits);
    }
}
