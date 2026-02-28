class Solution {
    class Pair{
        char ch;
        int freq;
        Pair( char ch, int freq ){
            this.ch = ch;
            this.freq = freq;
        }
    }
    public String frequencySort(String s) {

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> b.freq - a.freq
        );

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.offer(new Pair(entry.getKey(), entry.getValue()));
        }

        StringBuilder sb = new StringBuilder();

        while( !pq.isEmpty() ){
            Pair pair = pq.poll();
            char ch = pair.ch;
            int n = pair.freq;
            for( int i = 0 ; i < n; i++ ){
                sb.append(ch);
            }
        }
        return sb.toString();

    }
}