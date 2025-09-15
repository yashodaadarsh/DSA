package Stacks.BalancedParanthesis;

import java.util.Stack;

class BalancedParanthesis {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for( char ch : s.toCharArray() ){
            if( ch == '(' || ch == '[' || ch == '{' ){
                st.push(ch);
            }else{
                if( st.isEmpty() || (( ch == ')' && st.peek() != '(' ) || 
                    ( ch == ']' && st.peek() != '[' ) ||
                    ( ch == '}' && st.peek() != '{' )) )
                    return false;
                st.pop();
            }
        }
        if( !st.isEmpty() ) return false;
        return true;
    }
}