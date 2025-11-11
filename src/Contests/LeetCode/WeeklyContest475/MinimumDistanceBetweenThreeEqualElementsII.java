package Contests.LeetCode.WeeklyContest475;

class MinimumDistanceBetweenThreeEqualElementsII {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        Map<Integer,PriorityQueue<Integer>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for( int i = 0 ; i < n ; i++ ){
            if( map.containsKey(nums[i]) && map.get( nums[i] ).size() >= 2 ){
                PriorityQueue<Integer> pq = map.get( nums[i] );
                int a = pq.poll();
                int b = pq.peek();

                int dist = Math.abs(b-a) + Math.abs(i-b) + Math.abs(i-a);

                min = Math.min( dist , min );

                pq.add(a);
            }
            map.computeIfAbsent(nums[i], k -> new PriorityQueue<>( (a,b) -> b-a ));
            map.get(nums[i]).add(i);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}