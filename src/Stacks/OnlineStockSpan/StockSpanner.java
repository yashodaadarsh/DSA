package Stacks.OnlineStockSpan;

import java.util.Stack;

class StockSpanner {
    private Stack<int[]> st;
    private int day;
    public StockSpanner() {
        st = new Stack<>();
        day = 0;
    }
    
    public int next(int price) {
        day++;
        while( !st.isEmpty() && st.peek()[0] <= price ) st.pop();
        int last = st.isEmpty() ? 0 : st.peek()[1];
        st.push( new int[]{price,day} );
        return day - last;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */