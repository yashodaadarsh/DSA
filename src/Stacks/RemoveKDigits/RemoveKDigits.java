package Stacks.RemoveKDigits;

import java.util.Stack;

class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        int n = num.length();
        Stack<Integer> st = new Stack<>();
        int i = 0;
        for( i = 0 ; i < n ; i++ ){
            int el = num.charAt(i) - '0';
            while( !st.isEmpty() && k > 0 && st.peek() > el ){
                k--;
                st.pop();
            }
            st.push(el);
        }
        while( k > 0 && !st.isEmpty() ){
            k--;
            st.pop();
        } 
        StringBuilder sb = new StringBuilder();
        while( !st.isEmpty() ){ sb.append(st.pop());
        }
        sb.reverse();
        int j = 0;
        for( j = 0 ; j < sb.length() ; j++ ){
            if( sb.charAt(j) != '0' ) break;
        }
        String ans = sb.substring(j,sb.length());
        return ans.length() == 0 ? "0" : ans;
    }
}