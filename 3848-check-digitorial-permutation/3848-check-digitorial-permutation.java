class Solution {
    public boolean isDigitorialPermutation(int n) {
        int[] fact = new int[10];
        fact[0] = 1;
        for( int i = 1 ; i < 10 ; i++ ){
            fact[i] = i*fact[i-1];
        }

        int factSum = 0;
        int num = n;
        int[] digits = new int[10];
        while( n > 0 ){
            int dig = n%10;
            digits[dig]++;
            n = n/10;
            factSum += fact[dig];
        }

        while( factSum > 0 ){
            int dig = factSum%10;
        // System.out.println( dig );
            digits[dig]--;
            factSum = factSum/10;
        }
        for( int digit : digits ){
            if( digit != 0 ) return false;
        }
        return true;
    }
}