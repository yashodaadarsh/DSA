package Stacks.TrappingRainWater;

class TrappingRainWater {

    // with pointers
    public int trapWithPointers(int[] height) {
        int total = 0;
        int n = height.length;
        int l = 0 , r = n-1 , lMax = 0 , rMax = 0;
        while( l <= r ){
            if( height[l] <= height[r] ){
                if( lMax > height[l] ){
                    total += lMax - height[l];
                }
                lMax = Math.max( lMax , height[l] );
                l++;
            }
            else{
                if( rMax > height[r] ){
                    total += rMax - height[r];
                }
                rMax = Math.max( rMax , height[r] );
                r--;
            }
        }
        return total;
    }

    // with extra array
    public int trap(int[] height) {
        int n = height.length;
        int[] lh = new int[n];
        int[] rh = new int[n];
        int max = 0;
        for( int i = 0 ; i < n ; i++ ){
            if( height[i] > max )   max = height[i];
            lh[i] = max;
        }
        max = 0;
        for( int i = n-1 ; i >= 0 ; i-- ){
            if( height[i] > max )   max = height[i];
            rh[i] = max;
        }

        int ans = 0;

        for( int i = 0 ; i < n ; i++ ){
            ans += Math.min(lh[i] , rh[i]) - height[i];
        }
        return ans;
    }
}