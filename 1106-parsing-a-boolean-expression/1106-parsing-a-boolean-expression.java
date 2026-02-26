class Solution {
    public boolean parseBoolExpr(String expression) {
        Stack<Character> st = new Stack<>();
        for( char ch : expression.toCharArray() ){
            if( ch == ')' ){
                List<Character> list = new ArrayList<>();
                while( st.peek() != '(' ){
                    list.add( st.pop() );
                }
                st.pop();
                char operator = st.pop();
                char result = evaluateExpression( list,operator );
                st.push(result);
            }
            else{
                if( ch != ',' ){
                    st.push(ch);
                }
            }
        }
        return st.peek() == 't';
    }
    private Character evaluateExpression( List<Character> list, char operator ){
        if( operator == '&' ){
            return list.contains('f') ? 'f' : 't';
        }
        else if( operator == '|' ){
            return list.contains('t') ? 't' : 'f';
        }
        else{
            return list.get(0) == 't' ? 'f' : 't';
        }
    }
}