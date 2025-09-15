package Strings.LC_1935_MaximumNumberOfWordsYouCanType;

public class MaximumNumberOfWordsYouCanType {

    // Approach 1
    public int canBeTypedWordsI(String text, String brokenLetters) {
        // Step 1: Pre-process broken letters
        boolean[] broken = new boolean[26];
        for (char ch : brokenLetters.toCharArray()) {
            broken[ch - 'a'] = true;
        }

        // Step 2: Iterate through text
        int result = 0;
        boolean canType = true;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch == ' ') {
                // Word ends here
                if (canType) {
                    result++;
                }
                // Reset for next word
                canType = true;
            } else {
                // Check if current character is broken
                if (broken[ch - 'a']) {
                    canType = false;
                }
            }
        }

        // Step 4: Handle the last word
        if (canType) {
            result++;
        }

        return result;
    }

    //Approach 2
    public int canBeTypedWordsII(String text, String brokenLetters) {
        String[] words = text.split(" ");
        int n = words.length , count = 0;
        for( char ch : brokenLetters.toCharArray() ){
            for( int i = 0 ; i < n ; i++ ){
                if( words[i].contains(ch+"") ){
                    words[i]="";
                    count++;
                }
            }
        }
        return n-count;
    }
}
