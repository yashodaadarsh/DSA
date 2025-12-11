package Stacks.PrefixToInfix;// User function Template for Java

import java.util.Stack;

class PrefixToInfix {
    private static boolean isOperator(char ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    static String preToInfix(String pre_exp) {
        // code here
        Stack<String> st = new Stack<>();
        int n = pre_exp.length();
        for( int i = n-1 ; i >= 0 ; i-- ){
            char ch = pre_exp.charAt(i);
            if( !isOperator(ch) ){
                st.push(ch+"");
            }
            else{
                String s1 = st.pop();
                String s2 = st.pop();
                StringBuilder sb = new StringBuilder();
                sb.append( "(" + s1 + ch + s2 + ")" );
                st.push(sb.toString());
            }
        }
        return st.pop();
    }
}
