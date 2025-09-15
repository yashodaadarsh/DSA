package Stacks.NextSmallerElement;
import java.util.*;

class NextSmallerElement {
    static ArrayList<Integer> nextSmallerEle(int[] arr) {
        // code here
        int[] smaller = findNextSmaller( arr );
        ArrayList<Integer> list = new ArrayList<>();
        for( int num : smaller ){
            list.add(num);
        }
        return list;
    }
    static private int[] findNextSmaller( int[] nums ){
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        int k = n-1;
        for( int i = n-1 ; i >= 0 ; i-- ){
            while( !st.isEmpty() && st.peek() >= nums[i] ){
                st.pop();
            }
            res[k--] = st.isEmpty() ? -1 : st.peek();
            st.push(nums[i]);
        }
        return res;
    }
}