package Maths.LC_3021_AliceAndBobPlayingTheFlowerGame;

class Solution {
    public long flowerGame(int n, int m) {
        int nEven = n/2;
        int nOdd = n - nEven;
        int mEven = m/2;
        int mOdd = m - mEven;
        return ( 1l*nEven * mOdd + 1l*nOdd*mEven ) ;
    }
}