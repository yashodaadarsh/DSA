
# Stack Implementation using a Single Queue

This repository contains the discussion and implementation details for simulating a **Stack** data structure using one or more **Queues**.

## 💡 Core Concepts

| Data Structure | Rule | Description |
| :--- | :--- | :--- |
| **Stack** | LIFO (Last In, First Out) | The last element entered is the first one accessed or removed. |
| **Queue** | FIFO (First In, First Out) | The first element entered is the first one accessed or removed. |

The goal is to implement standard stack operations (`push`, `pop`, `top`) while only utilizing queue functions.

## Approach 1: Implementing Stack using Couple of Queues

This method utilizes two queues, $Q1$ and $Q2$. The objective is to ensure that $Q1$ always holds the elements in the correct LIFO order, meaning the most recently added element is always at the front of $Q1$ (which is the top of the stack).

### Push Operation (O(N) Time Complexity)

When a new element $X$ is pushed, a series of steps ensures $Q1$ maintains the LIFO order:

1.  **Add $X$ to $Q2$**: The new element $X$ is first inserted into $Q2$.
2.  **Transfer $Q1$ to $Q2$**: All existing elements from $Q1$ are moved element by element to $Q2$.
    *   *Rationale*: Since $Q1$ previously contained the stack elements, transferring them *after* $X$ is added to $Q2$ ensures $X$ (the new 'top') is now at the front of $Q2$.
3.  **Swap $Q1$ and $Q2$**: The queues are swapped, so $Q1$ now contains the new stack structure, with $X$ at the front.

**Pseudocode for Push(X) (Using two Queues):**

```
function Push(X):
// 1. Insert X to Q2
Q2.push(X)

    // 2. Transfer all elements from Q1 to Q2
    while Q1 is not empty:
        Q2.push(Q1.front)
        Q1.pop()

    // 3. Swap Q1 and Q2
    Swap(Q1, Q2)
```

### Pop and Top Operations (O(1) Time Complexity)

Since $Q1$ is maintained such that its front element is always the stack's top, these operations are simple:

*   **Top**: Return the front element of $Q1$ (`Q1.front` or `Q1.top`).
*   **Pop**: Remove the front element of $Q1$ (`Q1.pop`).

### Complexity Analysis (Couple of Queues)

| Operation | Time Complexity | Space Complexity |
| :--- | :--- | :--- |
| **Push** | $\text{Big O}(N)$ (due to transferring all $N$ elements) | $\text{Big O}(N)$ (requires two queues, $2N$, plus a third variable for swapping) |
| **Pop/Top** | $\text{Big O}(1)$ | $\text{Big O}(1)$ |

---

## Approach 2: Implementing Stack using a Single Queue (Optimization)

This approach is an optimized version that implements the stack functionality using only one queue. The time complexity for the `push` operation remains $O(N)$.

### Push Operation (O(N) Time Complexity)

When pushing a new element $X$ into the single queue $Q$, the following steps are performed:

1.  **Push $X$**: Add $X$ to the end of the queue $Q$.
2.  **Rotate Elements**: Iterate for $N-1$ times, where $N$ is the current size of $Q$ (one less than the current size). In each iteration:
    *   Take the element currently at the front of $Q$.
    *   Push that element back onto $Q$ (to the rear).
    *   Pop the element from the front of $Q$.
    *   *Rationale*: This rotation moves all the old elements behind the newly added element $X$. After $N-1$ rotations, $X$ will be the new front of the queue, thus correctly simulating the LIFO order.

**Pseudocode for Push(X) (Using Single Queue):**

```
function Push(X):
// 1. Push X to the queue
Q.push(X)

    // 2. Rotate N-1 elements
    N = Q.size()
    
    // Iterate till one less than size
    for i = 0 to N - 2: 
        element = Q.front() 
        Q.push(element)
        Q.pop() 
```

### Pop and Top Operations (O(1) Time Complexity)

*   **Top**: Return the front element of $Q$ (`Q.top`).
*   **Pop**: Remove the front element of $Q$ (`Q.pop`).

### Complexity Analysis (Single Queue)

| Operation | Time Complexity | Space Complexity |
| :--- | :--- | :--- |
| **Push** | $\text{Big O}(N)$ (due to rotating $N-1$ elements) | $\text{Big O}(N)$ (only one queue is used) |
| **Pop/Top** | $\text{Big O}(1)$ | $\text{Big O}(1)$ |

### Conclusion

When addressing this problem, it is recommended to first discuss the implementation using two queues and then present the optimization using a single queue.
