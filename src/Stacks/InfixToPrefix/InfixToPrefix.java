package Stacks.InfixToPrefix;

import java.util.Stack;

class InfixToPrefix {
    
    public StringBuilder reverse( String s ){
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        for( int i = 0 ; i <= n/2 ; i++ ){
            char temp = sb.charAt(i);
            sb.setCharAt(i,sb.charAt(n-1-i));
            sb.setCharAt(n-1-i,temp);
            
            
        }
        
        return sb;
        
    }
    public String infixToPrefix(String s) {
        // code here
        
        int n = s.length();
        
        StringBuilder sb = reverse(s);
        for( int i = 0 ; i < n ; i++ ){
            if (sb.charAt(i) == '(') sb.setCharAt(i, ')');
            else if (sb.charAt(i) == ')') sb.setCharAt(i, '(');
        }
        String postfix =  infixToPostfix(sb.toString());
        return reverse(postfix).toString();
        
    }
    
    public String infixToPostfix(String s) {
        // code here
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();
        for( int i = 0 ; i < n ; i++ ){
            char ch = s.charAt(i);
            if( (ch >= 'A' && ch <= 'Z' ) || 
                (ch >= 'a' && ch <= 'z' ) || 
                (ch >= '0' && ch <= '9' ) ){
                    sb.append(ch);
                }
            else if( ch == '(' ) st.push(ch);
            else if( ch == ')' ){
                while( !st.isEmpty() && st.peek() != '(' ){
                    sb.append( st.pop() );
                }
                st.pop();
            }
            //Operator Case
            else {
                while (!st.isEmpty() &&
                      ((ch != '^' && priority(ch) < priority(st.peek())) ||
                       (ch == '^' && priority(ch) <= priority(st.peek())))) {
                    sb.append(st.pop());
                }
                st.push(ch);
            }

        }
        //Appending remaining operators
        while( !st.isEmpty() ){
            sb.append( st.pop() );
        }
        return sb.toString();
    }
    
    private int priority( char ch ){
        switch(ch){
            case '^':
                return 5;
            case '/':
                return 4;
            case '*':
                return 4;
            case '-':
                return 2;
            case '+':
                return 2;
            default:
                return 0;
        }
    }
    
}