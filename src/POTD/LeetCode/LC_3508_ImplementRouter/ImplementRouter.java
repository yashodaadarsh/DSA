package POTD.LeetCode.LC_3508_ImplementRouter;

class ImplementRouter {

    private Deque<int[]> deque;
    private int limit;
    private Set<String> checkDuplicate;
    private Map<Integer,List<Integer>> map ;

    public Router(int memoryLimit) {
        deque = new ArrayDeque<>();
        this.limit = memoryLimit;
        checkDuplicate = new HashSet<>();
        map = new HashMap<>();
    }
    
    public boolean addPacket(int source, int destination, int timestamp) {
        String isDuplicate = source+"#"+destination+"#"+timestamp;
        if( checkDuplicate.contains(isDuplicate) ) return false;
        if( deque.size() == limit ){
            int[] packet = deque.pollFirst();
            String key = packet[0] + "#" + packet[1] + "#" + packet[2];
            checkDuplicate.remove( key );
            // map.get( packet[1] ).remove( new Integer(packet[2]) );
            map.get( packet[1] ).remove( 0 );
        }
        deque.offerLast(new int[]{source,destination,timestamp});
        checkDuplicate.add(isDuplicate);
        map.putIfAbsent( destination , new ArrayList<>() );
        map.get( destination ).add( timestamp );
        return true;
    }
    
    public int[] forwardPacket() {
        int[] packet = deque.pollFirst();
        if( packet == null ) return new int[]{};
        String key = packet[0] + "#" + packet[1] + "#" + packet[2];
        checkDuplicate.remove( key );
        // map.get( packet[1] ).remove( new Integer(packet[2]) );
        map.get( packet[1] ).remove( 0 );
        return packet;
    }
    
    public int getCount(int destination, int startTime, int endTime) {
        List<Integer> list = map.get( destination );
        if( list == null ) return 0;
        int start = lowerBound( list , startTime );
        int end = upperBound( list , endTime ); 
        return end - start;
    }

    private int lowerBound( List<Integer> arr , int target ){
        int low = 0 , high = arr.size() - 1;
        while( low <= high ){
            int mid = ( high - low )/2 + low ;
            if( arr.get(mid) < target ) low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }

    private int upperBound( List<Integer> arr , int target ){
        int low = 0 , high = arr.size() - 1;
        while( low <= high ){
            int mid = ( high - low )/2 + low ;
            if( arr.get( mid ) <= target ) low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }

}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */