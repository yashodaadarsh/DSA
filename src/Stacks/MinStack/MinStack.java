package Stacks.MinStack;
import java.util.*;

class MinStack {

    private Stack<Long> st;
    private long min;

    public MinStack() {
        st = new Stack<>();
    }
    
    public void push(int val) {
        if( st.isEmpty() ){
            min = val;
            st.push(1l*val);
        }
        else if( val < min ){
            long newVal = 2l*val - min;
            min = val;
            st.push(newVal);
        }
        else
            st.push(1l*val);
    }
    
    public void pop() {
        if( st.peek() < min ){
            min = 2*min - st.peek();
        }
        st.pop();
    }
    
    public int top() {
        if( st.peek() < min ){
            return (int)min;
        }
        long top = st.peek();
        return (int) top;
    }
    
    public int getMin() {
        return (int)min;
    }
    
}