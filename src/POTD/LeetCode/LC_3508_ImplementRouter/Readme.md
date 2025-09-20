# Router Implementation

This project implements a **Router data structure** that simulates packet handling with memory constraints.  
It supports the following operations efficiently:

1. **Add a packet** (if not duplicate and memory not full).
2. **Forward a packet** (remove the oldest packet from the queue).
3. **Query count of packets** for a given destination within a time range.

---

## 🚀 Features
- Uses a **queue (`Deque`)** to maintain packets in FIFO order.
- Prevents **duplicate packets** using a `HashSet`.
- Maintains per-destination packet timestamps using an **`ArrayList`** (sorted by nature of timestamps).
- Efficient **binary search** (`lowerBound`, `upperBound`) for time-range queries.

---

## 📂 Code Structure
- **`Router` class**
    - `addPacket(int source, int destination, int timestamp)`:  
      Adds a new packet if it is not a duplicate.
        - If memory limit is reached, evicts the oldest packet.
        - Inserts timestamp into destination’s list (kept sorted due to increasing timestamps).

    - `forwardPacket()`:  
      Forwards (removes) the oldest packet in the queue.

    - `getCount(int destination, int startTime, int endTime)`:  
      Returns the number of packets for a given destination within the given time range using binary search.

    - Helper functions:
        - `lowerBound(List<Integer> arr, int target)`
        - `upperBound(List<Integer> arr, int target)`

---

## ⚡ Complexity
- `addPacket`: **O(1)** amortized (insert at end of queue + append timestamp).
- `forwardPacket`: **O(1)** (remove from queue + remove first timestamp).
- `getCount`: **O(log n)** (binary search on timestamps).

---

## 📝 Example Usage
```java
public class Main {
    public static void main(String[] args) {
        Router obj = new Router(3);

        System.out.println(obj.addPacket(1, 100, 10)); // true
        System.out.println(obj.addPacket(2, 100, 20)); // true
        System.out.println(obj.addPacket(3, 200, 30)); // true
        System.out.println(obj.addPacket(4, 200, 40)); // evicts (1,100,10), returns true

        int[] forwarded = obj.forwardPacket(); 
        System.out.println(Arrays.toString(forwarded)); // [2, 100, 20]

        System.out.println(obj.getCount(200, 25, 45)); // 2 (timestamps 30, 40)
    }
}
