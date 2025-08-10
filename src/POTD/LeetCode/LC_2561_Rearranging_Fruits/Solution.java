package POTD.LeetCode.LC_2561_Rearranging_Fruits;

import java.util.*;

class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int minElem = Integer.MAX_VALUE;

        // Populate from basket1
        for (int x : basket1) {
            countMap.put(x, countMap.getOrDefault(x, 0) + 1);
            minElem = Math.min(minElem, x);
        }

        // Decrement from basket2
        for (int x : basket2) {
            countMap.put(x, countMap.getOrDefault(x, 0) - 1);
            minElem = Math.min(minElem, x);
        }

        List<Integer> extras = new ArrayList<>();

        // Build extras list and check impossibility
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int fruit = entry.getKey();
            int diff = entry.getValue();

            if (diff % 2 != 0) return -1; // Odd difference → impossible

            for (int i = 0; i < Math.abs(diff) / 2; i++) {
                extras.add(fruit);
            }
        }

        // Sort to handle cheapest swaps first
        Collections.sort(extras);

        long cost = 0;
        int n = extras.size();

        // Only first half (smallest) needed for direct cost calculation
        for (int i = 0; i < n / 2; i++) {
            int val = extras.get(i);
            cost += Math.min(val, 2L * minElem);
        }

        return cost;
    }
}