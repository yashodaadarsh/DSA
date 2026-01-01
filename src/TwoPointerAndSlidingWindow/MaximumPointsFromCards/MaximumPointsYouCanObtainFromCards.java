package TwoPointerAndSlidingWindow.MaximumPointsFromCards;

class MaximumPointsYouCanObtainFromCards {
    public int maxScore(int[] cardPoints, int k) {

        int n = cardPoints.length;
        int leftSum = 0 , rightSum = 0 , maxSum = 0;

        for( int i = 0 ; i < k ; i++ ){
            leftSum += cardPoints[i];
        }

        maxSum = leftSum + rightSum;

        for( int i = 0 ; i < k ; i++ ){
            rightSum += cardPoints[n-1-i];
            leftSum -= cardPoints[k-1-i];
            maxSum = Math.max( maxSum , leftSum + rightSum );
        }

        return maxSum;

    }
}