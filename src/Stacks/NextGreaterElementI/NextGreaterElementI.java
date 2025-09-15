package Stacks.NextGreaterElementI;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] greater = findNextGreater( nums2 );
        Map<Integer,Integer> map = new HashMap<>();
        for( int i = 0 ; i < nums2.length ; i++ ){
            map.put( nums2[i] ,i );
        }
        int[] ans = new int[nums1.length];
        int k = 0 ;
        for( int num : nums1 ){
            ans[k++] = greater[map.get(num)];
        }
        return ans;
    }
    int[] findNextGreater( int[] arr ){
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        int[] res = new int[n];
        int k = n-1;
        for( int i = n-1 ; i >= 0 ; i-- ){
            int num = arr[i];
            while( !st.isEmpty() && st.peek() <= num ){
                st.pop();
            }
            res[k--] = (st.isEmpty()) ? -1 : st.peek();
            st.push(num);
        }
        return res;
    }
}