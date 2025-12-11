package Stacks.PrefixToPostfix;// User function Template for Java

import java.util.Stack;

class PrefixToPostfix {
    private static boolean isOperator(char ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    static String preToPost(String pre_exp) {
        // code here
        Stack<String> st = new Stack<>();
        int n = pre_exp.length();
        for( int i = n-1 ; i >= 0 ; i-- ){
            char ch = pre_exp.charAt(i);
            if( !isOperator(ch) ){
                st.push(ch+"");
            }
            else{
                String t1 = st.pop();
                String t2 = st.pop();
                StringBuilder sb = new StringBuilder();
                sb.append(t1).append(t2).append(ch);
                st.push(sb.toString());
            }
        }
        return st.pop();
    }
}
