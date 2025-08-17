package GreedyAlgorithms.LC_1323_Maximum_69_Number;

class Solution {

    // --- Approach 1: Using String Conversion with char array ---
    public int maximum69Number_String(int num) {
        char[] digits = String.valueOf(num).toCharArray();  
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') {
                digits[i] = '9';
                break; // only replace the first '6'
            }
        }
        return Integer.parseInt(new String(digits));
    }

    // --- Approach 2: Mathematical Approach (without strings) ---
    public int maximum69Number_Math(int num) {
        int temp = num;
        int placeValue = -1;
        int powerOf10 = 1;

        while (temp > 0) {
            int digit = temp % 10;
            if (digit == 6) {
                placeValue = powerOf10; // store place value of this '6'
            }
            temp /= 10;
            powerOf10 *= 10;
        }

        // If no '6' found, return the original number
        if (placeValue == -1) {
            return num;
        }

        // Add 3 * placeValue to turn that '6' into '9'
        return num + 3 * placeValue;
    }

    // --- Approach 3: Using String replaceFirst ---
    public int maximum69Number_ReplaceFirst(int num) {
        String s = String.valueOf(num);
        // replace only the first '6'
        String modified = s.replaceFirst("6", "9");
        return Integer.parseInt(modified);
    }
}
