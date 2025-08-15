package Maths.LC_2264_Largest_3_Same_Digit_Number_in_String;

class Solution {
    public String largestGoodInteger(String num) {
        int n = num.length();
        char maxChar = ' '; // space means "no good integer found yet"

        // Start from index 2 so we can check (i, i-1, i-2)
        for (int i = 2; i < n; i++) {
            char c = num.charAt(i);
            if (c == num.charAt(i - 1) && c == num.charAt(i - 2)) {
                // Found a "good integer" candidate
                if (maxChar == ' ' || c > maxChar) {
                    maxChar = c;
                }
            }
        }

        // If no good integer found
        if (maxChar == ' ') {
            return "";
        }

        // Return maxChar repeated 3 times
        return new String(new char[]{maxChar, maxChar, maxChar});
    }
}
