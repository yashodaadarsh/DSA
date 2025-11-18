package Arrays.LC_717_1_bit_and_2_bit_Characters;

class Solution {

    //Approach 1
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int i = 0;

        // Traverse up to the second-to-last index (n-2)
        while (i < n - 1) { 
            // Check if bits[i] is 1 (start of a 2-bit character)
            if (bits[i] == 1) { 
                i += 2; // Jump by 2
            } else { 
                // If bits[i] is 0 (a 1-bit character)
                i += 1; // Jump by 1
            }
        }
        
        // Return true if the pointer landed exactly on n - 1
        return i == n - 1; 
    }

    //Approach 2
    public boolean isOneBitCharacter2(int[] bits) {
        int n = bits.length;
        int countOfOnes = 0;

        // Start traversal from n - 2 (index just before the final 0)
        int i = n - 2;

        // Keep counting as long as we see 1s and are within array bounds
        while (i >= 0 && bits[i] == 1) {
            countOfOnes++;
            i--;
        }

        // If the count of continuous 1s is even, the answer is True.
        // If the count is odd, the answer is False.
        // We check if (countOfOnes % 2 == 0)
        return (countOfOnes % 2 == 0);
    }
}