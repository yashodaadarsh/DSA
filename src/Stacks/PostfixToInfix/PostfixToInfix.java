package Stacks.PostfixToInfix;// User function Template for Java

import java.util.Stack;

class PostfixToInfix {
    private static boolean isOperator(char ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }
    static String postToInfix(String exp) {
        // code here
        Stack<String> st = new Stack<>();
        int n = exp.length();
        for( int i = 0 ; i < n ; i++ ){
            char ch = exp.charAt(i);
            if(!isOperator(ch)){
                st.push(ch+"");
            }
            else{
                StringBuilder sb = new StringBuilder();
                String s2 = st.pop();
                String s1 = st.pop();
                sb.append("(");
                sb.append(s1);
                sb.append(ch+"");
                sb.append(s2);
                sb.append(")");
                st.push(sb.toString());
            }
        }
        //Only one infix String exists in the stack
        return st.pop();
    }
}
